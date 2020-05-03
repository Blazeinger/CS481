package mips;

import ir.Register;
import ir.RegisterOffset;
import ir.Type;
import ir.expr.Byte;
import ir.expr.*;
import util.MakeList;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static mips.Asm.sizeOf;

public class Expression implements ir.expr.Visitor<List<String>> {

    private Map<Register, Integer> regAddress;

    public Expression(Map<Register, Integer> regAddress) {
        this.regAddress = regAddress;
    }

    @Override
    public List<String> visit(ReadReg exp) {
        List<String> asmCode = new LinkedList<>();

        Integer offset = regAddress.get( exp.getRegister());
        asmCode.add("\tlw $t0, "+offset+"($fp)");
        asmCode.addAll( Asm.push("$t0") );

        return asmCode;
    }

    @Override
    public List<String> visit(ReadMem exp) {
        List<String> asmCode = new LinkedList<>();

        // address of array
        Integer offset = regAddress.get( exp.getRegister());
        asmCode.add("\tlw $t0, "+offset+"($fp)");

        // NAP offset for array
        exp.getOffset().accept(this);
        Asm.pop("$t1");

        // compute MIPS offset
        Integer size = Asm.sizeOf(exp.getType());
        asmCode.add("mul $t1, $t1, " + size );
        asmCode.add("addi $t1, $t1, 4");
        asmCode.add("add $t1, $t1, $t0");

        // load from mem and push onto stack
        asmCode.add( Asm.command(Asm.load(size) + " $t0, ($t1)"));
        Asm.push("$t0");

        return asmCode;
    }

    @Override
    public List<String> visit(Byte exp) {
        List<String> asmCode =
                MakeList.one(Asm.command("li $t0, " + exp.getValue()));
        asmCode.addAll(Asm.push("$t0"));
        return asmCode;
    }

    @Override
    public List<String> visit(Int exp) {
        List<String> asmCode =
                MakeList.one(Asm.command("li $t0, " + exp.getValue()));
        asmCode.addAll(Asm.push("$t0"));
        return asmCode;
    }

    public List<String> memoryRW(RegisterOffset irObject, int size){
        Register arrayRegister = irObject.getRegister();
        int registerOffset = regAddress.get(arrayRegister);
        // The offset is an expression: the value is pushed
        List<String> asmCode = irObject.getOffset().accept(this);
        // The offset is popped in $t2
        asmCode.addAll(Asm.pop("$t2"));
        // The address of the array is in $t1
        asmCode.add(Asm.command(Asm.load(Program.DEFAULT_SIZE)
                + " $t1, " + registerOffset + "($fp)"));
        asmCode.add(Asm.command("li $t3, " + size));
        asmCode.add(Asm.command("mul $t2, $t2, $t3"));
        asmCode.add(Asm.command("add $t1, $t1, $t2"));
        asmCode.add(Asm.command("li $t3, " + sizeOf(Type.INT)));
        // The address of the cell is in $t1
        asmCode.add(Asm.command("add $t1, $t1, $t3"));
        return asmCode;
    }


    @Override
    public List<String> visit(Unary exp) {
        List<String> code = exp.getExp().accept(this);
        code.addAll(Asm.pop("$t0"));
        switch (exp.getOp()) {
            case SUB:
                code.add(Asm.command("subu $t0, $zero, $t0"));
                break;
            case NOT:
                code.add(Asm.command("li $t1, 4294967294"));
                code.add(Asm.command("nor $t0, $t1, $t0"));
        }
        code.addAll(Asm.push("$t0"));
        return code;
    }

    @Override
    public List<String> visit(Binary exp) {
        List<String> left = exp.getLeft().accept(this);
        List<String> right = exp.getRight().accept(this);
        String op = null;
        switch (exp.getOp()) {
            case ADD:
                op = "addu";
                break;
            case SUB:
                op = "subu";
                break;
            case DIV:
                op = "divu";
                break;
            case MUL:
                op = "mul";
                break;
            case MOD:
                op = "rem";
                break;
            case LT:
                op = "slt";
                break;
            case LE:
                op = "sle";
                break;
            case GT:
                op = "sgt";
                break;
            case GE:
                op = "sge";
                break;
            case AND:
                op = "and";
                break;
            case OR:
                op = "or";
                break;
            case EQ:
                op = "seq";
                break;
            case NEQ:
                op = "sne";
                break;
        }
        List<String> asmCode = left;
        asmCode.addAll(right);
        asmCode.addAll(Asm.pop("$t2"));
        asmCode.addAll(Asm.pop("$t1"));
        asmCode.add(Asm.command(op + " $t0, $t1, $t2"));
        asmCode.addAll(Asm.push("$t0"));
        return asmCode;
    }

    @Override
    public List<String> visit(Symbol exp) {
        int size;
        if (exp == Symbol.BYTE_SIZE) size = 1;
        else size = 4;
        List<String> asmCode = MakeList.one(Asm.command("li $t0, " + size));
        asmCode.addAll(Asm.push("$t0"));
        return asmCode;
    }
}
