package transform;

import ast.*;
import util.Generator;
import type.Basic;
import java.util.*;

import static util.MakeList.one;

public class ForElimination {

    public static Program transform(Program program){
        Eliminator eliminator = new Eliminator();
        return (Program) program.accept(eliminator);
    }

    private static class Eliminator extends VisitorCopy {

        private Generator nameGenerator;

        /* Note: there is no guarantee these names won't clash with user-defined names,
                 but we chose the names so that it is unlikely. A more robust version
                 should ensure the absence of name clashing. */
        Eliminator(){
            this.nameGenerator = new Generator("__c_o_u_n_t_e_r__");
        }

        private static final Position notActualPos = new Position(0, 0);
        private static final Type intType = new Type(new Position(0,0), Basic.INT);
        private static final ExpInt zero = new ExpInt(notActualPos, 0);
        private static final ExpInt one = new ExpInt(notActualPos, 1);

        private StmDecl buildCounterDecl(String counterName){
            return new StmDecl(notActualPos, counterName, intType,zero);
        }

        private ExpVar buildCounterVar(String counterName){
            return new ExpVar(notActualPos, counterName);
        }

        private Expression buildWhileCondition(String counterName, Expression array){
            Expression callToLength =
                    new ExpPredefinedCall(notActualPos,
                            OpPredefined.LENGTH, one(array));
            return new ExpBinop(notActualPos,
                    buildCounterVar(counterName),
                    OpBinary.LT,
                    callToLength
            );
        }

        private StmWhile buildWhile(String counterName, Type loopVarType,
                                    String loopVar, Expression array, Block body){
            Expression condition = buildWhileCondition(counterName, array);
            List<Statement> statements = body.statements;
            Expression counterVar = buildCounterVar(counterName);
            // Line to access the current element of the array:
            StmDecl loopVarDecl = new StmDecl(notActualPos, loopVar, loopVarType,
                    new ExpArrAccess(notActualPos, array, counterVar));
            // Line to increment the counter
            StmAssign incrCounter = new StmAssign(notActualPos, counterVar,
                    new ExpBinop(notActualPos, counterVar, OpBinary.ADD, one));
            // Add these to new statements to the for body
            statements.add(0, loopVarDecl);
            statements.add(incrCounter);
            Block newBody = new Block(notActualPos, statements);
            return StmWhile.While(notActualPos, condition, newBody);
        }

        private List<Statement> whileOfFor(StmFor stm){
            String loopVar = stm.var;
            ast.Type loopVarType = stm.type;
            Expression collection = stm.collection;
            Block body = (Block)stm.body.accept(this);
            String counterName = nameGenerator.getNew();
            StmDecl counterDecl = buildCounterDecl(counterName);
            StmWhile stmWhile = buildWhile(counterName, loopVarType, loopVar, collection, body);
            List<Statement> newStatements = one(counterDecl);
            newStatements.add(stmWhile);
            return newStatements;
        }

        @Override
        public Block visit(Block block) {
            List<Statement> statements = new LinkedList<>();
            for(int index = 0; index < block.statements.size(); index++) {
                Statement stm = block.statements.get(index);
                if (stm instanceof StmFor)
                    statements.addAll(whileOfFor((StmFor) stm));
                else
                    statements.add((Statement) stm.accept(this));
            }
            return new Block(block.pos, statements);
        }
    }
}