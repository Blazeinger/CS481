package semantic_analysis;

import ast.*;
import util.ErrorList;
import util.Pair;

import java.util.*;

/*@ The [SymbolTable] class provides a visitor of NAP abstract syntax trees.
    If the NAP program is correct, it builds Symbol Tables for this program.
    The global symbol table (function signatures) can be obtained with
    the getter "getGlobalTable". All the local tables are given as a map
    from blocks to variable symbol tables. The getter is
    "getLocalTables". */
public class SymbolTableBuilder extends ErrorList implements Visitor<Void>
{

    private SymbolTable symbolTable;
    private VisitedBlocks visitedBlocks;

    public SymbolTableBuilder() {
        this.symbolTable = new SymbolTable();
        this.visitedBlocks = new VisitedBlocks();
    }

    public SymbolTable getSymbolTable(){
        if (hasErrors())
            return null;
        else
            return symbolTable;
    }

    // For the symbol tables, we just need to look at:
    // - The headers of function definitions
    // - Declarations in block
    // All the other nodes are visited only to check for
    // undeclared variables.
    // Type Void has no object, we always return null.

    // ===========================================
    // Types
    // ===========================================
    @Override
    public Void visit(Type type) { return null; }

    // ===========================================
    // Expressions: Literals
    // ===========================================
    @Override
    public Void visit(ExpBool exp) { return null; }
    @Override
    public Void visit(ExpChar exp) { return null; }
    @Override
    public Void visit(ExpInt exp) { return null; }
    @Override
    public Void visit(ExpString exp) { return null; }

    // ===========================================
    // Other Expressions
    // ===========================================
    @Override
    public Void visit(ExpVar exp) {
        Optional<type.Type> type =
                symbolTable.varLookup(exp.name, visitedBlocks);
        if (!type.isPresent())
            addError("Undeclared variable " + exp.name
                    + " at position " + exp.pos);
        return null;
    }
    @Override
    public Void visit(ExpBinop exp) {
        exp.left.accept(this);
        return exp.right.accept(this);
    }
    @Override
    public Void visit(ExpArrAccess exp) {
        exp.array.accept(this);
        return exp.index.accept(this);
    }
    @Override
    public Void visit(ExpNew exp) {
        return exp.exp.accept(this);
    }
    @Override
    public Void visit(ExpUnop exp) {
        return exp.exp.accept(this);
    }
    @Override
    public Void visit(ExpAssignop exp) {
        return exp.exp.accept(this);
    }
    @Override
    public Void visit(ExpFuncCall exp) {
        // As functions are visible from anywhere in
        // a NAP program, we will check whether names of
        // called functions are actually defined in the
        // next phase.
        return visitListExpression(exp.arguments);
    }
    @Override
    public Void visit(ExpPredefinedCall exp) {
        return visitListExpression(exp.arguments);
    }
    @Override
    public Void visit(ExpArrEnum array) {
        return visitListExpression(array.exps);
    }

    private Void visitListExpression(List<Expression> list) {
        for(Expression arg : list)
            arg.accept(this);
        return null;
    }

    // ===========================================
    // Statements: Instructions
    // ===========================================
    @Override
    public Void visit(StmIf stm) {
        stm.then_branch.accept(this);
        if (stm.else_branch.isPresent()) {
            stm.else_branch.get().accept(this);
        }
        return stm.condition.accept(this);
    }
    @Override
    public Void visit(StmAssign stm) {
        stm.lValue.accept(this);
        return stm.exp.accept(this);
    }
    @Override
    public Void visit(StmExp stm) {
        return stm.exp.accept(this);
    }
    @Override
    public Void visit(StmRead stm) {
        return stm.exp.accept(this);
    }
    @Override
    public Void visit(StmPrint stm) {
        return stm.exp.accept(this);
    }
    @Override
    public Void visit(StmReturn stm) {
        return stm.exp.accept(this);
    }
    @Override
    public Void visit(StmWhile stm) {
        stm.body.accept(this);
        return stm.condition.accept(this);
    }

    // When a name is declared two problems are possible:
    // 1. the name is already declared in the block or the ascendant blocks,
    //    or is an already defined function.
    // 2. the name is a reserved word
    private void checkName(Position pos, String name, String nameType, String errorLocation, boolean local){
        if ((local && symbolTable.varLookup(name, visitedBlocks).isPresent())
                || (!local && symbolTable.funcLookup(name).isPresent()))
            addError(nameType + " already declared" + errorLocation
                    + ": " + name + "  " + pos);
        if (ReservedWord.is(name))
            addError(nameType + " name is incorrect " + errorLocation
                    + ": " + name + " is a NAP reserved word " + pos);
    }
    @Override
    public Void visit(StmFor stm) {
        type.Type type = stm.type.type;
        String variable = stm.var;
        checkName(stm.pos, variable, "Variable", "in for loop", true);
        Block body = stm.body;
        symbolTable.createLocalTable(body);
        symbolTable.addVariable(body, variable, type);
        body.accept(this);
        return stm.collection.accept(this);
    }

    // ===========================================
    // Statements: Declaration
    // ===========================================
    @Override
    public Void visit(StmDecl stm) {
        type.Type type = stm.binding.getSnd().type;
        String variable = stm.binding.getFst();
        checkName(stm.pos, variable, "Variable", " in declaration", true);
        // Warning: first check the initialization expression,
        //          then add the new binding to the local table.
        if (stm.initialization.isPresent())
            stm.initialization.get().accept(this);
        Block block = visitedBlocks.current();
        symbolTable.addVariable(block, variable, type);
        return null;
    }

    // ===========================================
    // Block
    // ===========================================
    @Override
    public Void visit(Block block) {
        // The local table of a newly visited block
        // may have been already built by the parent
        // AST node. If not, we create it.
        symbolTable.createLocalTable(block);
        visitedBlocks.enter(block);
        for(Statement stm: block.statements)
            stm.accept(this);
        visitedBlocks.exit();
        return null;
    }

    // ===========================================
    // Function Definition
    // ===========================================
    private Signature signatureOf(FunctionDefinition fun){
        List<Pair<type.Type, Boolean>> argTypes = new ArrayList<>();
        for (Pair<Pair<String, Type>, Boolean> pair : fun.arguments) {
            type.Type type = pair.getFst().getSnd().type;
            boolean passByReference = pair.getSnd();
            argTypes.add(new Pair<>(type, passByReference));
        }
        if (fun.returnType.isPresent())
            return new Signature(argTypes, Optional.of(fun.returnType.get().type));
        else
            return new Signature(argTypes, Optional.empty());
    }

    @Override
    public Void visit(FunctionDefinition fun) {
        checkName(fun.pos, fun.name, "Function", "", false);
        Signature signature = signatureOf(fun);
        symbolTable.addFunction(fun.name, signature);
        symbolTable.createLocalTable(fun.body);
        visitedBlocks.enter(fun.body); // the parameters are considered as part of the body
        for(Pair<Pair<String, Type>, Boolean> binding : fun.arguments){
            String variable = binding.getFst().getFst();
            type.Type type = binding.getFst().getSnd().type;
            checkName(fun.pos, variable, "Argument", " in function header", true);
            symbolTable.addVariable(fun.body, variable, type);
        }
        visitedBlocks.exit(); // we should exit as the next line will enter the same block again
        fun.body.accept(this);
        return null;
    }

    // ===========================================
    // Program
    // ===========================================
    @Override
    public Void visit(Program program) {
        for(FunctionDefinition fun : program.functions)
            fun.accept(this);
        return null;
    }
}