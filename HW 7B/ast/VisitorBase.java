package ast;

import util.Pair;

import java.util.List;

/*@ This base visitor visits all the nodes of the tree.
    For leaf nodes, it returns [null].
    For non-leaf nodes, it visits all its children,
    and return the value of its last visited children
    (the ordering is consistent with the concrete syntax
    of NAP, e.g. for print(type, expression), the type
    is visited first, then the expression.
 */
public class VisitorBase<T> implements Visitor<T> {
    @Override
    public T visit(ExpBool exp) {
        return null;
    }

    @Override
    public T visit(ExpChar exp) {
        return null;
    }

    @Override
    public T visit(ExpInt exp) {
        return null;
    }

    @Override
    public T visit(ExpString exp) {
        return null;
    }

    @Override
    public T visit(ExpVar exp) {
        return null;
    }

    @Override
    public T visit(ExpBinop exp) {
        exp.left.accept(this);
        return exp.right.accept(this);
    }

    @Override
    public T visit(ExpUnop exp) {
        return exp.exp.accept(this);
    }

    @Override
    public T visit(ExpAssignop exp) {
        return exp.exp.accept(this);
    }

    private T visitList(List<Expression> list){
        T result = null;
        for(Expression exp : list)
            result = exp.accept(this);
        return result;
    }

    @Override
    public T visit(ExpFuncCall exp) {
        return visitList(exp.arguments);
    }

    @Override
    public T visit(ExpPredefinedCall exp) {
        return visitList(exp.arguments);
    }

    @Override
    public T visit(ExpNew exp) {
        exp.type.accept(this);
        return exp.exp.accept(this);

    }

    @Override
    public T visit(ExpArrAccess array) {
        array.array.accept(this);
        return array.index.accept(this);
    }

    @Override
    public T visit(ExpArrEnum array) {
        return visitList(array.exps);
    }

    @Override
    public T visit(StmIf stm) {
        stm.condition.accept(this);
        T result = stm.then_branch.accept(this);
        if (stm.else_branch.isPresent())
            result = stm.else_branch.get().accept(this);
        return result;
    }

    @Override
    public T visit(StmAssign stm) {
        return stm.exp.accept(this);
    }

    @Override
    public T visit(StmExp stm) {
        return stm.exp.accept(this);
    }

    @Override
    public T visit(StmRead stm) {
        stm.type.accept(this);
        return stm.exp.accept(this);
    }

    @Override
    public T visit(StmPrint stm) {
        stm.type.accept(this);
        return stm.exp.accept(this);
    }

    @Override
    public T visit(StmReturn stm) {
        return stm.exp.accept(this);
    }

    @Override
    public T visit(StmWhile stm) {
        stm.condition.accept(this);
        return stm.body.accept(this);
    }

    @Override
    public T visit(StmFor stm) {
        stm.type.accept(this);
        stm.collection.accept(this);
        return stm.body.accept(this);
    }

    @Override
    public T visit(StmDecl stm) {
        T result = stm.binding.getSnd().accept(this);
        if (stm.initialization.isPresent())
            result = stm.initialization.get().accept(this);
        return result;
    }

    @Override
    public T visit(Type type) {
        return null;
    }

    @Override
    public T visit(Block block) {
        T result = null;
        for (Statement stm : block.statements)
            result = stm.accept(this);
        return result;
    }

    @Override
    public T visit(FunctionDefinition fun) {
        for(Pair<Pair<String, Type>, Boolean> t : fun.arguments)
            t.getFst().getSnd().accept(this);
        if (fun.returnType.isPresent())
            fun.returnType.get().accept(this);
        return fun.body.accept(this);
    }

    @Override
    public T visit(Program program) {
        T result = null;
        for(FunctionDefinition fun : program.functions)
            result = fun.accept(this);
        return result;
    }
}
