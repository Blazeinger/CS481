package ast;

import util.Pair;

import java.util.LinkedList;
import java.util.List;

public class VisitorCopy implements Visitor<Ast> {

    @Override
    public Ast visit(ExpBool exp) {
        return new ExpBool(exp.pos, exp.value);
    }

    @Override
    public Ast visit(ExpChar exp) {
        return new ExpChar(exp.pos, exp.value);
    }

    @Override
    public Ast visit(ExpInt exp) {
        return new ExpInt(exp.pos, exp.value);
    }

    @Override
    public Ast visit(ExpString exp) {
        return new ExpString(exp.pos, exp.value);
    }

    @Override
    public Ast visit(ExpVar exp) {
        return new ExpVar(exp.pos, exp.name);
    }

    @Override
    public Ast visit(ExpBinop exp) {
        Expression newLeft = (Expression) exp.left.accept(this);
        Expression newRight = (Expression) exp.right.accept(this);
        return new ExpBinop(exp.pos, newLeft, exp.op, newRight);
    }

    @Override
    public Ast visit(ExpUnop exp) {
        Expression newExp = (Expression) exp.exp.accept(this);
        return new ExpUnop(exp.pos, exp.op, newExp);
    }

    @Override
    public Ast visit(ExpAssignop exp) {
        Expression newExp = (Expression) exp.exp.accept(this);
        return new ExpAssignop(exp.pos, exp.op, newExp, exp.prefix);

    }

    private <T extends Ast> List<T> copyList(List<T> list) {
        List<T> newList = new LinkedList<>();
        for (T element : list){
	    Ast ast = element.accept(this);
	    newList.add((T) ast);
	}
        return newList;
    }

    @Override
    public Ast visit(ExpFuncCall exp) {
        return new ExpFuncCall(exp.pos, exp.funcName, copyList(exp.arguments));
    }

    @Override
    public Ast visit(ExpPredefinedCall exp) {
        return new ExpPredefinedCall(exp.pos, exp.funcName,
                copyList(exp.arguments));
    }

    @Override
    public Ast visit(ExpNew exp) {
        Expression newExp = (Expression) exp.exp.accept(this);
        Type newType = (Type) exp.type.accept(this);
        return new ExpNew(exp.pos, newType, newExp);
    }

    @Override
    public Ast visit(ExpArrAccess array) {
        Expression newArr = (Expression) array.array.accept(this);
        Expression newIndex = (Expression) array.index.accept(this);
        return new ExpArrAccess(array.pos, newArr, newIndex);
    }

    @Override
    public Ast visit(ExpArrEnum array) {
        return new ExpArrEnum(array.pos, copyList(array.exps));
    }

    @Override
    public Ast visit(StmIf stm) {
        Expression newCondition = (Expression) stm.condition.accept(this);
        Block newThen = (Block) stm.then_branch.accept(this);
        if (stm.else_branch.isPresent()) {
            Block newElse = (Block) stm.else_branch.get().accept(this);
            return new StmIf(stm.pos, newCondition, newThen, newElse);
        }
        return new StmIf(stm.pos, newCondition, newThen);
    }

    @Override
    public Ast visit(StmAssign stm) {
        Expression newLValue = (Expression) stm.lValue.accept(this);
        Expression newExp = (Expression) stm.exp.accept(this);
        if (stm.op.isPresent())
            return new StmAssign(stm.pos, newLValue, newExp, stm.op.get());
        else
            return new StmAssign(stm.pos, newLValue, newExp);
    }

    @Override
    public Ast visit(StmExp stm) {
        Expression newExp = (Expression) stm.exp.accept(this);
        return new StmExp(stm.pos, newExp);
    }

    @Override
    public Ast visit(StmRead stm) {
        Expression newExp = (Expression) stm.exp.accept(this);
        Type newType = (Type) stm.type.accept(this);
        return new StmRead(stm.pos, newType, newExp);
    }

    @Override
    public Ast visit(StmPrint stm) {
        Expression newExp = (Expression) stm.exp.accept(this);
        Type newType = (Type) stm.type.accept(this);
        return new StmPrint(stm.pos, newType, newExp);
    }

    @Override
    public Ast visit(StmReturn stm) {
        Expression newExp = (Expression) stm.exp.accept(this);
        return new StmReturn(stm.pos, newExp);
    }

    @Override
    public Ast visit(StmWhile stm) {
        Expression newCondition = (Expression) stm.condition.accept(this);
        Block newBody = (Block) stm.body.accept(this);
        if (stm.doWhile)
            return StmWhile.DoWhile(stm.pos, newCondition, newBody);
        return StmWhile.While(stm.pos, newCondition, newBody);
    }

    @Override
    public Ast visit(StmFor stm) {
        Expression newCollection = (Expression) stm.collection.accept(this);
        Type newType = (Type) stm.type.accept(this);
        Block newBody = (Block) stm.body.accept(this);
        return new StmFor(stm.pos, newType, stm.var, newCollection, newBody);
    }

    @Override
    public Ast visit(StmDecl stm) {
        Type newType = (Type) stm.binding.getSnd().accept(this);
        if (stm.initialization.isPresent()) {
            Expression newInit =
                    (Expression) stm.initialization.get().accept(this);
            return new StmDecl(stm.pos, stm.binding.getFst(), newType, newInit);
        }
        return new StmDecl(stm.pos, stm.binding.getFst(), newType);
    }

    @Override
    public Ast visit(Type type) {
        return new Type(type.pos, type.type);
    }

    @Override
    public Ast visit(Block block) {
        return new Block(block.pos, copyList(block.statements));
    }

    @Override
    public Ast visit(FunctionDefinition fun) {
        List<Pair<Pair<String, Type>, Boolean>> newArguments =
                new LinkedList<>();
        for (Pair<Pair<String, Type>, Boolean> argument : fun.arguments) {
            String argName = argument.getFst().getFst();
            Type argType = (Type) argument.getFst().getSnd().accept(this);
            newArguments.add(new Pair<>(new Pair<>(argName, argType),
                    argument.getSnd()));
        }
        Block newBody = (Block) fun.body.accept(this);
        if (fun.returnType.isPresent())
            return new FunctionDefinition(fun.pos, fun.name, newArguments, newBody,
                    (Type) fun.returnType.get().accept(this));
        return new FunctionDefinition(fun.pos, fun.name, newArguments, newBody);
    }

    @Override
    public Ast visit(Program program) {
        return new Program(program.pos, copyList(program.functions));
    }
}
