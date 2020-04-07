package compiler;

import ast.*;
import java.util.Optional;
import java.lang.NoSuchFieldException;

public class TypeChecker extends ErrorList implements ast.Visitor<Optional<type.Type>> {
	private SymbolTable symbolTable;
	private VisitedBlocks visitedBlocks;

	TypeChecker(SymbolTable symbolTable) {
		super();
		this.symbolTable = symbolTable;
		this.visitedBlocks = new VisitedBlocks();
	}

	private Boolean isAssignable(Expression exp) {
		Boolean isAssignable = false;

		// chech if the expression is assignable and set to return true if it is
		if(exp instanceof ExpVar ) {
			if(symbolTable.varLookup(((ExpVar)exp).name, visitedBlocks).isPresent()){
				isAssignable = true;
			}
		}

		return isAssignable;
	}

	public Optional<type.Type> visit(ExpBool exp) {
		return Optional.of(type.Basic.BOOL);
	}

	public Optional<type.Type> visit(ExpChar exp){
		return Optional.of(type.Basic.CHAR);
	}

	public Optional<type.Type> visit(ExpInt exp){
		return Optional.of(type.Basic.INT);
	}

	public Optional<type.Type> visit(ExpString exp){
		return Optional.of(new type.Array(type.Basic.CHAR));
	}

	public Optional<type.Type> visit(ExpVar exp){
		return Optional.of(symbolTable.varLookup(exp.name, visitedBlocks).get());
	}

	public Optional<type.Type> visit(ExpBinop exp){
		// visit left hand side of Expression
		type.Type lftType = exp.left.accept(this).get();

		// visit right hand side of expression
		type.Type rtType = exp.right.accept(this).get();

		// compare types taking into account of the operator
		if(exp.op == OpBinary.ADD
			|| exp.op == OpBinary.SUB
			|| exp.op == OpBinary.MUL
			|| exp.op == OpBinary.DIV
			|| exp.op == OpBinary.MOD){
			// arithmetic, needs type int (or float if implemented)
			if(lftType != type.Basic.INT){
				errors.add(exp.pos + " left hand side of arithmetic"
					+ " expression must evaluate to a type int");
			}
			if(rtType != type.Basic.INT){
				errors.add(exp.pos + " right hand side of arithmetic"
					+ " expression must evaluate to a type int");
			}
			return Optional.of(type.Basic.INT);
		}
		else if(exp.op == OpBinary.LT
			|| exp.op == OpBinary.GT
			|| exp.op == OpBinary.LE
			|| exp.op == OpBinary.GE){
			// comparison (allowing for: int, char?)
			// TODO: Are we able to compare characters?
			if(lftType != type.Basic.INT){
				errors.add(exp.pos + " left hand side of comparison only"
					+ " allows for types: int");
			}
			if(rtType != type.Basic.INT){
				errors.add(exp.pos + " right hand side of comparison only"
					+ " allows for types: int");
			}
			// TODO: add compare rhs with lhs if chars allowed

			return Optional.of(type.Basic.BOOL);
		}
		else if(exp.op == OpBinary.NEQ
			|| exp.op == OpBinary.EQ){
			// comparison (allowing for: boolean, int, char)
			if(lftType != type.Basic.INT
				&& lftType != type.Basic.BOOL
				&& lftType != type.Basic.CHAR){
				errors.add(exp.pos + " left hand side of comparison only"
					+ " allows for types: bool, int, char");
			}
			if(lftType != type.Basic.INT
				&& lftType != type.Basic.BOOL
				&& lftType != type.Basic.CHAR){
				errors.add(exp.pos + " right hand side of comparison only"
					+ " allows for types: bool, int, char");
			}
			// TODO: comparison of int with char
			// compare rhs and lhs

			return Optional.of(type.Basic.BOOL);
		}
		else{
			// logical
			// comparison (allowing for only bool types)
			if(lftType != type.Basic.BOOL){
				errors.add(exp.pos + " left hand side of logical Expression"
				 	+ " only allows for types: bool");
			}
			if(rtType != type.Basic.BOOL){
				errors.add(exp.pos + " right hand side of logical Expression"
				 	+ " only allows for types: bool");
			}
			return Optional.of(type.Basic.BOOL);
		}
	}

