// package compiler;
// import ast.*; import java.util.*;
//
// public class TypeChecker implements Visitor<Type>
// {
//     private Map<String, Type> typeEnvironment;
//     private List<String> errors;
//
//     public TypeChecker(){
// 	typeEnvironment = new HashMap<>();
// 	errors = new ArrayList<>();
//     }
//     public boolean hasErrors(){ return !errors.isEmpty(); }
//     public List<String> errors() { return errors; }
//     // return the data structure needed for the interpreter
//     public Map<String, TypBasic> typeEnvironment(){
// 	if (hasErrors())
// 	    throw new Error("Type checked failed");
// 	else {
// 	    Map<String, TypBasic> te = new HashMap<>();
// 	    for(Map.Entry<String, Type> kv : typeEnvironment.entrySet())
// 		te.put(kv.getKey(), kv.getValue().get());
// 	    return te;
// 	}
//     }
//     @Override
// 	// TODO: public Type visit(ExpArrAccess exp){}
// 	@Override
// 	// TODO: public Type visit(ExpArrEnum exp){}
// 	@Override
// 	// TODO: public Type visit(ExpAssignop exp){}
// 	@Override
// 	// TODO: public Type visit(ExpCallConversion exp){}
// 	@Override
// 	// TODO: public Type visit(ExpChar exp){}
// 	@Override
// 	// TODO: public Type visit(ExpFuncCall exp){}
// 	@Override
// 	// TODO: public Type visit(ExpLength exp){}
// 	@Override
// 	// TODO: public Type visit(ExpNew exp){}
// 	@Override
// 	// TODO: public Type visit(ExpString exp){}
// 	@Override
// 	// TODO: public Type visit(StmDecl stm){}
// 	@Override
// 	// TODO: public Type visit(StmExp stm){}
// 	@Override
// 	// TODO: public Type visit(StmFor stm){}
// 	@Override
// 	// TODO: public Type visit(StmReturn stm){}
// 	@Override
// 	// TODO: public Type visit()
//
//     @Override
//     public Type visit(ExpUnop exp) {
// 	Type type = exp.exp.accept(this);
// 	Signature signature = Signatures.unary.get(exp.op);
// 	if (!signature.check(type))
// 	    errors.add("At "+ exp.pos + ": argument has type "
// 		       + type + " but type " + signature.argTypes.get(0)
// 		       + " is expected.");
//         return signature.returnType;
//     }
//     @Override
//     public Type visit(ExpBinop exp) {
// 	Type left  = exp.left.accept(this);
// 	Type right = exp.right.accept(this);
// 	// Special case for EQ and NEQ because of polymorphism
// 	if (exp.op == OpBinary.EQ || exp.op == OpBinary.NEQ){
// 	    if (left != right)
// 		errors.add("Expression at " + exp.left.pos
// 			   + " should have the same type "
// 			   + "than expression at "
// 			   + exp.right.pos + ".");
// 	    return Type.bool;
// 	}
// 	Signature signature = Signatures.binary.get(exp.op);
// 	if (!signature.check(left, right))
// 	    errors.add("At "+ exp.pos + ": arguments have types "
// 		       + left + " and " + right + " but types "
// 		       + signature.argTypes.get(0) + " and "
// 		       + signature.argTypes.get(1)
// 		       + " are expected.");
// 	return signature.returnType;
//     }
//
//     @Override
//     public Type visit(ExpInt num) {
// 	return Type.integer;
//     }
//     @Override
//     public Type visit(ExpBool bool) {
// 	return Type.bool;
//     }
//     private Type checkDeclared(String var, Position pos){
// 	Type type = typeEnvironment.get(var);
// 	if (type == null){
// 	    errors.add("At " + pos + " variable "
// 		       + var + " is undeclared.");
// 	    return Type.none;
// 	}
// 	return type;
//     }
//     @Override
//     public Type visit(ExpVar var) {
// 	return checkDeclared(var.name, var.pos);
//     }
//
//     @Override
//     public Type visit(StmRead stm) {
// 	checkDeclared(ins.var, ins.pos);
// 	return Type.none;
//     }
//     @Override
//     public Type visit(StmPrint stm) {
// 	ins.exp.accept(this);
// 	return Type.none;
//     }
//     @Override
//     public Type visit(StmIf stm) {
// 	if (ins.exp.accept(this) != Type.bool)
// 	    errors.add("At " + ins.exp.pos + " an expression "
// 		       + "of type boolean is expected.");
// 	ins.then_branch.accept(this);
// 	ins.else_branch.accept(this);
// 	return Type.none;
//     }
//     @Override
//     public Type visit(StmWhile stm) {
// 	if (ins.exp.accept(this) != Type.bool)
// 	    errors.add("At " + ins.exp.pos +
// 		       " an expression of type boolean is expected.");
// 	ins.body.accept(this);
// 	return Type.none;
//     }
//     @Override
//     public Type visit(StmAssign stm) {
// 	Type e_type = ins.exp.accept(this);
// 	Type v_type = checkDeclared(ins.var, ins.pos);
// 	if (v_type != e_type)
// 	    errors.add("At " + ins.pos + " variable "
// 		       + ins.var + " has type " + v_type
// 		       + " but is assigned a value of type "
// 		       + e_type + ".");
// 	return Type.none;
//     }
//     @Override
//     public Type visit(TypBasic type) {
// 	switch(type.type){
// 	case INTEGER: return Type.integer;
// 	case BOOLEAN: return Type.bool;
// 	// TODO: add other types
// 	}
//         return Type.none;
//     }
//
//     @Override
//     public Type visit(Block block) {
// 	for(Ins instruction: block.instructions)
// 	    instruction.accept(this);
// 	return Type.none;
//     }
//
//     @Override
//     public Type visit(Program program) {
//         // TODO: implement
// 	return Type.none;
//     }
//
//     @Override
//     public Type visit()
// }
