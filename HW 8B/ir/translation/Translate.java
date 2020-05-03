package ir.translation;

import ast.*;
import semantic_analysis.Signature;
import semantic_analysis.SymbolTable;
import semantic_analysis.TypeChecker;
import ir.Frame;
import ir.Register;
import ir.com.*;
import ir.expr.Binary;
import ir.expr.ReadMem;
import ir.expr.ReadReg;
import ir.expr.Unary;
import type.Array;
import type.Basic;
import util.ErrorReporter;
import util.MakeList;
import util.Pair;

import java.util.*;

public class Translate {

    private static final TypeConverter typeConverter = new TypeConverter();
    private static ErrorReporter<ir.translation.Status> errorReporter =
            new ErrorReporter<>("IR translation error");

    /**
     * Convert an type.Type object to an ir.Type.
     *
     * @param type a type.Type object
     * @return an IR type i.e. BYTE, INT or ADDRESS.
     */
    private static ir.Type ofType(type.Type type) {
        return type.accept(typeConverter);
    }

    public static Pair<Label, List<Pair<Frame, List<Command>>>> run(SymbolTable symbolTable, Program program) {
        TranslationVisitor translator = new TranslationVisitor(symbolTable);
        program.accept(translator);
        return new Pair<>(translator.mainLabel, translator.fragments);
    }

    /**
     * A visitor of type.Type, to convert such a type to an ir.Type.
     */
    private static class TypeConverter implements type.Visitor<ir.Type> {
        @Override
        public ir.Type visit(Array type) {
            return ir.Type.ADDRESS;
        }

        @Override
        public ir.Type visit(type.Int type) {
            return ir.Type.INT;
        }

        @Override
        public ir.Type visit(type.Bool type) {
            return ir.Type.BYTE;
        }

        @Override
        public ir.Type visit(type.Char type) {
            return ir.Type.BYTE;
        }

        @Override
        public ir.Type visit(type.Byte type) {
            return ir.Type.BYTE;
        }

        @Override
        public ir.Type visit(type.Float type) {
            errorReporter.report(Status.UNSUPPORTED, "float");
            return null;
        }
    }

    private static class TranslationVisitor implements ast.Visitor<ir.translation.Result> {

        private SymbolTable symbolTable;
        private TypeChecker typeChecker;

        private Map<String, Frame> frames;
        private Map<Pair<Block, String>, Register> varToReg;
        private List<Pair<Frame, List<ir.com.Command>>> fragments;
        private Frame currentFrame;
        private Label mainLabel;

        public TranslationVisitor(SymbolTable symbolTable) {
            this.symbolTable = symbolTable;
            this.varToReg = new HashMap<>();
            this.typeChecker = new TypeChecker(symbolTable);
            this.fragments = new LinkedList<>();
            this.frames = new HashMap<>();
            this.currentFrame = null;
            this.mainLabel = null;
        }

        private Pair<Block, String> inCurrentBlock(String variable) {
            return new Pair<>(typeChecker.getVisitedBlocks().current(), variable);
        }

        @Override
        public Result visit(ExpBool exp) {
            return new Result(new ir.expr.Byte((byte) (exp.value ? 1 : 0)));
        }

        @Override
        public Result visit(ExpChar exp) {
            return new Result(new ir.expr.Byte((byte) exp.value));
        }

        @Override
        public Result visit(ExpInt exp) {
            return new Result(new ir.expr.Int(exp.value));
        }


        private Register registerLookup(String name) {
            Register reg = null;
            for (Block block : typeChecker.getVisitedBlocks().getStack()) {
                reg = varToReg.get(new Pair<>(block, name));
                if (reg != null) return reg;
            }
            assert false : "Internal Error: no register associated with " + name;
            return null;
        }

        @Override
        public Result visit(ExpVar exp) {
            Register reg = registerLookup(exp.name);
            return new Result(new ReadReg(reg));
        }

        @Override
        public Result visit(ExpBinop exp) {
            Result left = exp.left.accept(this);
            Result right = exp.right.accept(this);
            ir.expr.Expression binary = new Binary(left.getExp(), right.getExp(), exp.op);
            List<ir.com.Command> code = new LinkedList<>();
            code.addAll(left.getCode());
            code.addAll(right.getCode());
            return new Result(binary, code);
        }

