package interpreter.value;

import type.Array;
import type.Type;

abstract public class Value {
    private static final Default defaultVisitor = new Default();
    public static Value NONE = new None();

    static public Value defaultValue(Type type) {
        return type.accept(defaultVisitor);
    }

    private static class None extends Value {
        @Override
        public String toString() {
            return "None";
        }
    }

    private static class Default implements type.Visitor<Value> {
        @Override
        public Value visit(Array type) {
            return null;
        }

        @Override
        public Value visit(type.Int type) {
            return new ValueInt();
        }

        @Override
        public Value visit(type.Bool type) {
            return new ValueBool();
        }

        @Override
        public Value visit(type.Char type) {
            return new ValueChar();
        }

        @Override
        public Value visit(type.Byte type) {
            return new ValueByte();
        }

        @Override
        public Value visit(type.Float type) {
            assert false : "Values of type float are not supported yet";
            return null;
        }
    }
}
