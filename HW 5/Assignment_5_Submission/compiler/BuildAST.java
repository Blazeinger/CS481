package compiler;

import ast.*;
import parser.*;
import parser.napParser.*;

import java.util.*;
import javafx.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class BuildAST extends napBaseVisitor<Ast> {

	private static Position position(ParserRuleContext ctx) {
	   return new Position(ctx.start.getLine(),
	           ctx.start.getCharPositionInLine());
	}

	@Override
	public Ast visitProgram(napParser.ProgramContext ctx) {
	   List<FunctionDefinition> funcs = new ArrayList<>();
	   for (Function_definitionContext dc : ctx.function_definition())
	       funcs.add((FunctionDefinition) visit(dc));
	   return new Program(position(ctx), funcs);
	}

	// public Ast visitFunctionDefinition(napParser.Function_definitionContext ctx) {
	//     String name = visit(ctx.Identifier()).toString();
	//     Block body = (Block) visit(ctx.block());
	//     List<Parameter> arguments = new ArrayList<>();
	//     for (ParametersContext pc : ctx.parameter())
	//     		arguments.add((Parameter) visit(pc));
	//     return new FunctionDefinition(position(ctx), name, arguments, body);
	// }

	@Override
	public Ast visitBlock(napParser.BlockContext ctx) {
	  List<Statement> statements = new ArrayList<>();
	  for (StatementContext stm : ctx.statement())
	      statements.add((Statement) stm.accept(this));
	  return new Block(position(ctx), statements);
	}

	public Ast visitTInt(napParser.TIntContext ctx) {
	   return new TypBasic(BasicType.INT);
	}

	@Override
	public Ast visitTBool(napParser.TBoolContext ctx) {
	   return new TypBasic(BasicType.BOOL);
	}

	@Override
	public Ast visitTChar(napParser.TCharContext ctx) {
	    return new TypBasic(BasicType.CHAR);
	}

	@Override
	public Ast visitTFloat(napParser.TFloatContext ctx) {
	    return new TypBasic(BasicType.FLOAT);
	}

	@Override
	public Ast visitTByte(napParser.TByteContext ctx) {
	    return new TypBasic(BasicType.BYTE);
	}

	// @Override
	// public Ast visitTArray(napParser.TArrayContext ctx) {
	//     return new TypArray(BasicType);
	// }

	// @Override
	// public Ast visitDeclaration(napParser.DeclarationContext ctx) {
	//     String name = visit(ctx.Identifier()).toString();
	//     Optional<Expression> val = (Optional<Expression>) ctx.expr();
	//     Type type = (Type) visit(ctx.Type());
	//     return new Declaration(position(ctx), name, type, val);
    // }

    public Ast visitStmAssign(napParser.IAssignContext ctx) {
        String var = ctxf.Identifier().toString();
        Expression exp = (Expression) visit(ctx.expr());
        return new StmAssign(position(ctx), var, exp);
    }

    public Ast visitStmExp(napParser.IExprContext ctx) {
        Expression exp = (Expression) visit(ctx.expr());
        return new StmExp(position(ctx), exp);
    }

    public Ast visitStmIf(napParser.IIfContext ctx) {
        Expression condition = (Expression) visit(ctx.expr());
        Block then_branch = (Block) visit(ctx.block(0));
        Block else_branch = (Block) visit(ctx.block(1));
        return new StmIf(position(ctx), condition,
                then_branch, else_branch);
    }

    public Ast visitStmPrint(napParser.IPrintContext ctx) {
        TypBasic type = (TypBasic) visit(ctx.type());
        Expression exp = (Expression) visit(ctx.expr());
        return new StmPrint(position(ctx), type, exp);
    }

    public Ast visitStmRead(napParser.IInputContext ctx) {
        Type type = (Type) visit(ctx.type());
        Expression exp = (Expression) visit(ctx.expr());
        return new StmRead(position(ctx), type, exp);
    }

    public Ast visitStmReturn(napParser.IReturnContext ctx) {
        Expression exp = (Expression) visit(ctx.expr());
        return new StmReturn(position(ctx), exp);
    }

      public Ast visitStmWhile(napParser.IWhileContext ctx) {
          Expression condition = (Expression) visit(ctx.expr());
          Block body = (Block) visit(ctx.block());
     	Bool doWhile = (Bool) visit(ctx.bool());
          return new StmWhile(position(ctx), condition, body, doWhile);
      }

    public Ast visitEArrAccess(napParser.EArrayAccessContext ctx) {
        Expression array = (Expression) visit(ctx.expr(0));
        Expression index = (Expression) visit(ctx.expr(1));
        return new EArrAccess(position(ctx), array, index);
    }

    public Ast visitEArrayEnum(napParser.EArrayEnumerationContext ctx) {
        List<Expression> exps = new ArrayList<>();
        for (ExprContext ec : ctx.expr())
            exps.add((Expression) visit(ec));
        return new ArrayEnum(position(ctx), exps);
    }

    public Ast visitEAssignop(napParser.IAssignContext ctx) {
        Expression exp = (Expression) visit(ctx.expr());
        boolean prefix = (boolean) visit(ctx.bool());
        return new EAssignop(position(ctx), OpBinary.EQ, exp, prefix);
    }

    public Ast visitEOpAnd(napParser.EAndContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        return new ExpBinop(position(ctx), left, OpBinary.AND, right);
    }

    public Ast visitEOpOr(napParser.EOrContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        return new ExpBinop(position(ctx), left, OpBinary.OR, right);
    }

    public Ast visitEOpCmp(napParser.ECmpContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        OpBinary cmp = OpBinary.EQ;
        switch (ctx.op.getType()) {
            case napLexer.NEQ:
                cmp = OpBinary.NEQ;
                break;
            case napLexer.EQ:
                cmp = OpBinary.EQ;
                break;
            case napLexer.LT:
                cmp = OpBinary.LT;
                break;
            case napLexer.GT:
                cmp = OpBinary.GT;
                break;
            case napLexer.LE:
                cmp = OpBinary.LE;
                break;
            case napLexer.GE:
                cmp = OpBinary.GE;
                break;
        }
        return new ExpBinop(position(ctx), left, cmp, right);
    }

    public Ast visitEOpMuls(napParser.EMulsContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        OpBinary op = null;
        switch (ctx.op.getType()) {
            case napLexer.DIV:
                op = OpBinary.DIV;
                break;
            case napLexer.MOD:
                op = OpBinary.MOD;
                break;
            case napLexer.MUL:
                op = OpBinary.MUL;
        }
        return new ExpBinop(position(ctx), left, op, right);
    }

    public Ast visitEOpAdds(napParser.EAddsContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        OpBinary op = null;
        switch (ctx.op.getType()) {
            case napLexer.SUB:
                op = OpBinary.SUB;
                break;
            case napLexer.ADD:
                op = OpBinary.ADD;
                break;
        }
        return new ExpBinop(position(ctx), left, op, right);
    }

    @Override
    public Ast visitEBool(napParser.EBoolContext ctx) {
        return new ExpBool(position(ctx),
			  Boolean.parseBoolean(ctx.BConstant().toString()));
    }


    @Override
    public Ast visitEChar(napParser.ECharContext ctx) {
        return new ExpChar(position(ctx),
			  Character.parseCharacter(ctx.CConstant().toString()));
    }

    public Ast ExpFuncCall(napParser.ECallContext ctx) {
        String name = visit(ctx.Identifier()).toString();
        List<Expression> arguments = new ArrayList<>();
        for (ExprContext ec : ctx.expr())
            arguments.add((Expression) visit(ec));
        return new FuncCall(position(ctx), name, arguments);
    }

    @Override
    public Ast visitEInt(napParser.EIntContext ctx) {
        return new ExpInt(position(ctx),
			  Integer.parseInt(ctx.IConstant().toString()));
    }

    // @Override
    // public Ast visitExpLength(napParser.EIntContext ctx) {
    //     Expression exp = (Expression) visit(ctx.expr());
    //     return new ExpLength(position(ctx), exp);
    // }

    @Override
    public Ast visitENew(napParser.ENewContext ctx) {
        Expression exp = (Expression) visit(ctx.expr());
        TypBasic type = (TypBasic) visit(ctx.typbasic());
        return new ENew(position(ctx),type,  exp);
    }

    @Override
    public Ast visitEString(napParser.EStringContext ctx) {
        return new ExpString(position(ctx), ctx.StringConstant);
    }

    public Ast visitEOpNot(napParser.ENotContext ctx) {
        Expression exp = (Expression) visit(ctx.expr());
        return new ExpUnop(position(ctx), exp, OpUnary.NOT);
    }

    // public Ast visitEOpMin(napParser.EMinContext ctx) {
    //     Expression exp = (Expression) visit(ctx.expr());
    //     return new ExpUnop(position(ctx), exp, OpUnary.SUB);
    // }

    public Ast visitEVar(napParser.EIdentifierContext ctx) {
        return new ExpVar(position(ctx), ctx.Identifier().toString());
    }

}
