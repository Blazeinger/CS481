package compiler;

import ast.*;
import java.util.Optional;
import java.lang.NoSuchFieldException;
import java.util.Stack;

public class TypeChecker extends ErrorList implements ast.Visitor<Optional<type.Type>> {
	private SymbolTable symbolTable;
	private VisitedBlocks visitedBlocks;
	private Stack<Optional<type.Type>> visitedFunctions;

	TypeChecker(SymbolTable symbolTable) {
		super();
		this.symbolTable = symbolTable;
		this.visitedBlocks = new VisitedBlocks();
		visitedFunctions = new Stack<Optional<type.Type>>();
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
		// get op for expression and type of expression
		OpUnary op = exp.op;
		type.Type expType = exp.exp.accept(this).get();

		// if op is subtract, expression must evaluate to an int
		// (or a float if being implemented)
		if(op == OpUnary.SUB){
			if(expType != type.Basic.INT){
				errors.add(exp.pos + " cannot subtract a non-int type");
			}
			return Optional.of(expType);
		}

		// if op is not (negate), expression must evaluate to a boolean
		else{
			if(expType != type.Basic.BOOL){
				errors.add(exp.pos + " cannot negate a non-bool type");
			}
			return Optional.of(expType);
		}
	}

	public Optional<type.Type> visit(ExpAssignop exp){
		// check if of type compatible with "++" and "--"
		// type int (or float if being implemented)
		type.Type expType = exp.exp.accept(this).get();
		if(expType != type.Basic.INT) {
			errors.add(exp.pos + " cannot increment or decrement a non-int type");
		}
		return Optional.of(expType);
	}

	public Optional<type.Type> visit(ExpFuncCall exp){
		// find argument types from symbol table
		Optional<Signature> funcSigOpt = symbolTable.funcLookup(exp.funcName);
		type.Type returnType = null;

		if(funcSigOpt.isPresent()){
			// pull signature out of Optional
			Signature funcSig = funcSigOpt.get();

			// find return type
			Optional<type.Type> rtOpt = funcSig.returnType;
			if(rtOpt.isPresent()){
				returnType = rtOpt.get();
			}

			// check all argument types
			// account for number of arguments and type of arguments
			if(exp.arguments.size() == funcSig.argTypes.size()){
				// check for same number of elements
				for(int index = 0; index < exp.arguments.size(); index++){
					// compare argument in exp to argument in signature
					if(exp.arguments.get(index).accept(this).get()
						!= funcSig.argTypes.get(index).getFst()){
							errors.add(exp.pos + "mismatch of argument"
								+ " types");
					}
				}
				// number of args not the same
				errors.add(exp.pos + " number of arguments in function call"
					+ " does not match number of arguments in function"
					+ " declaraction");
				return Optional.of(returnType);

			}

			// return the function's return type
			return Optional.of(returnType);
		}

		return Optional.empty();
	}

	public Optional<type.Type> visit(ExpPredefinedCall exp){
		// find argument types from symbol table
		Optional<Signature> funcSigOpt
						= symbolTable.funcLookup(exp.funcName.toString());
		type.Type returnType = null;

		if(funcSigOpt.isPresent()){
			// pull signature out of Optional
			Signature funcSig = funcSigOpt.get();

			// find return type
			Optional<type.Type> rtOpt = funcSig.returnType;
			if(rtOpt.isPresent()){
				returnType = rtOpt.get();
			}

			// check all argument types
			// account for number of arguments and type of arguments
			if(exp.arguments.size() == funcSig.argTypes.size()){
				// check for same number of elements
				for(int index = 0; index < exp.arguments.size(); index++){
					// compare argument in exp to argument in signature
					if(exp.arguments.get(index).accept(this).get()
						!= funcSig.argTypes.get(index).getFst()){
							errors.add(exp.pos + "mismatch of argument"
								+ " types");
					}
				}
				// number of args not the same
				errors.add(exp.pos + " number of arguments in function call"
					+ " does not match number of arguments in function"
					+ " declaraction");
				return Optional.of(returnType);

			}

			// return the function's return type
			return Optional.of(returnType);
		}

		return Optional.empty();
	}

	public Optional<type.Type> visit(ExpNew exp){
		// check that expression in new constructor evaluates to the same type
		// as the type specified
		type.Type expType = exp.exp.accept(this).get();

		if(expType != exp.type.type){
			errors.add(exp.pos + " mismatch of types in new statement");
		}

		return Optional.of(expType);
	}

