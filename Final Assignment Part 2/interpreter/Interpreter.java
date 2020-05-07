package interpreter;

import ast.*;
import interpreter.value.*;
import type.Array;
import type.Basic;
import util.ErrorReporter;
import util.MakeList;
import util.Pair;

import java.util.*;

public class Interpreter {

    public static void run(Program program, List<String> arguments) {
        Execute interpreter = new Execute(arguments);
        program.accept(interpreter);
    }

    private static class Execute implements Visitor<Value> {

        private static ErrorReporter<interpreter.Status> errorReporter =
                new ErrorReporter<>("Runtime error");
        private List<String> arguments;
        private Map<String, FunctionDefinition> functions;
        private Stack<Map<String, Value>> callStack;

        public Execute(List<String> arguments) {
            this.arguments = arguments;
            functions = new HashMap<>();
            callStack = new Stack<>();

        }

        @Override
        public Value visit(ExpBool exp) {
            return new ValueBool(exp.value);
        }

        @Override
        public Value visit(ExpChar exp) {
            return new ValueChar(exp.value);
        }

        @Override
        public Value visit(ExpInt exp) {
            return new ValueInt(exp.value);
        }


        private Value valueOfString(String s) {
            List<Value> values = new ArrayList<>();
            for (char c : s.toCharArray())
                values.add(new ValueChar(c));
            values.add(new ValueChar('\0'));
            return new ValueArray(values);
        }

        @Override
        public Value visit(ExpString exp) {
            return valueOfString(exp.value);
        }

        @Override
        public Value visit(ExpVar exp) {
            Map<String, Value> environment = callStack.peek();
            Value value = environment.get(exp.name);
            if (value != null) return value;
            return Value.NONE;
        }

        private ValueInt arithmeticOp(Position pos, Value left, OpBinary op, Value right) {
            assert (op == OpBinary.ADD || op == OpBinary.SUB || op == OpBinary.MUL
                    || op == OpBinary.MOD || op == OpBinary.DIV) :
                    "Internal Error: should be an arithmetic operation: " + op;
            assert left instanceof ValueInt : "Internal Error: type error: int expected (L) " + left + " " + pos;
            assert right instanceof ValueInt : "Internal Error: type error: int expected (R)  " + right + " " + pos;
            int x = ((ValueInt) left).value;
            int y = ((ValueInt) right).value;
            int result;
            switch (op) {
                case ADD:
                    result = x + y;
                    break;
                case SUB:
                    result = x - y;
                    break;
                case MUL:
                    result = x * y;
                    break;
                case DIV:
                    result = x / y;
                    break;
                default:
                    result = x % y;
            }
            return new ValueInt(result);
        }

        private ValueBool arithmeticCmp(Position pos, Value left, OpBinary op, Value right) {
            assert (op == OpBinary.LT || op == OpBinary.LE
                    || op == OpBinary.GT || op == OpBinary.GE) :
                    "Internal Error: should be an arithmetic comparison: " + op + pos;
            assert left instanceof ValueInt : "Internal Error: type error " + left + pos;
            assert right instanceof ValueInt : "Internal Error: type error " + right + pos;
            int x = ((ValueInt) left).value;
            int y = ((ValueInt) right).value;
            boolean result;
            switch (op) {
                case LT:
                    result = x < y;
                    break;
                case LE:
                    result = x <= y;
                    break;
                case GT:
                    result = x > y;
                    break;
                default:
                    result = x >= y;
            }
            return new ValueBool(result);
        }

        private ValueBool booleanOp(Position pos, Value left, OpBinary op, Value right) {
            assert (op == OpBinary.AND || op == OpBinary.OR) :
                    "Internal Error: should be a Boolean operation: " + op + pos;
            assert left instanceof ValueBool : "Internal Error: type error " + left + pos;
            assert right instanceof ValueBool : "Internal Error: type error " + right + pos;
            boolean x = ((ValueBool) left).value;
            boolean y = ((ValueBool) right).value;
            boolean result;
            if (op == OpBinary.AND) result = x && y;
            else result = x || y;
            return new ValueBool(result);
        }