        @Override
        public Result visit(ExpUnop exp) {
            Result result = exp.exp.accept(this);
            ir.expr.Expression newExp = new Unary(result.getExp(), exp.op);
            return new Result(newExp, result.getCode());
        }

        @Override
        public Result visit(ExpAssignop exp) {
            errorReporter.report(Status.UNSUPPORTED, "++ and --");
            return null;
        }

        @Override
        public Result visit(ExpArrAccess exp) {
            Optional<type.Type> typingResult = exp.accept(typeChecker);
            assert typingResult.isPresent() : "Internal Error: typing failed in " + exp;
            type.Type cellType = typingResult.get();
            Result arrayResult = exp.array.accept(this);
            Result indexResult = exp.index.accept(this);
            Register arrayRegister = new Register(ir.Type.ADDRESS);
            currentFrame.addLocal(arrayRegister);
            List<ir.com.Command> code = new LinkedList<>(indexResult.getCode());
            code.addAll(arrayResult.getCode());
            ir.expr.Expression index = indexResult.getExp();
            ir.com.Command arrayCom = new WriteReg(arrayRegister, arrayResult.getExp());
            code.add(arrayCom);
            ir.expr.Expression newExp = new ReadMem(arrayRegister, index, ofType(cellType));
            return new Result(newExp, code);
        }

        @Override
        public Result visit(StmAssign stm) {
            // StmAssign has passed the type checking, therefore the lValue field
            // contains either an ExpVar, or and ExpArrAccess expression
            if (stm.op.isPresent())
                errorReporter.report(Status.UNSUPPORTED,
                        "assignment with operator", stm.pos);
            assert (stm.lValue instanceof ast.ExpVar || stm.lValue instanceof ast.ExpArrAccess) :
                    "Internal Error: lValue not ExpVar or ExpArrAccess in " + stm;
            Result resultExp = stm.exp.accept(this);
            List<ir.com.Command> code = new LinkedList<>(resultExp.getCode());
            if (stm.lValue instanceof ast.ExpVar) {
                ast.ExpVar expVar = (ast.ExpVar) stm.lValue;
                Register reg = registerLookup(expVar.name);
                assert reg != null : "Internal Error: no register associated with " + expVar.name;
                code.add(new ir.com.WriteReg(reg, resultExp.getExp()));
            }
            if (stm.lValue instanceof ast.ExpArrAccess) {
                Result result = stm.lValue.accept(this);
                if (result.getExp() instanceof ReadMem) {
                    code.addAll(result.getCode());
                    ReadMem read = (ReadMem) result.getExp();
                    Optional<type.Type> typingResult = stm.exp.accept(typeChecker);
                    assert typingResult.isPresent() : "Internal Error: typing failed in " + stm.exp;
                    ir.Type type = ofType(typingResult.get());
                    code.add(new ir.com.WriteMem(read.getRegister(), read.getOffset(), resultExp.getExp(), type));
                } else
                    assert false : "Internal error: ExpArrAccess not translated to ReadMem in " + stm.lValue;
            }
            return new Result(code);
        }

        @Override
        public Result visit(StmExp stm) {
            return stm.exp.accept(this);
        }

        @Override
        public Result visit(StmReturn stm) {
            Result result = stm.exp.accept(this);
            assert currentFrame.getResult().isPresent() :
                    "Internal Error: return statement in a function without a return type in " + stm;
            Register returnReg = currentFrame.getResult().get();
            Command writeReturnReg = new WriteReg(returnReg, result.getExp());
            Command gotoExitPoint = new Jump(currentFrame.getExitPoint());
            List<Command> code = new LinkedList<>(result.getCode());
            code.add(writeReturnReg);
            code.add(gotoExitPoint);
            return new Result(code);
        }

        private Pair<List<ir.expr.Expression>, List<Command>> translateExpressions(List<Expression> exps) {
            List<Command> code = new LinkedList<>();
            List<ir.expr.Expression> expressions = new LinkedList<>();
            for (Expression expression : exps) {
                Result result = expression.accept(this);
                expressions.add(result.getExp());
                code.addAll(result.getCode());
            }
            return new Pair<>(expressions, code);
        }

