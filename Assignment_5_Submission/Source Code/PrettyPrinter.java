package compiler;

import ast.*;

import java.util.*;

public class PrettyPrinter implements Visitor<String> {
    private int baseIndent;
    private int currentIndent;

    private static String spaces(int n) {
        return String.join("",
                Collections.nCopies(n, " "));
    }

    public PrettyPrinter(int baseIndent) {
        this.baseIndent = baseIndent;
        this.currentIndent = - baseIndent;
    }

    @Override
    public String visit(ExpUnop exp) {
        return exp.op + " "
                + exp.exp.accept(this);
    }

    @Override
    public String visit(ExpBinop exp) {
        return "(" + exp.left.accept(this)
                + " " + exp.op + " "
                + exp.right.accept(this) + ")";
    }

    @Override
    public String visit(ExpBool bool) {
        return Boolean.toString(bool.value);
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
    public String visit(InsInput ins) {
        return "input(" + ins.var + ")";
    }

    @Override
    public String visit(InsPrint ins) {
        return "print(" + ins.exp.accept(this) + ")";
    }

    @Override
    public String visit(InsIf ins) {
        String condition = ins.exp.accept(this);
        String then_branch = ins.then_branch.accept(this);
        String else_branch = ins.else_branch.accept(this);
        return "if " + condition + "\n"
                + spaces(currentIndent) + "then\n" + then_branch
                + spaces(currentIndent) + "else\n" + else_branch
                + spaces(currentIndent) + "end";
    }

    @Override
    public String visit(InsWhile ins) {
        String condition = ins.exp.accept(this);
        String body = ins.body.accept(this);
        return "while " + condition + " do\n"
                + body
                + spaces(currentIndent) + "end";
    }

    @Override
    public String visit(InsAssign ins) {
        String exp = ins.exp.accept(this);
        String var = ins.var;
        return var + " := " + exp;
    }

    @Override
    public String visit(Typ type) {
        return type.type.toString();
    }

    @Override
    public String visit(Block block) {
        String result = "";
        currentIndent += baseIndent;
        for (Ins ins : block.instructions)
            result += (spaces(currentIndent) + ins.accept(this) + ";\n");
        currentIndent -= baseIndent;
        return result;
    }

    @Override
    public String visit(Declaration declaration) {
        String type = declaration.type.type.toString();
        Optional<String> vars = declaration.vars.stream()
                .reduce((s1, s2) -> s1 + ", " + s2);
        return type + " " + vars.get();
    }

    @Override
    public String visit(Program program) {
        String declarations = "";
        for(Declaration d : program.declarations)
            declarations += d.accept(this) +";\n";
        String main = program.main.accept(this);
        return declarations + main;
    }
}
