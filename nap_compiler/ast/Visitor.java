package ast;

public interface Visitor<T>
{
    T visit(ExpBool exp);
    T visit(ExpChar exp);
    T visit(ExpInt exp);
    T visit(ExpString exp);
    T visit(ExpVar exp);
    T visit(ExpBinop exp);
    T visit(ExpUnop exp);
    T visit(ExpAssignop exp);
    T visit(ExpFuncCall exp);
    T visit(ExpLength exp);
    T visit(ExpNew exp);
    T visit(ExpCallConversion exp);
    T visit(ExpArrAccess array);
    T visit(ExpArrEnum array);
    T visit(StmIf stm);
    T visit(StmAssign stm);
    T visit(StmExp stm);
    T visit(StmRead stm);
    T visit(StmPrint stm);
    T visit(StmReturn stm);
    T visit(StmWhile stm);
    T visit(StmFor stm);
    T visit(StmDecl stm);
    T visit(TypArray type);
    T visit(TypBasic type);
    T visit(Block block);
    T visit(FunctionDefinition fun);
    T visit(Program prog);
}
