package ast;

public abstract class Expression extends Ast {
    public abstract <T> T accept(Visitor<T> visitor);
}
