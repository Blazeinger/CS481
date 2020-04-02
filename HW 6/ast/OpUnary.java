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
   },
    INCR {
        @Override
        public String toString() {
            return "++";
        }
   },
   DECR {
	  @Override
	  public String toString() {
		 return "--";
	  }
   }
}
