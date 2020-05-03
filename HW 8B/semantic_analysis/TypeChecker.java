package semantic_analysis;

import ast.*;
import type.Array;
import type.Basic;
import type.Type;
import util.ErrorList;

import java.util.*;

public class TypeChecker extends ErrorList implements ast.Visitor<Optional<type.Type>> {

    private SymbolTable symbolTable;
    private VisitedBlocks visitedBlocks;
    private String currentFunction;

    public TypeChecker(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.visitedBlocks = new VisitedBlocks();
        this.currentFunction = null;
    }

    public VisitedBlocks getVisitedBlocks() {
        return visitedBlocks;
    }

    // In NAP only variables or array cells are assignable
    private boolean assignable(Ast ast){
        return (ast instanceof ExpVar) || (ast instanceof ExpArrAccess);
    }

    @Override
    public Optional<Type> visit(ast.Type type) {
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(ExpBool exp) {
        return Optional.of(Basic.BOOL);
    }

    @Override
    public Optional<Type> visit(ExpChar exp) {
        return Optional.of(Basic.CHAR);
    }

    @Override
    public Optional<Type> visit(ExpInt exp) {
        return Optional.of(Basic.INT);
    }

    @Override
    public Optional<Type> visit(ExpString exp) {
        return Optional.of(new Array(Basic.CHAR));
    }

    @Override
    public Optional<Type> visit(ExpVar exp) {
        Optional<Type> type = symbolTable.varLookup(exp.name, visitedBlocks);
        return type;
    }

    @Override
    public Optional<Type> visit(ExpBinop exp) {
        Optional<type.Type> leftType = exp.left.accept(this);
        Optional<type.Type> rightType = exp.right.accept(this);
        Signature signature = Signatures.binary.get(exp.op);
        if (leftType.isPresent() && rightType.isPresent()) {
            if (exp.op == OpBinary.EQ || exp.op == OpBinary.NEQ)
                if (!leftType.get().equals(rightType.get()))
                    addError("The two arguments to the (in)equality "
                            + "should have the same type " + exp.pos);
                else
                    return Optional.of(Basic.BOOL);
            if (signature != null && !signature.check(leftType.get(), rightType.get()))
                addError("Operation expected two arguments of types "
                        + signature.argTypes.get(0).getFst() + " and "
                        + signature.argTypes.get(1).getFst() + " but values of types "
                        + leftType.get() + " and " + rightType.get()
                        + " are given " + exp.pos);
        }
        if (!leftType.isPresent())
            addError("Left argument of binary operation should have a type "
                    + exp.left.pos);
        if (!rightType.isPresent())
            addError("Right argument of binary operation should have a type "
                    + exp.right.pos);
        return signature==null?Optional.empty():signature.returnType;
    }

    @Override
    public Optional<Type> visit(ExpUnop exp) {
        Optional<type.Type> argType = exp.exp.accept(this);
        Signature signature = Signatures.unary.get(exp.op);
        if (argType.isPresent() && !signature.check(argType.get()))
            addError("Operation expected one argument of type "
                       + signature.argTypes.get(0) + " but a value of type "
                       + argType.get() + " is given " + exp.pos);
        return signature.returnType;
    }

    @Override
    public Optional<Type> visit(ExpAssignop exp) {
        Optional<type.Type> type = exp.exp.accept(this);
        if(!(type.isPresent() && type.get().equals(Basic.INT)))
            addError("The argument of " + exp.op + " should have type "
                    + Basic.INT + " " + exp.exp.pos);
        if (!assignable(exp.exp))
            addError("The argument of " + exp.op + " should be assignable "
                    + exp.exp.pos);
        return Optional.of(Basic.INT);
    }


    private void checkArguments(Position pos, Signature signature, List<Expression> arguments){
        int counter = 0;
        int expectedArity = signature.argTypes.size();
        int actualArity = arguments.size();
        if (expectedArity != actualArity)
            addError("The applied function expects " + expectedArity
                     + " argument"+(expectedArity>1?"s":"") + " but is applied "
                     + "to " + actualArity + " argument"+(actualArity>1?"s":"")
                     + " " + pos);
        else
            for(Expression arg : arguments) {
                Optional<type.Type> actualType = arg.accept(this);
                type.Type expectedType = signature.argTypes.get(counter).getFst();
                boolean passByReference = signature.argTypes.get(counter).getSnd();
                if (!(actualType.isPresent() && actualType.get().equals(expectedType)))
                    addError("Expected type for argument: " + expectedType
                            + (actualType.isPresent()?
                                " but argument has type " + actualType.get() : "")
                            + " " + arg.pos);
                if (passByReference && !assignable(arg))
                    addError("Argument is passed by reference and therefore "
                            + "should be assignable " + arg.pos);
                counter += 1;
        }
    }

    @Override
    public Optional<Type> visit(ExpFuncCall exp) {
        Optional<Signature> optSignature = symbolTable.funcLookup(exp.funcName);
        if (!optSignature.isPresent()) {
            addError("Function " + exp.funcName + " undefined " + exp.pos);
            return Optional.empty();
        }
        Signature signature = optSignature.get();
        checkArguments(exp.pos, signature, exp.arguments);
        return signature.returnType;
    }

    private Optional<type.Type> checkLength(ExpPredefinedCall exp){
        assert(exp.funcName == OpPredefined.LENGTH);
        if (exp.arguments.size() != 1)
            addError("length expects 1 argument " + exp.pos);
        else {
            Expression argument = exp.arguments.get(0);
            Optional<type.Type> argType = argument.accept(this);
            if(!(argType.isPresent() && argType.get() instanceof type.Array))
                addError("length expects an array as argument "
                        + argument.pos);
        }
        return Optional.of(Basic.INT);
    }

    @Override
    public Optional<Type> visit(ExpPredefinedCall exp) {
        if (exp.funcName == OpPredefined.LENGTH)
            return checkLength(exp);
        Signature signature = Signatures.predefined.get(exp.funcName);
        checkArguments(exp.pos, signature, exp.arguments);
        return signature.returnType;
    }

    @Override
    public Optional<Type> visit(ExpNew exp) {
        type.Type cellType = exp.type.type;
        Optional<type.Type> sizeType = exp.exp.accept(this);
        if (!(sizeType.isPresent() && sizeType.get().equals(Basic.INT)))
            addError("Expression should have type " + Basic.INT
                    + " " + exp.exp.pos);
        return Optional.of(new Array(cellType));
    }

    @Override
    public Optional<Type> visit(ExpArrAccess exp) {
        Optional<type.Type> arrayType = exp.array.accept(this);
        Optional<type.Type> indexType = exp.index.accept(this);
        if(!(indexType.isPresent() && Basic.INT.equals(indexType.get())))
            addError("The index should have type int " + exp.pos);
        if(!(arrayType.isPresent() && arrayType.get() instanceof type.Array)) {
            addError("Expression should have a type of the form array<T> " +
                        exp.array.pos);
            return Optional.empty();
        }
        return Optional.of(((Array)arrayType.get()).type);
    }

    @Override
    public Optional<Type> visit(ExpArrEnum array) {
        // For being able to type an array enumeration,
        // we need to have at least one element in the
        // enumeration. If the first correctly typed element
        // of the enumeration has type T, then all other arguments
        // should have type T, and the array enumeration has type array<T>.
        // It the array enumeration is empty, then we don't know
        // what is T.
        // It is possible to handle this case, but would make
        // the type system more complicated. For the moment,
        // we don't support this case.
        List<Expression> exps = array.exps;
        assert(0 < exps.size()) :
                "Array enumerations of length 0 are not supported yet "
                + array.pos;
        type.Type cellType = null;
        for(Expression exp : exps){
            Optional<type.Type> currentCellType = exp.accept(this);
            if(currentCellType.isPresent() && cellType == null)
                cellType = currentCellType.get();
            if (!currentCellType.isPresent())
                addError("Expression should have a valid NAP type: " + exp.pos);
            else if(!currentCellType.get().equals(cellType) && cellType != null)
                addError("All expressions in an array enumeration should have the "
                        + "same type " + cellType + ", but this expression has type "
                        + currentCellType.get() + " " + exp.pos);
        }
        if (cellType == null)
            return Optional.empty();
        else
            return Optional.of(new Array(cellType));
    }

    @Override
    public Optional<Type> visit(StmExp stm) {
        stm.exp.accept(this);
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(StmAssign stm) {
        Optional<type.Type> lValueType = stm.lValue.accept(this);
        Optional<type.Type> expType = stm.exp.accept(this);
        if (!lValueType.isPresent()) {
            addError("The left hand side of the assignment should "
                    + " have a valid NAP type " + stm.lValue.pos);
        }
        if (!(lValueType.isPresent() && expType.isPresent() &&
                lValueType.get().equals(expType.get())))
            addError("Both sides of the assignment should "
                    + " have the same type but the left hand side "
                    + " has type " + (lValueType.isPresent()?lValueType.get():lValueType)
                    + " and the right hand side has type " + (expType.isPresent()?expType.get():expType)
                    + " " + stm.lValue.pos);
        if (!assignable(stm.lValue))
            addError("The left hand side of the assignment "
                    + "should be assignable " + stm.lValue.pos);
        if(stm.op.isPresent() &&
                !(lValueType.isPresent() && lValueType.get().equals(Basic.INT) &&
                        expType.isPresent() && expType.get().equals(Basic.INT)))
            addError("When an assignment operator is used, " +
                    "both sides should have type int " + stm.pos);
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(StmRead stm) {
        Optional<type.Type> expType = stm.exp.accept(this);
        type.Type readType = stm.type.type;
        if (!assignable(stm.exp))
            addError("The expression argument of a read "
                    + "should be assignable " + stm.exp.pos);
        if (!expType.isPresent()) {
            addError("The expression argument of a read "
                    + "should have a valid NAP type " + stm.exp.pos);
            return Optional.empty();
        }
        if (!readType.equals(expType.get()))
            addError("The expression argument of a read "
                    + "should have the type declared in the read "
                    + "but it has type " + expType.get() + "and "
                    + readType + " was expected" + stm.pos);
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(StmPrint stm) {
        Optional<type.Type> expType = stm.exp.accept(this);
        type.Type printType = stm.type.type;
        if (!expType.isPresent()) {
            addError("The expression argument of a print should "
                    + "have a valid NAP type " + stm.exp.pos);
            return Optional.empty();
        }
        if (!printType.equals(expType.get()))
            addError("The expression argument of a print "
                    + "should have the type declared in the print "
                    + "but it has type " + expType.get() + "and "
                    + printType + " was expected" + stm.pos);
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(StmReturn stm) {
        Optional<type.Type> expType = stm.exp.accept(this);
        assert(symbolTable.funcLookup(currentFunction).isPresent());
        Signature signature = symbolTable.funcLookup(currentFunction).get();
        Optional<type.Type> returnType = signature.returnType;
        if (!returnType.isPresent()) {
            addError("The function signature does not have a return type "
                    + "but a return statement is present " + stm.pos);
            return Optional.empty();
        }
        if (!expType.equals(returnType)) {
            addError("The returned expression has type " + expType
                + " but from the function signature, type " + returnType
                + " is expected " + stm.exp.pos);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(StmIf stm) {
        Optional<type.Type> type = stm.condition.accept(this);
        if (!(type.isPresent() && Basic.BOOL.equals(type.get())))
            addError("The condition of a conditional statement "
                    + "should have type " + Basic.BOOL + stm.condition.pos);
        stm.then_branch.accept(this);
        if (stm.else_branch.isPresent())
            stm.else_branch.get().accept(this);
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(StmWhile stm) {
        Optional<type.Type> type = stm.condition.accept(this);
        if (!(type.isPresent() && Basic.BOOL.equals(type.get())))
            addError("The condition of a while loop "
                    + "should have type " + Basic.BOOL + stm.condition.pos);
        return stm.body.accept(this);
    }

    @Override
    public Optional<Type> visit(StmFor stm) {
        Optional<type.Type> collType = stm.collection.accept(this);
        if(!(collType.isPresent() && collType.get() instanceof type.Array))
            addError("The type of expression the loop iterates over "
                + "should be of the form array<T> " + stm.collection.pos);
        else {
            if (!stm.type.type.equals(((Array) collType.get()).type))
                addError("The type of the loop variable should be compatible "
                    + " with the type of the collection" + stm.type.pos);
        }
        stm.body.accept(this);
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(StmDecl stm) {
        if (stm.initialization.isPresent()) {
            Optional<type.Type> expType = stm.initialization.get().accept(this);
            type.Type declType = stm.binding.getSnd().type;
            if (!(expType.isPresent() && declType.equals(expType.get())))
                addError("The declared type for variable " + stm.binding.getFst()
                        + " and the type of the initial value are different "
                        + stm.initialization.get().pos);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(Block block) {
        visitedBlocks.enter(block);
        for(Statement stm : block.statements)
            stm.accept(this);
        visitedBlocks.exit();
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(FunctionDefinition fun) {
        this.currentFunction = fun.name;
        fun.body.accept(this);
        return Optional.empty();
    }

    @Override
    public Optional<Type> visit(Program program) {
        for(FunctionDefinition fun : program.functions)
            fun.accept(this);
        return Optional.empty();
    }
}