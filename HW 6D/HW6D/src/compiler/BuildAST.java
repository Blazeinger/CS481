package compiler;

import ast.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import parser.napLexer;
import parser.napParser;
import parser.napVisitor;
import util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BuildAST extends AbstractParseTreeVisitor<Ast> implements napVisitor<Ast>
{
    // Building an AST Position from an ANTLR Context
    private static Position position(ParserRuleContext ctx) {
        return new Position(ctx.start.getLine(),
                ctx.start.getCharPositionInLine());
    }

    // Several ParseTree node are basically binary operation applications.
    // getOpBinary and binary are two private methods used to
    // avoid code duplication when these ParseTree nodes will be visited.
    private OpBinary getOpBinary(int opType) {
        switch(opType) {
            case napLexer.ADD: return OpBinary.ADD;
            case napLexer.SUB: return OpBinary.SUB;
            case napLexer.MUL: return OpBinary.MUL;
            case napLexer.DIV: return OpBinary.DIV;
            case napLexer.MOD: return OpBinary.MOD;
            case napLexer.AND: return OpBinary.AND;
            case napLexer.OR: return OpBinary.OR;
            case napLexer.NEQ: return OpBinary.NEQ;
            case napLexer.LT: return OpBinary.LT;
            case napLexer.LE: return OpBinary.LE;
            case napLexer.GT: return OpBinary.GT;
            case napLexer.GE: return OpBinary.GE;
            default: return OpBinary.EQ;
        }
    }

    private Ast binary(Position pos, napParser.ExprContext expr0,
                       napParser.ExprContext expr1, int opType) {
        Expression left = (Expression) visit(expr0);
        Expression right = (Expression) visit(expr1);
        return new ExpBinop(pos, left, getOpBinary(opType), right);
    }


    // ======================================================
    // Type
    // ======================================================

    @Override
    public Ast visitTInt(napParser.TIntContext ctx) {
        return new ast.Type(position(ctx), type.Basic.INT);
    }

    @Override
    public Ast visitTBool(napParser.TBoolContext ctx) {
        return new ast.Type(position(ctx), type.Basic.BOOL);
    }

    @Override
    public Ast visitTChar(napParser.TCharContext ctx) {
        return new ast.Type(position(ctx), type.Basic.CHAR);
    }

    @Override
    public Ast visitTFloat(napParser.TFloatContext ctx) {
        return new ast.Type(position(ctx), type.Basic.FLOAT);
    }

    @Override
    public Ast visitTByte(napParser.TByteContext ctx) {
        return new ast.Type(position(ctx), type.Basic.BYTE);
    }

    @Override
    public Ast visitTArray(napParser.TArrayContext ctx) {
        ast.Type type = (Type) ctx.type().accept(this);
        return new Type(position(ctx), new type.Array(type.type));
    }

     // ======================================================
    // Expression
    // ======================================================

    @Override
    public Ast visitEBool(napParser.EBoolContext ctx) {
        int lexerType = ctx.Bool().getSymbol().getType();
        assert(lexerType == napLexer.TRUE || lexerType == napLexer.FALSE);
        return new ExpBool(position(ctx), lexerType == napLexer.TRUE);
    }

    @Override
    public Ast visitEInt(napParser.EIntContext ctx) {
        int value = Integer.parseInt(ctx.getText());
        return new ExpInt(position(ctx), value);
    }

    @Override
    public Ast visitEIdentifier(napParser.EIdentifierContext ctx) {
        return new ExpVar(position(ctx), ctx.getText());
    }

    private String escaping(String input){
        return input.replace("\\n", "\n")
                .replace("\\t", "\t")
                .replace("\\0", "\0")
                .replace("\\\"", "\"")
                .replace("\\'", "'")
                .replace("\\\\", "\\");
    }

    @Override
    // The char constants are represented in the ParseTree by
    // a string. For example, if the NAP program contains '\n'
    // the ParseTree will contain a ECharContext node with text "'\\n'"
    // We have only a few escaped characters in nap.g4:
    // \n, \t, \\, \', \", \0
    public Ast visitEChar(napParser.ECharContext ctx) {
        char c = escaping(ctx.getText()).charAt(1);
        return new ExpChar(position(ctx), c);
    }

    @Override
    public Ast visitEString(napParser.EStringContext ctx) {
        String escaped = escaping(ctx.getText());
        return new ExpString(position(ctx), escaped.substring(1, escaped.length()-1));
    }

    @Override
    public Ast visitEOpp(napParser.EOppContext ctx) {
        return new ExpUnop(position(ctx), OpUnary.SUB,
                (Expression) visit(ctx.expr()));
    }

    @Override
    public Ast visitENot(napParser.ENotContext ctx) {
        return new ExpUnop(position(ctx), OpUnary.NOT,
                (Expression) visit(ctx.expr()));
    }

    @Override
    public Ast visitEMuls(napParser.EMulsContext ctx) {
        int opType = ctx.op.getType();
        assert(opType == napLexer.MUL || opType == napLexer.DIV || opType == napLexer.MOD);
        return binary(position(ctx), ctx.expr(0), ctx.expr(1), opType);
    }

    @Override
    public Ast visitEAdds(napParser.EAddsContext ctx) {
        int opType = ctx.op.getType();
        assert(opType == napLexer.ADD || opType == napLexer.SUB);
        return binary(position(ctx), ctx.expr(0), ctx.expr(1), opType);
    }

    @Override
    public Ast visitEAnd(napParser.EAndContext ctx) {
        int opType = ctx.AND().getSymbol().getType();
        assert(opType == napLexer.AND);
        return binary(position(ctx), ctx.expr(0), ctx.expr(1), opType);
    }

    @Override
    public Ast visitEOr(napParser.EOrContext ctx) {
        int opType = ctx.OR().getSymbol().getType();
        assert(opType == napLexer.OR);
        return binary(position(ctx), ctx.expr(0), ctx.expr(1), opType);
    }

    @Override
    public Ast visitECmp(napParser.ECmpContext ctx) {
        int opType = ctx.op.getType();
        assert(opType == napLexer.EQ || opType == napLexer.NEQ || opType == napLexer.LT
                || opType == napLexer.LE || opType == napLexer.GT || opType == napLexer.GE);
        return binary(position(ctx), ctx.expr(0), ctx.expr(1), opType);
    }

    private Ast assignmentOperator(Position pos, napParser.ExprContext expr, int opType, boolean prefix){
        Expression exp = (Expression) visit(expr);
        OpAssign op = OpAssign.INC;
        if (opType == napLexer.DECR) op = OpAssign.DEC;
        return new ExpAssignop(pos, op, exp, prefix);
    }

    @Override
    public Ast visitEPrefix(napParser.EPrefixContext ctx) {
        int opType = ctx.AssignOp().getSymbol().getType();
        return assignmentOperator(position(ctx), ctx.expr(), opType, true);
    }

    @Override
    public Ast visitEPostfix(napParser.EPostfixContext ctx) {
        int opType = ctx.AssignOp().getSymbol().getType();
        return assignmentOperator(position(ctx), ctx.expr(), opType, false);
    }

    @Override
    public Ast visitEArrayAccess(napParser.EArrayAccessContext ctx) {
        Expression array = (Expression) visit(ctx.expr(0));
        Expression index = (Expression) visit(ctx.expr(1));
        return new ExpArrAccess(position(ctx), array, index);
    }

    // We manipulate list of expressions in two cases:
    // array enumerations and function calls.
    // This private function factorizes the common code
    private List<Expression> expList(List<napParser.ExprContext> exprs){
        List<Expression> arguments = new LinkedList<>();
        for(napParser.ExprContext expr : exprs)
            arguments.add((Expression)visit(expr));
        return arguments;
    }

    // We consider that the application of some predefined functions
    // are application of some unary operations rather than function application:
    // byte_of_int, int_of_byte, char_of_byte, byte_of_char, length
    private static Map<String, OpPredefined> buildPredefined(){
        Map<String, OpPredefined> predefined = new TreeMap<>();
        predefined.put("byte_of_int", OpPredefined.BYTE_OF_INT);
        predefined.put("char_of_byte", OpPredefined.CHAR_OF_BYTE);
        predefined.put("int_of_byte", OpPredefined.INT_OF_BYTE);
        predefined.put("byte_of_char", OpPredefined.BYTE_OF_CHAR);
        predefined.put("length", OpPredefined.LENGTH);
        return predefined;
    }

    private static final Map<String, OpPredefined> predefined = buildPredefined();

    private boolean isPredefined(String name){
        return predefined.get(name) != null;
    }

    @Override
    public Ast visitECall(napParser.ECallContext ctx) {
        String funcName = ctx.Identifier().getText();
        List<Expression> arguments = expList(ctx.expressions().expr());
        if (isPredefined(funcName))
            return new ExpPredefinedCall(position(ctx), predefined.get(funcName), arguments);
        return new ExpFuncCall(position(ctx), funcName, arguments);
    }

    @Override
    public Ast visitEArrayEnumeration(napParser.EArrayEnumerationContext ctx) {
        return new ExpArrEnum(position(ctx), expList(ctx.expressions().expr()));
    }

    @Override
    public Ast visitENew(napParser.ENewContext ctx) {
        Type type = (Type) visit(ctx.type());
        Expression exp = (Expression) visit(ctx.expr());
        return new ExpNew(position(ctx), type, exp);
    }

    @Override
    public Ast visitEPar(napParser.EParContext ctx) {
        return visit(ctx.expr());
    }

    // ======================================================
    // Instruction
    // ======================================================

    private static OpBinary opBinaryOfAssign(int opType){
        assert(opType == napLexer.AEQ || opType == napLexer.SEQ
                || opType == napLexer.MEQ || opType == napLexer.DEQ);
        switch (opType){
            case napLexer.AEQ: return OpBinary.ADD;
            case napLexer.SEQ: return OpBinary.SUB;
            case napLexer.MEQ: return OpBinary.MUL;
            default: return OpBinary.DIV;
        }
    }

    @Override
    public Ast visitIAssign(napParser.IAssignContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        int opType = ctx.op.getType();
        if (opType == napLexer.ASSIGN)
            return new StmAssign(position(ctx), left, right);
        else
            return new StmAssign(position(ctx), left, right, opBinaryOfAssign(opType));
    }

    @Override
    public Ast visitIFor(napParser.IForContext ctx) {
        Type type = (Type) visit(ctx.type());
        String identifier = ctx.Identifier().getText();
        Expression exp = (Expression) visit(ctx.expr());
        Block block = (Block) visit(ctx.block());
        return new StmFor(position(ctx), type, identifier, exp, block);
    }

    @Override
    public Ast visitIWhile(napParser.IWhileContext ctx) {
        Expression condition = (Expression) visit(ctx.expr());
        Block block = (Block) visit(ctx.block());
        return StmWhile.While(position(ctx), condition, block);
    }

    @Override
    public Ast visitIDoWhile(napParser.IDoWhileContext ctx) {
        Expression condition = (Expression) visit(ctx.expr());
        Block block = (Block) visit(ctx.block());
        return StmWhile.DoWhile(position(ctx), condition, block);
    }

    @Override
    public Ast visitIInput(napParser.IInputContext ctx) {
        Type type = (Type) visit(ctx.type());
        Expression exp = (Expression) visit(ctx.expr());
        return new StmRead(position(ctx), type, exp);
    }

    @Override
    public Ast visitIPrint(napParser.IPrintContext ctx) {
        Type type = (Type) visit(ctx.type());
        Expression exp = (Expression) visit(ctx.expr());
        return new StmPrint(position(ctx), type, exp);
    }

    @Override
    public Ast visitIIf(napParser.IIfContext ctx) {
        Expression condition = (Expression) visit(ctx.expr());
        Block then_block = (Block) visit(ctx.block(0));
        if (ctx.block(1) == null)
            return new StmIf(position(ctx), condition, then_block);
        else {
            Block else_block = (Block) visit(ctx.block(1));
            return new StmIf(position(ctx), condition, then_block, else_block);
        }
    }

    @Override
    public Ast visitIReturn(napParser.IReturnContext ctx) {
        Expression exp = (Expression) visit(ctx.expr());
        return new StmReturn(position(ctx), exp);
    }

    @Override
    public Ast visitIExpr(napParser.IExprContext ctx) {
        Expression exp = (Expression) visit(ctx.expr());
        return new StmExp(position(ctx), exp);
    }

    // ======================================================
    // Declaration
    // ======================================================

    @Override
    public Ast visitDeclaration(napParser.DeclarationContext ctx) {
        Type type = (Type) ctx.type().accept(this);
        String name = ctx.Identifier().getText();
        if(ctx.expr() == null)
            return new StmDecl(position(ctx), name, type);
        else {
            Expression exp = (Expression) ctx.expr().accept(this);
            return new StmDecl(position(ctx), name, type, exp);
        }
    }

    // ======================================================
    // Statement
    // ======================================================

    @Override
    public Ast visitSIns(napParser.SInsContext ctx) {
        return ctx.instruction().accept(this);
    }

    @Override
    public Ast visitSDecl(napParser.SDeclContext ctx) {
        return ctx.declaration().accept(this);
    }

    // ======================================================
    // Block
    // ======================================================

    @Override
    public Ast visitBlock(napParser.BlockContext ctx) {
        List<Statement> statements = new LinkedList<>();
        for(napParser.StatementContext stm: ctx.statement())
            statements.add((Statement)visit(stm));
        return new Block(position(ctx), statements);
    }

    // ======================================================
    // Function Definition
    // ======================================================

    @Override
    public Ast visitFunction_definition(napParser.Function_definitionContext ctx) {
        String name = ctx.Identifier().getText();
        List<Pair<Pair<String, Type>, Boolean>> arguments = new LinkedList<>();
        for(napParser.ParameterContext arg : ctx.parameters().parameter()){
            String argName = arg.Identifier().getText();
            Type argType = (Type) arg.type().accept(this);
            boolean passByRef = arg.getChildCount() == 3;
            arguments.add(new Pair<>(new Pair<>(argName, argType), passByRef));
        }
        Block block = (Block) visit(ctx.block());
        if (ctx.returnType == null)
            return new FunctionDefinition(position(ctx), name, arguments, block);
        else {
            Type returnType = (Type) visit(ctx.returnType);
            return new FunctionDefinition(position(ctx), name, arguments, block, returnType);
        }
    }

    // ======================================================
    // Program
    // ======================================================

    @Override
    public Ast visitProgram(napParser.ProgramContext ctx) {
        List<FunctionDefinition> functions = new LinkedList<>();
        for(napParser.Function_definitionContext func : ctx.function_definition())
            functions.add((FunctionDefinition)visit(func));
        return new Program(position(ctx), functions);
    }

    // ======================================================
    // NOT USED
    // ======================================================

    @Override
    public Ast visitExpressions(napParser.ExpressionsContext ctx) {
        assert(false) : "The AST builder should not arrive there.";
        return null;
    }

    @Override
    public Ast visitParameter(napParser.ParameterContext ctx) {
        assert(false) : "The AST builder should not arrive there.";
        return null;
    }

    @Override
    public Ast visitParameters(napParser.ParametersContext ctx) {
        assert(false) : "The AST builder should not arrive there.";
        return null;
    }
}