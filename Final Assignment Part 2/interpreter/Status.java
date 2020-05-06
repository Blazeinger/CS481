package interpreter;

public enum Status {
    SUCCESS,
    NO_MAIN {
        @Override
        public String toString() {
            return "No main function";
        }
    },
    MAIN_PARAMETER_COUNT {
        @Override
        public String toString() {
            return "main should have at most one parameter";
        }
    },
    MAIN_PARAMETER_TYPE {
        @Override
        public String toString() {
            return "Incorrect type for the parameter of main:";
        }
    },
    MAIN_RETURN_TYPE {
        @Override
        public String toString() {
            return "Incorrect return type for main";
        }
    },
    ARRAY_ACCESS_OUT_OF_BOUNDS{
        @Override
        public String toString() {
            return "Array access out of bounds";
        }
    },
    UNSUPPORTED{
        @Override
        public String toString() {
            return "Unsupported feature";
        }
    },
    READ_ERROR{
        @Override
        public String toString() {
            return "read error";
        }
    }
}
