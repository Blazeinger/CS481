package transform;

import ast.*;

import java.util.LinkedList;
import java.util.List;

public class ExpAssignopElimination {

    public static Program transform(Program program){
        Transformer transformer = new Transformer();
        return (Program) program.accept(transformer);
    }

    private static class Transformer extends VisitorCopy{

        private List<Statement> before;
        private List<Statement> after;

        @Override
        public Ast visit(ExpAssignop exp) {
            OpBinary op = OpBinary.ADD;
            if (exp.op == OpAssign.DEC)
                op = OpBinary.SUB;
            Expression newExp = (Expression) exp.exp.accept(this);
            StmAssign stm = new StmAssign(exp.pos,
                newExp, new ExpBinop(exp.pos, newExp, op,
                    new ExpInt(exp.pos, 1)));
            if (exp.prefix)
                before.add(stm);
            else
                after.add(stm);
            return newExp;
        }

        @Override
        public Ast visit(Block block) {
            List<Statement> statements = new LinkedList<>();
            for(Statement stm : block.statements){
                before = new LinkedList<>();
                after = new LinkedList<>();
                Statement newStm = (Statement) stm.accept(this);
                statements.addAll(before);
                statements.add(newStm);
                statements.addAll(after);
            }
            before = new LinkedList<>();
            after = new LinkedList<>();
            return new Block(block.pos, statements);
        }
    }
}