        private Result makeFunCall(type.Type type, Frame frame,
                                   List<ir.expr.Expression> arguments,
                                   List<Command> code) {
            Register reg = new Register(ofType(type));
            currentFrame.addLocal(reg);
            ir.com.Command call = new FunCall(reg, frame, arguments);
            code.add(call);
            return new Result(new ReadReg(reg), code);
        }

        private Result makeProcCall(Frame frame, List<ir.expr.Expression> arguments,
                                    List<Command> code) {
            code.add(new ProcCall(frame, arguments));
            return new Result(code);
        }

        @Override
        public Result visit(ExpFuncCall exp) {
            Pair<List<ir.expr.Expression>, List<Command>> translation =
                    translateExpressions(exp.arguments);
            List<ir.com.Command> code = translation.getSnd();
            List<ir.expr.Expression> arguments = translation.getFst();
            Frame frame = frames.get(exp.funcName);
            Optional<Signature> signature = symbolTable.funcLookup(exp.funcName);
            assert signature.isPresent() : "Internal Error: function name not in symbol table: " + exp.funcName;
            Optional<type.Type> returnType = signature.get().returnType;
            if (returnType.isPresent())
                return makeFunCall(returnType.get(), frame, arguments, code);
            return makeProcCall(frame, arguments, code);
        }


        @Override
        public Result visit(ExpPredefinedCall exp) {
            if (exp.funcName == OpPredefined.LENGTH) {
                Pair<List<ir.expr.Expression>, List<Command>> translation =
                        translateExpressions(exp.arguments);
                List<Command> code = translation.getSnd();
                Register reg = new Register(ir.Type.INT);
                currentFrame.addLocal(reg);
                code.add(new FunCall(reg, PredefinedFrames.LENGTH, translation.getFst()));
                return new Result(new ReadReg(reg), code);
            }
            // All other predefined functions take one argument and do nothing
            assert exp.arguments.size() == 1 :
                    "Internal Error: predefined function has more than 1 argument";
            return exp.arguments.get(0).accept(this);
        }

        @Override
        public Result visit(StmRead stm) {
            Result translation = stm.exp.accept(this);
            ir.expr.Expression expr = translation.getExp();
            List<Command> code = new LinkedList<>(translation.getCode());
            Frame frame = null;
            List<ir.expr.Expression> arguments = new LinkedList<>();
            if (stm.type.type.equals(type.Basic.INT) || stm.type.type.equals(Basic.BYTE)
                    || stm.type.type.equals(Basic.BOOL) || stm.type.type.equals(Basic.CHAR)) {
                arguments.add(translation.getExp());
                if (stm.type.type.equals(Basic.INT) || stm.type.type.equals(Basic.BYTE))
                    frame = PredefinedFrames.READ_INT;
                if (stm.type.type.equals(Basic.CHAR))
                    frame = PredefinedFrames.READ_CHAR;
                if (stm.type.type.equals(Basic.BOOL))
                    frame = PredefinedFrames.READ_BOOL;
                Register reg = null;
                assert (expr instanceof ReadReg || expr instanceof ReadMem) :
                        "Internal Error: the argument of a read statement should be assignable";
                if (expr instanceof ReadReg)
                    reg = ((ReadReg) expr).getRegister();
                if (expr instanceof ReadMem) {
                    reg = new Register(expr.getType());
                    currentFrame.addLocal(reg);
                }
                code.add(new FunCall(reg, frame, arguments));
                if (expr instanceof ReadMem)
                    code.add(new WriteMem(((ReadMem) expr).getRegister(),
                            ((ReadMem) expr).getOffset(), new ReadReg(reg), expr.getType()));
                return new Result(code);
            }
            if (stm.type.type.equals(type.Array.stringType)) {
                Expression len = new ExpPredefinedCall(stm.pos,
                        OpPredefined.LENGTH,
                        MakeList.one(stm.exp));
                Result translateLen = len.accept(this);
                code.addAll(translateLen.getCode());
                code.add(new ProcCall(PredefinedFrames.READ_STRING,
                        MakeList.two(expr, translateLen.getExp())));
                return new Result(code);
            }
            errorReporter.report(Status.UNSUPPORTED,
                    "reading values of type " + stm.type.type, stm.pos);
            return null;
        }

