package compiler;

import ast.*;
import parser.*;
import parser.napParser.*;

import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class BuildAST extends W2BaseVisitor<Ast> {

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

	@Override
	public Ast visitFunctionDefinition(napParser.ProgramContext ctx) {
	    String name = (String) visit(ctx.Identifier());
	    Block body = (Block) visit(ctx.block());
	    List<Pair<Pair<String, Type>, Boolean>> arguments = new ArrayList<>();
	    for (ParametersContext pc : ctx.parameters())
	    		arguments.add((Parameter) visit(pc));
	    return new FunctionDefinition(position(ctx), name, arguments, body);
	}

	@Override
	public Ast visitBlock(napParser.BlockContext ctx) {
	  List<Statement> statements = new ArrayList<>();
	  for (StatementContext stm : ctx.statement())
	      statements.add((Statement) stm.accept(this));
	  return new Block(statements);
	}

	@Override
	public Ast visitTINT(napParser.TINTContext ctx) {
	   return new Typ(TypBasic.INTEGER);
	}

	@Override
	public Ast visitTBOOL(napParser.TBOOLContext ctx) {
	   return new Typ(TypBasic.BOOLEAN;
	}

	@Override
	public Ast visitTCHAR(napParser.TCHARContext ctx) {
	    return new Typ(TypBasic.CHARACTER);
	}

	@Override
	public Ast visitTFLOAT(napParser.TFLOATContext ctx) {
	    return new Typ(TypBasic.FLOAT);
	}

	@Override
	public Ast visitTBYTE(napParser.TBYTEContext ctx) {
	    return new Typ(TypBasic.BYTE);
	}

	@Override
	public Ast visitTARRAY(napParser.TARRAYContext ctx) {
	    return new TypArr(TypBasic.ARRAY);
	}

	@Override
	public Ast visitDeclaration(napParser.StatementContext ctx) {
	    String name = (String) visit(ctx.Identifier());
	    Optional<Expression> val = (Expression) ctx.expression;
	    Type type = (Type) visit(ctx.Type());
	    return new Declaration(position(ctx), name, type, val);
    }

    @Override
    public Ast visitStmAssign(napParser.IAssignContext ctx) {
        String var = ctx.Identifier().toString();
        Exp exp = (Exp) visit(ctx.expr());
        return new StmAssign(position(ctx), var, exp);
    }

    @Override
    public Ast visitStmExp(napParser.IInputContext ctx) {
        Exp exp = (Exp) visit(ctx.expr());
        return new visitSTMExp(position(ctx), exp);
    }

    @Override
    public Ast visitStmIf(napParser.IIfContext ctx) {
        Exp condition = (Exp) visit(ctx.expr());
        Block then_branch = (Block) visit(ctx.block(0));
        Block else_branch = (Block) visit(ctx.block(1));
        return new StmIf(position(ctx), condition,
                then_branch, else_branch);
    }

    @Override
    public Ast visitStmPrint(napParser.IPrintContext ctx) {
        Type type = (Type) visit(ctx.type());
        Exp exp = (Exp) visit(ctx.expr());
        return new StmPrint(position(ctx), type, exp);
    }

    @Override
    public Ast visitStmRead(napParser.IInputContext ctx) {
        Type type = (Type) visit(ctx.type());
        Exp exp = (Exp) visit(ctx.expr());
        return new StmRead(position(ctx), type, exp);
    }

    @Override
    public Ast visitStmReturn(napParser.IReturnContext ctx) {
        Exp exp = (Exp) visit(ctx.expr());
        return new visitSTMReturn(position(ctx), exp);
    }

      @Override
      public Ast visitSTMWhile(napParser.IWhileContext ctx) {
          Exp condition = (Exp) visit(ctx.expr());
          Block body = (Block) visit(ctx.block());
          boolean doWhile = (boolean) visit(ctx.boolean());
          return new visitSTMWhile(position(ctx), condition, body, doWhile);
      }

    @Override
    public Ast visitEId(napParser.EIdentifierContext ctx) {
        return new ExpVar(position(ctx), ctx.Identifier().toString());
    }

    @Override // TODO: Find Context
    public Ast visitEOpNeg(napParser.EOpNegContext ctx) {
        Exp exp = (Exp) visit(ctx.expr());
        return new ExpUnop(position(ctx), OpUnary.NOT, exp);
    }

    @Override // TODO: Find Context
    public Ast visitEOpMin(napParser.EOpMinContext ctx) {
        Exp exp = (Exp) visit(ctx.expr());
        return new ExpUnop(position(ctx), OpUnary.MINUS, exp);
    }

    @Override
    public Ast visitEOpOr(napParser.EOrContext ctx) {
        Exp left = (Exp) visit(ctx.expr(0));
        Exp right = (Exp) visit(ctx.expr(1));
        return new ExpBinop(position(ctx), left, OpBinary.OR, right);
    }

    @Override
    public Ast visitEInt(napParser.EIntContext ctx) {
        return new ExpInt(position(ctx),
			  Integer.parseInt(ctx.IConstant().toString()));
	}

	@Override
	public Ast visitEBool(napParser.EBoolContext ctx) {
	   return new ExpBool(position(ctx),
			   Boolean.parseBoolean(ctx.BConstant().toString()));
	}

	@Override
	public Ast visitEOpAnd(napParser.EAndContext ctx) {
	   Exp left = (Exp) visit(ctx.expr(0));
	   Exp right = (Exp) visit(ctx.expr(1));
	   return new ExpBinop(position(ctx), left, OpBinary.AND, right);
	}

	@Override
	public Ast visitEOpOr(napParser.EOrContext ctx) {
	   Exp left = (Exp) visit(ctx.expr(0));
	   Exp right = (Exp) visit(ctx.expr(1));
	   return new ExpBinop(position(ctx), left, OpBinary.OR, right);
	}

	@Override
	public Ast visitEOpCmp(napParser.ECmpContext ctx) {
	   Exp left = (Exp) visit(ctx.expr(0));
	   Exp right = (Exp) visit(ctx.expr(1));
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

	@Override
	public Ast visitEPar(napParser.EParContext ctx) {
	   return (Exp) visit(ctx.expr());
	}

	@Override
	public Ast visitEOpMuls(napParser.EMulsContext ctx) {
	   Exp left = (Exp) visit(ctx.expr(0));
	   Exp right = (Exp) visit(ctx.expr(1));
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

	@Override
	public Ast visitEOpAdds(napParser.EAddsContext ctx) {
	   Exp left = (Exp) visit(ctx.expr(0));
	   Exp right = (Exp) visit(ctx.expr(1));
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
}
