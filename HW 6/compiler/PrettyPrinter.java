package compiler;

import ast.*;
import util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class PrettyPrinter implements ast.Visitor<String> {
    private int baseIndent;
    private int currentIndent;

    public PrettyPrinter(int baseIndent) {
        this.baseIndent = baseIndent;
        this.currentIndent = 0;
    }

    private static String spaces(int n) {
        return String.join("",
                Collections.nCopies(n, " "));
    }

    private static String deEscaping(String input) {
        return input.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\0", "\\0")
                .replace("\"", "\\\"")
                .replace("'", "\\'");
    }

    private static String parenthesis(String string) {
        return "(" + string + ")";
    }

    @Override
    public String visit(ExpUnop unaryExpr) {
        return parenthesis(unaryExpr.op.toString()
                + unaryExpr.exp.accept(this));
    }

    @Override
    public String visit(ExpBinop exp) {
        return parenthesis(exp.left.accept(this)
                + " " + exp.op + " "
                + exp.right.accept(this));
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
    public String visit(ExpChar exp) {
        return "'" + deEscaping(Character.toString(exp.value)) + "'";
    }

    @Override
    public String visit(ExpString string) {
        return "\"" + deEscaping(string.value) + "\"";
    }

    @Override
    public String visit(StmRead stm) {
        String type = stm.type.accept(this);
        String exp = stm.exp.accept(this);
        return "read( " + type + ", " + exp + ")";
    }

    @Override
    public String visit(StmIf stm) {
        String condition = parenthesis(stm.condition.accept(this));
        String then_branch = stm.then_branch.accept(this);
        String result =
                "if " + condition + " {\n" + then_branch;
        String else_branch;
        if (stm.else_branch.isPresent()) {
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
        String var = stm.l_value.accept(this);
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
    public String visit(Type type) {
        return type.type.toString();
    }

    @Override
    public String visit(Block block) {
        StringBuilder result = new StringBuilder();
        currentIndent += baseIndent;
        for (Statement stm : block.statements)
            result.append(spaces(currentIndent)).append(stm.accept(this)).append("\n");
        currentIndent -= baseIndent;
        return result.toString();
    }

    @Override
    public String visit(StmDecl decl) {
        String var = decl.binding.getFst();
        String type = decl.binding.getSnd().accept(this);
        String result = "var " + type + " " + var;
        return decl.initialization.map(expression -> result + " = "
                + expression.accept(this)).orElse(result);
    }

    @Override
    public String visit(FunctionDefinition fun) {
        StringBuilder arguments = new StringBuilder();
        for (Pair<Pair<String, Type>, Boolean> arg : fun.arguments) {
            if (!arguments.toString().equals("")) arguments.append(", ");
            String var = arg.getFst().getFst();
            String type = arg.getFst().getSnd().accept(this);
            String ref = arg.getSnd() ? "ref " : "";
            arguments.append(ref).append(type).append(" ").append(var);
        }
        String body = fun.body.accept(this) + spaces(currentIndent) + "}\n";
        String returnType = "";
        if (fun.returnType.isPresent())
            returnType = " -> " + fun.returnType.get().accept(this);
        return "func "
                + fun.name + "(" + arguments + ")" + returnType + " {\n"
                + body;
    }

    @Override
    public String visit(Program program) {
        StringBuilder functions = new StringBuilder();
        for (FunctionDefinition f : program.functions)
            functions.append(f.accept(this)).append("\n");
        return functions.toString();
    }

    @Override
    public String visit(StmFor forLoop) {
        String header = "for(" + forLoop.type.accept(this) + " " +
                forLoop.var + " in " + forLoop.collection.accept(this) + ")";
        String block = "{\n"
                + forLoop.body.accept(this)
                + spaces(currentIndent) + "}";
        return header + block;
    }

    @Override
    public String visit(StmWhile stm) {
        String block = stm.body.accept(this);
        String condition = parenthesis(stm.condition.accept(this));
        if (!stm.doWhile)
            return "while " + condition + " {\n"
                    + block
                    + spaces(currentIndent) + "}";
        else
            return "do {\n"
                    + block
                    + spaces(currentIndent) + "} "
                    + "while " + condition;
    }

    @Override
    public String visit(StmPrint stm) {
        return "print(" + stm.type.accept(this) + ", "
                + stm.exp.accept(this) + ")";
    }

    @Override
    public String visit(StmExp stm) {
        return stm.exp.accept(this);
    }

    @Override
    public String visit(ExpArrEnum exp) {
        StringBuilder expressions = new StringBuilder();
        for (int i = 0; i < exp.exps.size(); i++) {
            if (!expressions.toString().equals("")) expressions.append(", ");
            String expr = exp.exps.get(i).accept(this);
            expressions.append(expr);
        }
        return "{" + expressions + "}";
    }

    @Override
    public String visit(ExpArrAccess exp) {
        return exp.array.accept(this)
                + "[" + exp.index.accept(this) + "]";
    }

    @Override
    public String visit(ExpFuncCall exp) {
        String funcName = exp.funcName;
        return buildCall(funcName, exp.arguments);
    }

    @Override
    public String visit(ExpPredefinedCall exp) {
        String funcName = exp.funcName.toString();
        return buildCall(funcName, exp.arguments);
    }

    private String buildCall(String funcName, List<Expression> arguments) {
        StringBuilder argsString = new StringBuilder();
        for (int i = 0; i < arguments.size(); i++) {
            if (!argsString.toString().equals("")) argsString.append(", ");
            String expr = arguments.get(i).accept(this);
            argsString.append(expr);
        }
        return funcName + "(" + argsString + ")";
    }

    @Override
    public String visit(ExpNew exp) {
        return "new(" + exp.type.accept(this)
                + ", " + exp.exp.accept(this)
                + ")";
    }

    @Override
    public String visit(ExpAssignop exp) {
        String expString = exp.exp.accept(this);
        String opString = exp.op.toString();
        if (exp.prefix)
            return opString + expString;
        else
            return expString + opString;
    }
}
