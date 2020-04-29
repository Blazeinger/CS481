package ir;

public enum Type {
    BYTE {
        @Override
        public String toString() {
            return "byte";
        }
    },
    INT{
        @Override
        public String toString() {
            return "int";
        }
    },
    ADDRESS{
        @Override
        public String toString() {
            return "address";
        }
    }
}