        @Override
        public Result visit(StmPrint stm) {
            Result translation = stm.exp.accept(this);
            ir.expr.Expression expr = translation.getExp();
            List<Command> code = new LinkedList<>(translation.getCode());
            Frame frame = null;
            List<ir.expr.Expression> arguments = new LinkedList<>();
            if (stm.type.type.equals(Basic.INT) || stm.type.type.equals(Basic.BYTE)
                    || stm.type.type.equals(Basic.BOOL) || stm.type.type.equals(Basic.CHAR)
                    || stm.type.type.equals(type.Array.stringType)) {
                arguments.add(translation.getExp());
                if (stm.type.type.equals(Basic.INT) || stm.type.type.equals(Basic.BYTE))
                    frame = PredefinedFrames.PRINT_INT;
                if (stm.type.type.equals(Basic.CHAR))
                    frame = PredefinedFrames.PRINT_CHAR;
                if (stm.type.type.equals(Basic.BOOL))
                    frame = PredefinedFrames.PRINT_BOOL;
                if (stm.type.type.equals(type.Array.stringType))
                    frame = PredefinedFrames.PRINT_STRING;
                code.add(new ProcCall(frame, arguments));
                return new Result(code);
            }
            errorReporter.report(Status.UNSUPPORTED,
                    "printing values of type " +
                            stm.type.type, stm.pos);
            return null;
        }

        private Result newArray(type.Type cellType, Result length) {
            Register arrayReg = new Register(ir.Type.ADDRESS);
            currentFrame.addLocal(arrayReg);
            ir.Type type = ofType(cellType);
            ir.expr.Expression cellSize = type.toSymbol();
            List<Command> code = length.getCode();
            code.add(new FunCall(arrayReg, PredefinedFrames.NEW,
                    MakeList.two(cellSize, length.getExp())));
            return new Result(new ReadReg(arrayReg), code);
        }

        @Override
        public Result visit(ExpNew exp) {
            Result length = exp.exp.accept(this);
            return newArray(exp.type.type, length);
        }

        @Override
        public Result visit(ExpArrEnum array) {
            int size = array.exps.size();
            if (size == 0)
                errorReporter.report(Status.UNSUPPORTED, "array of size 0", array.pos);
            type.Type type = array.exps.get(0).accept(typeChecker).get();
            Result arrayCreation = newArray(type, new Result(new ir.expr.Int(size)));
            Register arrayReg = ((ReadReg) arrayCreation.getExp()).getRegister();
            List<Command> code = arrayCreation.getCode();
            for (int counter = 0; counter < size; counter++) {
                Result elementResult = array.exps.get(counter).accept(this);
                code.addAll(elementResult.getCode());
                code.add(new WriteMem(arrayReg, new ir.expr.Int(counter),
                        elementResult.getExp(), elementResult.getExp().getType()));
            }
            return new Result(new ReadReg(arrayReg), code);
        }

        private ExpArrEnum enumOfString(ExpString exp) {
            List<Expression> exps = new LinkedList<>();
            for (char c : exp.value.toCharArray())
                exps.add(new ExpChar(exp.pos, c));
            exps.add(new ExpChar(exp.pos, '\0'));
            return new ExpArrEnum(exp.pos, exps);
        }

        @Override
        public Result visit(ExpString exp) {
            return enumOfString(exp).accept(this);
        }

        @Override
        public Result visit(StmIf stm) {
            Result condition = stm.condition.accept(this);
            Label thenLabel = Label.fresh();
            Label elseLabel = Label.fresh();
            Label exitLabel = Label.fresh();
            List<Command> thenBranch = stm.then_branch.accept(this).getCode();
            boolean hasElse = stm.else_branch.isPresent();
            List<Command> elseBranch = hasElse ?
                    stm.else_branch.get().accept(this).getCode() :
                    new LinkedList<>();
            Command conditional = new ir.com.CJump(condition.getExp(), thenLabel, elseLabel);
            List<Command> code = condition.getCode();
            code.add(conditional);
            code.add(thenLabel);
            code.addAll(thenBranch);
            if (hasElse) code.add(new Jump(exitLabel));
            code.add(elseLabel);
            code.addAll(elseBranch);
            if (hasElse) code.add(exitLabel);
            return new Result(code);
        }