	public Optional<type.Type> visit(ExpUnop exp){
		return null;
	}

	public Optional<type.Type> visit(ExpAssignop exp){
		// check if of type compatible with "++" and "--"
		// type int (or float if being implemented)
		type.Type expType = exp.exp.accept(this).get();
		if(expType != type.Basic.INT) {
			errors.add(exp.pos + " invalid type to apply increment or"
				+ " decrement to");
		}
		return Optional.of(expType);
	}

	public Optional<type.Type> visit(ExpFuncCall exp){
		// find argument types from symbol table
			// check all argument types
		// find return type from symbol table
			// check return type
		return null;
	}

	public Optional<type.Type> visit(ExpPredefinedCall exp){
		return null;
	}

	public Optional<type.Type> visit(ExpNew exp){
		return null;
	}

	public Optional<type.Type> visit(ExpArrAccess array){
		return null;
	}

	public Optional<type.Type> visit(ExpArrEnum array){

		return null;
	}

	public Optional<type.Type> visit(StmIf stm){
		// check that test expression evaluates to a boolean type
		type.Type rtnType = stm.condition.accept(this).get();

		if(rtnType != type.Basic.BOOL){
			// add error to error error list
			errors.add(stm.pos + " Expression in conditional does not"
					+ "evaluate to type bool");
		}

		// check inside block
		stm.then_branch.accept(this);

		// check else block if exists
		if(stm.else_branch.isPresent()){
			stm.else_branch.get().accept(this);
		}

		return null;
	}

	public Optional<type.Type> visit(StmAssign stm){
		// check that lValue is assignable
		// it needs to be an identifier, evaluate to an identifier,
		// or be an array index
		Boolean isAssignable = isAssignable(stm.lValue);

		if(isAssignable) {
			// check lValue and "r-value" have same types
			// if op is present, both types must be int
			// (or float, but not implementing)
			if(stm.op.isPresent()) {
				// check if right hand side is of type usable in +=, -=, /=, */
				if(!(stm.exp instanceof ExpInt)) {
					errors.add(stm.pos + " right hand side of assignment"
						+ " is not of type int");
				}
				// check if correct assignable type for +=, -=, /=, */
				if(symbolTable.varLookup(((ExpVar)stm.lValue).name, visitedBlocks).get()
											!= type.Basic.INT ) {
					errors.add(stm.pos + " left hand side of assignment"
						+ " is not of type int");
				}
			}

			// possibly a switch statement for the type of the right hand side
			// if(symbolTable.varLookup(stm.lValue, visitedBlocks).get()) {
			// 	errors.add(stm.pos + " mismatched assignment types");
			// }
		}

		return null;
	}

	public Optional<type.Type> visit(StmExp stm){
		return null;
	}

	public Optional<type.Type> visit(StmRead stm){
		return null;
	}

	public Optional<type.Type> visit(StmPrint stm){
		return null;
	}

	public Optional<type.Type> visit(StmReturn stm){
		return null;
	}

	public Optional<type.Type> visit(StmWhile stm){
		return null;
	}

	public Optional<type.Type> visit(StmFor stm){
		return null;
	}

	public Optional<type.Type> visit(StmDecl stm){
		return null;
	}

	public Optional<type.Type> visit(Type type){
		return null;
	}

	public Optional<type.Type> visit(Block block){
		// push current block
		visitedBlocks.enter(block);

		// iterate through statements and check
		for(Statement stm : block.statements){
			stm.accept(this);
		}

		// pop block
		visitedBlocks.exit();

		return null;
	}

	public Optional<type.Type> visit(FunctionDefinition fun){
		// check inside block
		fun.body.accept(this);
		return null;
	}

	public Optional<type.Type> visit(Program program){
		// loop through function definitions
		for(FunctionDefinition funcDef : program.functions) {
			funcDef.accept(this);
		}
		return null;
	}

}
