package compiler;

import ast.*;
import java.util.*;
import javafx.util.*;

public class PrettyPrinter implements ast.Visitor<String> {
    private int baseIndent;
    private int currentIndent;

    private static String spaces(int n) {
        return String.join("",
                Collections.nCopies(n, " "));
    }

    public PrettyPrinter(int baseIndent) {
        this.baseIndent = baseIndent;
        this.currentIndent = 0;
    }

    @Override
    public String visit(ExpBinop exp) {
        return "(" + exp.left.accept(this)
                + " " + exp.op + " "
                + exp.right.accept(this) + ")";
    }

    @Override
    public String visit(ExpInt num) {
        return Integer.toString(num.value);
    }

    @Override
    public String visit(ExpVar var) {
        return var.name;
    }

	@Override
    public String visit(ExpBool bool) {
        return Boolean.toString(bool.value);
    }

    @Override
    public String visit(ExpChar chara) {
        return Character.toString(chara.value);
    }

    @Override
    public String visit(ExpString string) {
	return "\"" + string + "\"";
    }

    @Override
    public String visit(StmRead stm){
	String type = stm.type.accept(this);
	String exp = stm.exp.accept(this);
	return "read( " + type + ", " + exp + ")";
    }
    
    @Override
    public String visit(StmIf stm) {
        String condition = stm.condition.accept(this);
        String then_branch = stm.then_branch.accept(this);
	String result =
	    "if " + condition + " {\n"
	    + spaces(currentIndent) + then_branch;
	String else_branch = "";
	if (stm.else_branch.isPresent()){
	    else_branch = stm.else_branch.get().accept(this);
	    return result
		+ spaces(currentIndent) + "} else {\n" + else_branch
		+ spaces(currentIndent) + "}";
	} else {
	    return result + spaces(currentIndent) + "}";
	}
    }

    @Override
    public String visit(StmAssign stm) {
        String exp = stm.exp.accept(this);
        String var = stm.var;
	String assign = "=";
	Optional<OpBinary> op = stm.op;
	if (op.isPresent())
	    assign = op.get().toString() + assign;
        return var + " " + assign + " " + exp;
    }

    @Override
    public String visit(StmReturn stm) {
        String exp = stm.exp.accept(this);
        return "-> " + exp;
    }

    @Override
    public String visit(TypBasic type) {
        return type.type.toString();
    }

    @Override
    public String visit(TypArray type) {
	String element_type = type.type.accept(this);
        return "array<"+element_type+">";
    }

    @Override
    public String visit(Block block) {
        String result = "";
        currentIndent += baseIndent;
        for (Statement stm : block.statements)
            result += (spaces(currentIndent) + stm.accept(this) + "\n");
        currentIndent -= baseIndent;
        return result;
    }

    @Override
    public String visit(StmDecl decl) {
	String var = decl.binding.getKey();
        String type = decl.binding.getValue().accept(this);
	String result = "var " + type + " " + var;
	if (decl.initialization.isPresent())
	    return result + " = "
		+ decl.initialization.get().accept(this);
	return result;
    }

    @Override
    public String visit(FunctionDefinition fun) {
	String arguments = "";
	for(Pair<Pair<String, Type>, Boolean> arg : fun.arguments){
	    if (!arguments.equals("")) arguments += ", ";
	    String var = arg.getKey().getKey();
	    String type = arg.getKey().getValue().accept(this);
	    String ref = arg.getValue()?"ref ":"";
	    arguments += ref + type + " " + var;
	}
        String body = fun.body.accept(this) + spaces(currentIndent)+"}\n";
	String returnType = "";
	if (fun.returnType.isPresent())
	    returnType = " -> " + fun.returnType.get().accept(this);
        return "func "
	    + fun.name + "(" + arguments + ")" + returnType + " {\n"
	    + body;
    }

    @Override
    public String visit(Program program) {
        String functions = "";
        for(FunctionDefinition f : program.functions)
            functions += f.accept(this) + "\n";
        return functions;
    }
    @Override
    public String visit(StmFor forLoop) {
	String header = "for( " + forLoop.type.accept(this) + " " +
	    forLoop.var + " in " + forLoop.collection.accept(this) + " )";
	
	String block = "{\n" + spaces( currentIndent ) +
	    forLoop.body.accept(this) + "\n}";
	
	return header + block;
    }
    @Override
    public String visit(ExpUnop unaryExpr) {
	return unaryExpr.op.toString() + unaryExpr.exp.accept(this);
    }
    // TO DO //
    @Override
    public String visit(StmWhile stm){
	return "NOT IMPLEMENTED YET";
    }
    @Override
    public String visit(StmPrint stm){
	return "NOT IMPLEMENTED YET";
    }
    @Override
    public String visit(StmExp stm){
	return "NOT IMPLEMENTED YET";
    }
    @Override
    public String visit(ExpArrEnum exp){
	return "NOT IMPLEMENTED YET";
    }
    @Override
    public String visit(ExpArrAccess exp){
	return "NOT IMPLEMENTED YET";
    }

    @Override
    public String visit(ExpCallConversion exp){
        String con = exp.con.toString( );
        String expr = exp.exp.accept(this);
    return con+"("+expr+")";
    }

    @Override
    public String visit(ExpFuncCall exp){
        String funcName = exp.funcName;
        String arguments = "";
        for(int i = 0; i < exp.arguments.size(); i++) {
            if(!arguments.equals("")) arguments += ", ";
            String expr =  exp.arguments.get(i).accept(this);
            arguments += expr;
        }
    return funcName + "(" + arguments + ")";
    }

    @Override
    public String visit(ExpNew exp){
	return "NOT IMPLEMENTED YET";
    }

    @Override
    public String visit(ExpLength exp){
    return "length("+exp.exp.accept(this)+")";
    }

    @Override
    public String visit(ExpAssignop exp){
	return "NOT IMPLEMENTED YET";
    }
}
