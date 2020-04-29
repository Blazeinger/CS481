package ast;

public enum OpUnary {
    SUB {
        @Override
        public String toString() {
            return "-";
        }
    },
    NOT {
        @Override
        public String toString() {
            return "!";
        }
    }
}