        @Override
        public Result visit(StmWhile stm) {
            Label loopEntry = Label.fresh();
            Label loopBody = Label.fresh();
            Label loopExit = Label.fresh();
            Result conditionTranslation = stm.condition.accept(this);
            List<Command> bodyCode = stm.body.accept(this).getCode();
            List<Command> code = MakeList.one(loopEntry);
            if (!stm.doWhile) {
                code.addAll(conditionTranslation.getCode());
                code.add(new CJump(conditionTranslation.getExp(),
                        loopBody, loopExit));
                code.add(loopBody);
            }
            code.addAll(bodyCode);
            if (stm.doWhile) {
                code.addAll(conditionTranslation.getCode());
                code.add(new CJump(conditionTranslation.getExp(),
                        loopBody, loopExit));
                code.add(loopBody);
            }
            code.add(new Jump(loopEntry));
            code.add(loopExit);
            return new Result(code);
        }


        @Override
        public Result visit(StmFor stm) {
            errorReporter.report(Status.UNSUPPORTED, "for loop");
            return null;
        }

        @Override
        public Result visit(StmDecl stm) {
            // Declaration of a new variable = creation of a fresh register
            Register reg = new Register(ofType(stm.binding.getSnd().type));
            varToReg.put(inCurrentBlock(stm.binding.getFst()), reg);
            currentFrame.addLocal(reg);
            // If there is an initialization, a write to the new register is needed
            List<ir.com.Command> code = new LinkedList<>();
            if (stm.initialization.isPresent()) {
                Result result = stm.initialization.get().accept(this);
                code.addAll(result.getCode());
                code.add(new ir.com.WriteReg(reg, result.getExp()));
            }
            return new Result(code);
        }

        @Override
        public Result visit(Type type) {
            assert false : "Internal Error: Type node should not be visited";
            return null;
        }

        @Override
        public Result visit(Block block) {
            typeChecker.getVisitedBlocks().enter(block);
            List<Command> code = new LinkedList<>();
            for (Statement stm : block.statements) {
                Result result = stm.accept(this);
                code.addAll(result.getCode());
            }
            typeChecker.getVisitedBlocks().exit();
            return new Result(code);
        }

        @Override
        public Result visit(FunctionDefinition fun) {
            Frame frame = frames.get(fun.name);
            assert frame != null : "Internal Error: no frame for function " + fun.name;
            currentFrame = frame;
            Result result = fun.body.accept(this);
            // If result contains an expression part, it is just discarded
            fragments.add(new Pair<>(frame, result.getCode()));
            return null;
        }

        @Override
        public Result visit(Program program) {
            program.accept(typeChecker);
            FramesBuilder framesBuilder = new FramesBuilder();
            program.accept(framesBuilder);
            for (FunctionDefinition fun : program.functions)
                fun.accept(this);
            return null;
        }

        private class FramesBuilder extends ast.VisitorBase<Void> {

            @Override
            public Void visit(FunctionDefinition fun) {
                List<Register> parameters = new LinkedList<>();
                List<Boolean> passedByRef = new LinkedList<>();
                for (Pair<Pair<String, ast.Type>, Boolean> argument : fun.arguments) {
                    ast.Type argType = argument.getFst().getSnd();
                    String argName = argument.getFst().getFst();
                    boolean byRef = argument.getSnd();
                    if (byRef) errorReporter.report(Status.UNSUPPORTED,
                            "pass by reference", fun.pos);
                    Register register = new Register(ofType(argType.type));
                    varToReg.put(new Pair<>(fun.body, argName), register);
                    parameters.add(register);
                    passedByRef.add(byRef);
                }
                Frame frame;
                if (fun.returnType.isPresent()) {
                    ir.Type type = ofType(fun.returnType.get().type);
                    frame = new Frame(Label.fresh(), Label.fresh(), parameters, passedByRef, new Register(type));
                } else
                    frame = new Frame(Label.fresh(), Label.fresh(), parameters, passedByRef);
                frames.put(fun.name, frame);
                if (fun.name.equals("main"))
                    mainLabel = frame.getEntryPoint();
                return null;
            }

            @Override
            public Void visit(Program program) {
                for (FunctionDefinition fun : program.functions)
                    fun.accept(this);
                return null;
            }
        }
    }
}
