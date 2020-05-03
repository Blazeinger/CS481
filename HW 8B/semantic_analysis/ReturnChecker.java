package semantic_analysis;

import ast.*;
import util.ErrorList;

public class ReturnChecker extends ErrorList {

    public final static ReturnChecker checker = new ReturnChecker();

    public void run(Program program) {
        resetErrors();
        Checker checker = new Checker();
        checker.visit(program);
    }

    private ReturnChecker() { super(); }

    private class Checker extends VisitorDefault<Boolean> {
        private boolean hasOneReturnStatement;

        public Checker() { super(Boolean.FALSE); }

        @Override
        public Boolean visit(StmReturn stm) {
            hasOneReturnStatement = true;
            return Boolean.TRUE;
        }

        @Override
        public Boolean visit(StmWhile stm) {
            return stm.body.accept(this);
        }

        @Override
        public Boolean visit(StmFor stm) {
            return stm.body.accept(this);
        }

        @Override
        public Boolean visit(StmIf stm) {
            return stm.then_branch.accept(this) &&
                    stm.else_branch.isPresent() &&
                    stm.else_branch.get().accept(this);
        }

        @Override
        public Boolean visit(Block block) {
            for (Statement stm : block.statements)
                if (stm.accept(this))
                    return true;
            return false;
        }

        @Override
        public Boolean visit(FunctionDefinition fun) {
            hasOneReturnStatement = false;
            if (fun.returnType.isPresent()) {
                boolean hasEnoughReturn = fun.body.accept(this);
                if (!hasEnoughReturn && hasOneReturnStatement)
                    addError("Function " + fun.name
                            + " doesn't have a return statement in all cases.");
                if (!hasOneReturnStatement)
                    addError("Function " + fun.name
                            + " requires a return statement.");
                return hasEnoughReturn;
            }
            return Boolean.TRUE;
        }
    }
}