	public Optional<type.Type> visit(ExpArrAccess array){
		// check if index is of type int
		if(array.index.accept(this).get() != type.Basic.INT){
			errors.add(array.pos + " cannot access array index of non-int"
				+ " type");
		}
		return Optional.of(array.array.accept(this).get()) ;
	}

	public Optional<type.Type> visit(ExpArrEnum array){
		// if enumeration is not empty, check that all types are the same
		// within the enumeration
		if(!array.exps.isEmpty()){
			// compare all elements
			type.Type firstElemType = array.exps.get(0).accept(this).get();
			for(Expression exp : array.exps){
				if(exp.accept(this).get() != firstElemType){
					errors.add(array.pos + " element cannot be assigned to"
						+ " array enumeration of type "
						+ firstElemType.toString());
					Optional.empty();
				}
			}
			return Optional.of(firstElemType);
		}
		// array enumeration is empty, record error
		else{
			errors.add(array.pos + " cannot enumerate an empty array");
			return Optional.empty();
		}
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
		type.Type rhsType = stm.exp.accept(this).get();
		type.Type lhsType = stm.lValue.accept(this).get();
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
				if(!(rhsType == type.Basic.INT)) {
					errors.add(stm.pos + " right hand side of assignment"
						+ " is not of type int");
				}
				// check if correct assignable type for +=, -=, /=, */
				if(lhsType != type.Basic.INT ) {
					errors.add(stm.pos + " left hand side of assignment"
						+ " is not of type int");
				}
			}

			// compare rhs type and lhs type
			if(rhsType != lhsType){
				errors.add(stm.pos + "cannot assign type " + rhsType + " to"
					+ " type " + lhsType);
			}
		}
		else{
			errors.add(stm.pos + " left-hand side of statement is not assignable");
		}

		return null;
	}

	public Optional<type.Type> visit(StmExp stm){
		return stm.exp.accept(this);
	}

	public Optional<type.Type> visit(StmRead stm){
		// check exp is assignable and matches type with the type
		if(stm.exp.accept(this).get() == stm.type.type){
			if(!isAssignable(stm.exp)){
				errors.add(stm.pos + "given expression is not assignable");
			}
		}
		else{
			errors.add(stm.pos + " mismatched expression type and read type");
		}
		return null;
	}

	public Optional<type.Type> visit(StmPrint stm){
		// check exp is assignable and matches type with the type
		if(stm.exp.accept(this).get() == stm.type.type){
			if(!isAssignable(stm.exp)){
				errors.add(stm.pos + "given expression is not assignable");
			}
		}
		else{
			errors.add(stm.pos + " mismatched expression type and print type");
		}
		return null;
	}

	public Optional<type.Type> visit(StmReturn stm){
		// check type of exp is the same as the current func return type
		Optional<type.Type> rtnOpt = stm.exp.accept(this);
		if(visitedFunctions.peek().isPresent()){
			if(rtnOpt != visitedFunctions.peek()){
				errors.add(stm.pos + " invalid return type for defined function");
			}
		}
		else{
			if(rtnOpt.isPresent()){
				errors.add(stm.pos + " function of no return type cannot"
					+ " return type " + rtnOpt.get());
			}
		}
		return rtnOpt;
	}

	public Optional<type.Type> visit(StmWhile stm){
		// check condition evaluates to a bool
		if(stm.condition.accept(this).get() != type.Basic.BOOL){
			errors.add(stm.pos + " condition expression does not evaluate to"
				+ " a type of bool");
		}

		// visit the body
		stm.body.accept(this);

		return null;
	}

	public Optional<type.Type> visit(StmFor stm){
		// check for collection is of type array
		type.Type collection = stm.collection.accept(this).get();
		if(collection instanceof type.Array) {
		// check type of array against type of for var
		// TODO: determine iterating through collections (allow for range of int)
			if(((type.Array)collection).type != stm.type.type){
				errors.add(stm.pos + "variable type does not match type in"
					+ " collection");
			}
		}
		else{
			// not of type array
			errors.add(stm.pos + "collection expression is not of type array");
		}
		// visit body
		stm.body.accept(this);

		return null;
	}

	public Optional<type.Type> visit(StmDecl stm){
		// if initialization is present, then visit
		if(stm.initialization.isPresent()){
			stm.initialization.get().accept(this);
		}
		return null;
	}

	public Optional<type.Type> visit(Type type){
		return Optional.of(type.type);
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
		// push onto visited visitedFunctions
		if(fun.returnType.isPresent()){
			visitedFunctions.push(Optional.of(fun.returnType.get().type));
		}
		else{
			visitedFunctions.push(Optional.empty());
		}

		// check inside block
		fun.body.accept(this);

		// pop from visitedFunctions
		visitedFunctions.pop();

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
