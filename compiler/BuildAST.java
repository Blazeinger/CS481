package compiler;

import ast.*;
import parser.*;
import parser.W2Parser.*;

import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class BuildAST extends W2BaseVisitor<Ast> {

    private static Position position(ParserRuleContext ctx) {
        return new Position(ctx.start.getLine(),
                ctx.start.getCharPositionInLine());
    }

    @Override
    public Ast visitProgram(W2Parser.ProgramContext ctx) {
        List<FunctionDefinition> funcs = new ArrayList<>();
        for (Function_definitionContext dc : ctx.function_definition())
            funcs.add((FunctionDefinition) visit(dc));
        return new Program(position(ctx), funcs);
    }

    @Override
    public Ast visitFunctionDefinition(W2Parser.ProgramContext ctx) {
	    String name = (String) visit(ctx.Identifier());
	    Block body = (Block) visit(ctx.block());
	    List<Pair<Pair<String, Type>, Boolean>> arguments = new ArrayList<>();
	    for (ParametersContext pc : ctx.parameters())
	    		arguments.add((Parameter) visit(pc));
	    return new FunctionDefinition(position(ctx), name, arguments, body);
    }

   @Override
   public Ast visitBlock(W2Parser.BlockContext ctx) {
       List<Statement> statements = new ArrayList<>();
       for (StatementContext stm : ctx.statement())
           statements.add((Statement) stm.accept(this));
       return new Block(statements);
   }

    @Override
    public Ast visitTINT(W2Parser.TINTContext ctx) {
        return new Typ(TypBasic.INTEGER);
    }

    @Override
    public Ast visitTBOOL(W2Parser.TBOOLContext ctx) {
        return new Typ(TypBasic.BOOLEAN;
    }

    @Override
    public Ast visitTCHAR(W2Parser.TCHARContext ctx) {
	    return new Typ(TypBasic.CHARACTER);
    }

    @Override
    public Ast visitTFLOAT(W2Parser.TFLOATContext ctx) {
	    return new Typ(TypBasic.FLOAT);
    }

    @Override
    public Ast visitTBYTE(W2Parser.TBYTEContext ctx) {
	    return new Typ(TypBasic.BYTE);
    }

    @Override
    public Ast visitTARRAY(W2Parser.TARRAYContext ctx) {
	    return new TypArr(TypBasic.ARRAY);
    }

    @Override
    public Ast visitDeclaration(W2Parser.StatementContext ctx) {
	    String name = (String) visit(ctx.Identifier());
	    Optional<Expression> val = (Expression) ctx.expression;
	    Type type = (Type) visit(ctx.Type());
	    return new Declaration(position(ctx), name, type, val);
    }

    @Override
    public Ast visitIAssign(W2Parser.IAssignContext ctx) {
        String var = ctx.Identifier().toString();
        Exp exp = (Exp) visit(ctx.expr());
        return new InsAssign(position(ctx), var, exp);
    }

    @Override
    public Ast visitIIf(W2Parser.IIfContext ctx) {
        Exp condition = (Exp) visit(ctx.expr());
        Block then_branch = (Block) visit(ctx.block(0));
        Block else_branch = (Block) visit(ctx.block(1));
        return new InsIf(position(ctx), condition,
                then_branch, else_branch);
    }

    @Override
    public Ast visitIWhile(W2Parser.IWhileContext ctx) {
        Exp condition = (Exp) visit(ctx.expr());
        Block body = (Block) visit(ctx.block());
        return new InsWhile(position(ctx), condition, body);
    }

    @Override
    public Ast visitIPrint(W2Parser.IPrintContext ctx) {
        Exp exp = (Exp) visit(ctx.expr());
        return new InsPrint(position(ctx), exp);
    }

    @Override
    public Ast visitIInput(W2Parser.IInputContext ctx) {
        String var = ctx.Identifier().toString();
        return new InsInput(position(ctx), var);
    }

    @Override
    public Ast visitEId(W2Parser.EIdContext ctx) {
        return new ExpVar(position(ctx), ctx.Identifier().toString());
    }

    @Override
    public Ast visitEOpNeg(W2Parser.EOpNegContext ctx) {
        Exp exp = (Exp) visit(ctx.expr());
        return new ExpUnop(position(ctx), OpUnary.NOT, exp);
    }

    @Override
    public Ast visitEOpMin(W2Parser.EOpMinContext ctx) {
        Exp exp = (Exp) visit(ctx.expr());
        return new ExpUnop(position(ctx), OpUnary.MINUS, exp);
    }

    @Override
    public Ast visitEOpOr(W2Parser.EOpOrContext ctx) {
        Exp left = (Exp) visit(ctx.expr(0));
        Exp right = (Exp) visit(ctx.expr(1));
        return new ExpBinop(position(ctx), left, OpBinary.OR, right);
    }

    @Override
    public Ast visitEInt(W2Parser.EIntContext ctx) {
        return new ExpInt(position(ctx),
			  Integer.parseInt(ctx.IConstant().toString()));
    }

    @Override
    public Ast visitEBool(W2Parser.EBoolContext ctx) {
        return new ExpBool(position(ctx),
			   Boolean.parseBoolean(ctx.BConstant().toString()));
    }

    @Override
    public Ast visitEOpAnd(W2Parser.EOpAndContext ctx) {
        Exp left = (Exp) visit(ctx.expr(0));
        Exp right = (Exp) visit(ctx.expr(1));
        return new ExpBinop(position(ctx), left, OpBinary.AND, right);
    }
		       
    @Override
    public Ast visitEOpOr(W2Parser.EOpOrContext ctx) {
        Exp left = (Exp) visit(ctx.expr(0));
        Exp right = (Exp) visit(ctx.expr(1));
        return new ExpBinop(position(ctx), left, OpBinary.OR, right);
    }

    @Override
    public Ast visitEOpCmp(W2Parser.EOpCmpContext ctx) {
        Exp left = (Exp) visit(ctx.expr(0));
        Exp right = (Exp) visit(ctx.expr(1));
        OpBinary cmp = OpBinary.EQ;
        switch (ctx.op.getType()) {
            case W2Lexer.NEQ:
                cmp = OpBinary.NEQ;
                break;
            case W2Lexer.EQ:
                cmp = OpBinary.EQ;
                break;
            case W2Lexer.LT:
                cmp = OpBinary.LT;
                break;
            case W2Lexer.GT:
                cmp = OpBinary.GT;
                break;
            case W2Lexer.LE:
                cmp = OpBinary.LE;
                break;
            case W2Lexer.GE:
                cmp = OpBinary.GE;
                break;
        }
        return new ExpBinop(position(ctx), left, cmp, right);
    }

    @Override
    public Ast visitEPar(W2Parser.EParContext ctx) {
        return (Exp) visit(ctx.expr());
    }

    @Override
    public Ast visitEOpMuls(W2Parser.EOpMulsContext ctx) {
        Exp left = (Exp) visit(ctx.expr(0));
        Exp right = (Exp) visit(ctx.expr(1));
        OpBinary op = null;
        switch (ctx.op.getType()) {
            case W2Lexer.DIV:
                op = OpBinary.DIV;
                break;
            case W2Lexer.MOD:
                op = OpBinary.MOD;
                break;
            case W2Lexer.MUL:
                op = OpBinary.MUL;
        }
        return new ExpBinop(position(ctx), left, op, right);
    }

    @Override
    public Ast visitEOpAdds(W2Parser.EOpAddsContext ctx) {
        Exp left = (Exp) visit(ctx.expr(0));
        Exp right = (Exp) visit(ctx.expr(1));
        OpBinary op = null;
        switch (ctx.op.getType()) {
            case W2Lexer.SUB:
                op = OpBinary.SUB;
                break;
            case W2Lexer.ADD:
                op = OpBinary.ADD;
                break;
        }
        return new ExpBinop(position(ctx), left, op, right);
    }
}
