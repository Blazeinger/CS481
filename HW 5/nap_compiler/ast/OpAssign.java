package ast;

public enum OpAssign {
  INC {
    @Override
    public String toString(){
      return "++";
    }
  },
  DEC {
    @Override
    public String toString(){
      return "--";
    }
  }
}
