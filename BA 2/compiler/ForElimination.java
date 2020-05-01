package compiler;

import ast.*;
import java.util.ArrayList;
import java.util.List;

public class ForElimination {
	public static Program transform(Program program){
         Transformer transformer = new Transformer();
         return (Program) program.accept(transformer);
     }

     private static class Transformer extends ast.VisitorCopy{

         private List<Statement> before;
         private List<Statement> after;
	    Generator generator = new Generator("ForToWhile");

         @Override
         public Ast visit(StmFor stm) {
		    	// make if statement block
		    	List<Statement> ifStmList = new ArrayList<Statement>();
		    	// counter and type var from for loop
		    	String counterName = generator.getNew();
			Statement counterStm = new StmDecl(stm.pos,
		    						counterName,
								new ast.Type(stm.pos, type.Basic.INT),
								new ExpInt(stm.pos, 0) );
			ExpVar counterVar = new ExpVar(stm.pos, counterName);
		    	ifStmList.add( counterStm );
			ifStmList.add( new StmDecl(stm.pos,
								stm.var,
								new ast.Type(stm.pos, stm.type.type) ) );

			// make custom while block with counter increment and variable assignment,
			// and inject statements from "for" block
			List<Statement> whileStmList = new ArrayList<Statement>();
			whileStmList.add( new StmAssign(stm.pos,
								new ExpVar(stm.pos, stm.var),
								new ExpArrAccess(stm.pos, stm.collection,
									new ExpVar(stm.pos, counterName)) ) );

			for( Statement statement: stm.body.statements ) {
				whileStmList.add( statement );
			}

			whileStmList.add( new StmAssign(stm.pos,
								new ExpVar(stm.pos, counterName),
								new ExpInt(stm.pos, 1),
								OpBinary.ADD) );

			Block whileStmBlock = new Block(stm.pos, whileStmList);

			// create while with custom block
			List<Expression> collectionList = new ArrayList<Expression>();
			collectionList.add( stm.collection );
			StmWhile stmWhile = StmWhile.While( stm.pos,
			 					new ExpBinop( stm.pos,
									counterVar,
									OpBinary.LT,
									new ExpPredefinedCall(stm.pos,
										OpPredefined.LENGTH,
										collectionList) ),
								whileStmBlock );

			ifStmList.add(stmWhile);

			Block ifStmBlock = new Block(stm.pos, ifStmList);

			// create if statement for work around
			StmIf tempIfStm = new StmIf( stm.pos, new ExpBool(stm.pos, true),
									ifStmBlock );

			// accept if statement and return
			return tempIfStm.accept(this);
         }
     }
}