        @Override
        public Value visit(ExpBinop exp) {
            Value left = exp.left.accept(this);
            Value right = exp.right.accept(this);
            switch (exp.op) {
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                case MOD:
                    return arithmeticOp(exp.pos, left, exp.op, right);
                case LT:
                case LE:
                case GT:
                case GE:
                    return arithmeticCmp(exp.pos, left, exp.op, right);
                case AND:
                case OR:
                    return booleanOp(exp.pos, left, exp.op, right);
                case NEQ:
                    return new ValueBool(!left.equals(right));
                default:
                    return new ValueBool(left.equals(right));
            }
        }

        @Override
        public Value visit(ExpUnop exp) {
	    	Value expVal = exp.exp.accept(this);

	    	switch (exp.op) {
		    	case SUB:
				return arithmeticOp(exp.pos, new ValueInt(0), OpBinary.SUB, expVal);
			default:
				return new ValueBool(!((ValueBool)expVal).value);
	    	}
        	}

        @Override
        public Value visit(ExpAssignop exp) {
            errorReporter.report(Status.UNSUPPORTED,
				 "assignment operations", exp.pos);
            return null;
        }

        @Override
        public Value visit(ExpFuncCall exp) {
            Map<String, Value> environment = new HashMap<>();
            FunctionDefinition fun = functions.get(exp.funcName);
            assert fun != null : "Internal Error: undefined function in call";
            assert fun.arguments.size() == exp.arguments.size() :
                    "Internal Error: wrong number of arguments in call";
            for (int i = 0; i < fun.arguments.size(); i++) {
                Value value = exp.arguments.get(i).accept(this);
                String variable = fun.arguments.get(i).getFst().getFst();
                environment.put(variable, value);
            }
            callStack.push(environment);
            Value value = fun.body.accept(this);
            callStack.pop();
            return value;
        }


        private ValueByte byteOfInt(Position pos, Value value) {
            assert value instanceof ValueInt :
                    "Internal Error: int value expected " + pos;
            int number = ((ValueInt) value).value;
            return new ValueByte((byte) number);
        }

        private ValueInt intOfByte(Position pos, Value value) {
            assert value instanceof ValueByte :
                    "Internal Error: byte value expected " + pos;
            byte number = ((ValueByte) value).value;
            return new ValueInt(number);
        }

        private ValueChar charOfByte(Position pos, Value value) {
            assert value instanceof ValueByte :
                    "Internal Error: byte value expected " + pos;
            byte number = ((ValueByte) value).value;
            return new ValueChar((char) number);
        }

        private ValueByte byteOfChar(Position pos, Value value) {
            assert value instanceof ValueChar :
                    "Internal Error: char value expected " + pos;
            char character = ((ValueChar) value).value;
            return new ValueByte((byte) character);
        }

        @Override
        public Value visit(ExpPredefinedCall exp) {
            assert exp.arguments.size() == 1 :
                    "Internal Error: predefined operation " + exp.funcName
                            + " expects 1 argument " + exp.pos;
            Value value = exp.arguments.get(0).accept(this);
            if (exp.funcName == OpPredefined.LENGTH)
                return new ValueInt(((ValueArray) value).getValues().size());
            if (exp.funcName == OpPredefined.BYTE_OF_CHAR)
                return byteOfChar(exp.pos, value);
            if (exp.funcName == OpPredefined.CHAR_OF_BYTE)
                return charOfByte(exp.pos, value);
            if (exp.funcName == OpPredefined.BYTE_OF_INT)
                return byteOfInt(exp.pos, value);
            if (exp.funcName == OpPredefined.INT_OF_BYTE)
                return intOfByte(exp.pos, value);
            return Value.NONE;
        }

        @Override
        public Value visit(ExpNew exp) {
            Value sizeValue = exp.exp.accept(this);
            assert sizeValue instanceof ValueInt :
                    "Internal Error: size is not a ValueInt " + exp.pos;
            int size = ((ValueInt) sizeValue).value;
            return new ValueArray(exp.type.type, size);
        }

