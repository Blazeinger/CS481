package mips;

import ir.Register;
import ir.com.*;
import util.ErrorReporter;
import util.MakeList;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static mips.Asm.sizeOf;

public class Command implements Visitor<List<String>> {

    private ErrorReporter<mips.Status> errorReporter;
    private Expression exprVisitor;
    private Map<Register, Integer> regAlloc;

    public Command(ErrorReporter<mips.Status> errorReporter, Map<Register, Integer> regAlloc) {
        this.errorReporter = errorReporter;
        this.regAlloc = regAlloc;
        this.exprVisitor = new mips.Expression(regAlloc);
    }

    @Override
    public List<String> visit(Label com) {
        return MakeList.one(Asm.label(com.toString()));
    }

    @Override
    public List<String> visit(WriteReg com) {
        List<String> asmCode = new LinkedList<>();
        asmCode.addAll(com.getExp().accept(exprVisitor));
        asmCode.addAll(Asm.pop("$t0"));
        int offset = regAlloc.get(com.getReg());
        asmCode.add(Asm.command(Asm.save(Program.DEFAULT_SIZE)
                + " $t0, " + offset + "($fp)"));
        return asmCode;
    }

    @Override
    public List<String> visit(WriteMem com) {
        int size = sizeOf(com.getType());
        List<String> asmCode = new LinkedList<>();
        asmCode.addAll(com.getExp().accept(exprVisitor));
        asmCode.addAll(exprVisitor.memoryRW(com, size));
        asmCode.addAll(Asm.pop("$t2"));
        asmCode.add(Asm.command(Asm.save(size) +
                " $t2, ($t1)"));
        return asmCode;
    }

    @Override
    public List<String> visit(CJump com) {
	// TODO: To Complete
	List<String> asmCode = new LinkedList<>();
        return asmCode;
    }

    @Override
    public List<String> visit(Jump com) {
        return MakeList.one(Asm.command("j " + com.getGotoLabel()));
    }

    private List<String> passArguments(List<ir.expr.Expression> exps) {
        List<String> asmCode = new LinkedList<>();
        List<String> popAndCopy = new LinkedList<>();
        int counter = exps.size() - 1;
        for (ir.expr.Expression exp : exps) {
            asmCode.addAll(exp.accept(exprVisitor));
            popAndCopy.addAll(Asm.pop("$a" + counter));
            counter -= 1;
        }
        asmCode.addAll(popAndCopy);
        return asmCode;
    }

    @Override
    public List<String> visit(ProcCall com) {
	// TODO: To Complete
	List<String> asmCode = new LinkedList<>();
        return asmCode;	
    }

    @Override
    public List<String> visit(FunCall com) {
        List<String> asmCode = passArguments(com.getArguments());
        asmCode.add(Asm.command("jal " + com.getFrame().getEntryPoint()));
        Integer offset = regAlloc.get(com.getRegister());
        asmCode.add(Asm.command("sw $v0, " + offset + "($fp)"));
        return asmCode;
    }
}
