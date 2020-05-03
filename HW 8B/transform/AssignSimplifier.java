package transform;

import ast.*;

public class AssignSimplifier {

    public static Program transform(Program program){
        Transformer transformer = new Transformer();
        return (Program) program.accept(transformer);
    }

    private static class Transformer extends VisitorCopy {
        @Override
        public Ast visit(StmAssign stm) {
            if (!stm.op.isPresent())
                return stm;
            Expression left = stm.lValue;
            Expression right = stm.exp;
            Expression newRHS = new ExpBinop(stm.exp.pos, left, stm.op.get(), right);
            return new StmAssign(stm.pos, stm.lValue, newRHS);
        }
    }
}