        @Override
        public Value visit(ExpArrAccess exp) {
            Value indexValue = exp.index.accept(this);
            Value arrayValue = exp.array.accept(this);
            assert indexValue instanceof ValueInt :
                    "Internal Error: array index is not a ValueInt";
            int index = ((ValueInt) indexValue).value;
            assert arrayValue instanceof ValueArray :
                    "Internal Error: array is not a ValueArray";
            List<Value> array = ((ValueArray) arrayValue).getValues();
            if (!(0 <= index && index < array.size()))
                errorReporter.report(Status.ARRAY_ACCESS_OUT_OF_BOUNDS, exp.pos);
            return ((ValueArray) arrayValue).getValues().get(index);
        }

        @Override
        public Value visit(ExpArrEnum array) {
	    // evaluate the list of expressions to obtain values
	    // then build a value array
	    List<Value> values = new ArrayList<Value>();
	    for (Expression exp : array.exps) {
		    values.add(exp.accept(this));
	    }
	    return new ValueArray(values);
        }

        @Override
        public Value visit(StmIf stm) {
	   		Value conditionVal = stm.condition.accept(this);
			assert conditionVal instanceof ValueBool :
				"Internal Error: if statement conditional is not of type bool";
			if (((ValueBool)conditionVal).value) {
				return stm.then_branch.accept(this);
			}
			else {
				if (stm.else_branch.isPresent()) {
					return stm.else_branch.get().accept(this);
				}
				else {
					return Value.NONE;
				}
			}
        }

        @Override
        public Value visit(StmAssign stm) {
            if (stm.op.isPresent())
                errorReporter.report(Status.UNSUPPORTED,
				     "assignment with operator", stm.pos);
            Map<String, Value> environment = callStack.peek();
            Value value = stm.exp.accept(this);
            if (stm.lValue instanceof ExpVar) {
                String variable = ((ExpVar) stm.lValue).name;
                environment.remove(variable);
                environment.put(variable, value);
                return Value.NONE;
            }
            if (stm.lValue instanceof ExpArrAccess) {
                Value indexValue = ((ExpArrAccess) stm.lValue).index.accept(this);
                assert indexValue instanceof ValueInt :
		    "Internal Error: index not evaluated to a ValueInt";
                Value arrayValue = ((ExpArrAccess) stm.lValue).array.accept(this);
                assert arrayValue instanceof ValueArray :
		    "Internal Error: array not evaluated to a ValueArray";
                int index = ((ValueInt) indexValue).value;
                List<Value> array = ((ValueArray) arrayValue).getValues();
                if (!(0 <= index && index < array.size()))
                    errorReporter.report(Status.ARRAY_ACCESS_OUT_OF_BOUNDS,
					 stm.lValue.pos);
                array.set(index, value);
                return Value.NONE;
            }
            assert false :
		"Internal Error: assignable value not ExpVar or ExpArrAccess";
            return null;
        }

        @Override
        public Value visit(StmExp stm) {
            stm.exp.accept(this);
            return Value.NONE;
        }

        @Override
        public Value visit(StmRead stm) {
            String line = System.console().readLine();
            Expression expression = null;
            try {
                if (stm.type.type == Basic.INT)
                    expression = new ExpInt(stm.pos, Integer.parseInt(line));
                if (stm.type.type == Basic.CHAR)
                    expression = new ExpChar(stm.pos, line.charAt(0));
                if (stm.type.type == Basic.BYTE)
                    expression = new ExpPredefinedCall(stm.pos, OpPredefined.BYTE_OF_INT,
                            MakeList.one(new ExpInt(stm.pos, Integer.parseInt(line))));
                if (stm.type.type == Basic.BOOL)
                    if (line.equals("T")) {
					expression = new ExpBool(stm.pos, true);
				}
				else {
					expression = new ExpBool(stm.pos, false);
				}
                if (stm.type.type.equals(type.Array.stringType))
                    expression = new ExpString(stm.pos, line);
            } catch (NumberFormatException e) {
                errorReporter.report(Status.READ_ERROR, "input value is not a number", stm.pos);
            } catch (ArrayIndexOutOfBoundsException e) {
                errorReporter.report(Status.READ_ERROR, "empty input", stm.pos);
            }
            if (expression == null)
                errorReporter.report(Status.UNSUPPORTED, "read only supports basic types and strings", stm.pos);
            new StmAssign(stm.pos, stm.exp, expression).accept(this);
            return Value.NONE;
        }

