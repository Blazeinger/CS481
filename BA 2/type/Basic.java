package type;

abstract public class Basic extends type.Type {

    public static final Basic INT = new Int();
    public static final Basic BOOL = new Bool();
    public static final Basic BYTE = new Byte();
    public static final Basic FLOAT = new Float();
    public static final Basic CHAR = new Char();

    public static class Int extends Basic {
        private Int(){}
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
        @Override
        public String toString() { return "int"; }
    }
    public static class Bool extends Basic {
        private Bool(){}
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
        @Override
        public String toString() { return "char"; }
    }
    public static class Byte extends Basic {
        private Byte(){}
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
        @Override
        public String toString() { return "byte"; }
    }
    public static class Char extends Basic {
        private Char(){}
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
        @Override
        public String toString() { return "char"; }
    }
    public static class Float extends Basic {
        private Float(){}
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
        @Override
        public String toString() { return "float"; }
    }
}