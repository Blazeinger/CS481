package ast;

public enum OpBinary {
    ADD { 
        @Override
        public String toString() {
            return "+";
        }
    },
    SUB {
        @Override
        public String toString() {
            return "-";
        }
    },
    MUL {
        @Override
        public String toString() {
            return "*";
        }
    },
    DIV {
        @Override
        public String toString() {
            return "/";
        }
    },
    MOD {
        @Override
        public String toString() {
            return "mod";
        }
    },
    LT {
        @Override
        public String toString() {
            return "<";
        }
    },
    GT {
        @Override
        public String toString() {
            return ">";
        }
    },
    LE {
        @Override
        public String toString() {
            return "<=";
        }
    },
    GE {
        @Override
        public String toString() {
            return ">=";
        }
    },
    NEQ {
        @Override
        public String toString() {
            return "!=";
        }
    },
    EQ {
        @Override
        public String toString() {
            return "==";
        }
    },
    AND {
        @Override
        public String toString() {
            return "&&";
        }
    },
    OR {
        @Override
        public String toString() {
            return "||";
        }
    }
}