        @Override
        public Value visit(StmPrint stm) {
            Value value = stm.exp.accept(this);
            System.out.print(value);
            return Value.NONE;
        }

        @Override
        public Value visit(StmReturn stm) {
            return stm.exp.accept(this);
        }

        @Override
        public Value visit(StmWhile stm) {
            Value condition;
            Value value = Value.NONE;
            if (stm.doWhile)
                do {
                    value = stm.body.accept(this);
                    if (value != Value.NONE) return value;
                    condition = stm.condition.accept(this);
                    assert condition instanceof ValueBool : "Internal Error: condition: " + stm.pos;
                }
			 while (((ValueBool) condition).value);
            else {
                condition = stm.condition.accept(this);
                assert condition instanceof ValueBool : "Internal Error: condition: " + stm.pos;
                while (((ValueBool) condition).value) {
                    value = stm.body.accept(this);
                    if (value != Value.NONE) return value;
                    condition = stm.condition.accept(this);
                }
            }
            return value;
        }

        @Override
        public Value visit(StmFor stm) {
		   Value collectionVal = stm.collection.accept(this);
		   assert collectionVal instanceof ValueArray :
		   		"Internal Error: collection is not iterable";
		   for (Value val : ((ValueArray)collectionVal).getValues()) {
			   stm.body.accept(this);
		   }

		   return Value.NONE;
        }

        @Override
        public Value visit(StmDecl stm) {
            Map<String, Value> currentEnvironment = callStack.peek();
            String name = stm.binding.getFst();
            currentEnvironment.remove(name);
            type.Type type = stm.binding.getSnd().type;
            if (stm.initialization.isPresent()) {
                Value value = stm.initialization.get().accept(this);
                currentEnvironment.put(name, value);
            } else
                currentEnvironment.put(name, Value.defaultValue(type));
            return Value.NONE;
        }

        @Override
        public Value visit(Type type) {
            assert false : "Internal Error: visiting Type";
            return null;
        }

        @Override
        public Value visit(Block block) {
            for (Statement stm : block.statements) {
                Value value = stm.accept(this);
                if (value != Value.NONE)
                    return value;
            }
            return Value.NONE;
        }

        @Override
        public Value visit(FunctionDefinition fun) {
            assert false : "Internal Error: Visiting FunctionDefinition";
            return null;
        }

        @Override
        public Value visit(Program program) {
            for (FunctionDefinition fun : program.functions)
                functions.put(fun.name, fun);
            FunctionDefinition main = functions.get("main");
            Value value = Value.NONE;
            if (main != null) {
                if (main.returnType.isPresent() && !main.returnType.equals(Basic.INT))
                    errorReporter.report(Status.MAIN_RETURN_TYPE, main.pos);
                callStack.push(new HashMap<>());
                List<Pair<Pair<String, Type>, Boolean>> arguments = main.arguments;
                if (0 < arguments.size()) {
                    type.Type expectedParameterType = new Array(type.Array.stringType);
                    if (1 < arguments.size())
                        errorReporter.report(Status.MAIN_PARAMETER_COUNT, main.pos);
                    if (!arguments.get(0).getFst().getSnd().type.equals(expectedParameterType))
                        errorReporter.report(Status.MAIN_PARAMETER_TYPE,
                                "it should be " + expectedParameterType, main.pos);
                    String argName = arguments.get(0).getFst().getFst();
                    List<Value> values = new LinkedList<>();
                    for (String s : this.arguments)
                        values.add(valueOfString(s));
                    callStack.peek().put(argName, new ValueArray(values));
                }

                Block mainBody = main.body;
                value = mainBody.accept(this);
            } else errorReporter.report(Status.NO_MAIN, program.pos);
            callStack.pop();
            return value;
        }
    }
}
