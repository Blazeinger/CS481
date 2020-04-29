package ast;

public abstract class Ast {
    public Position pos;
    abstract public <T> T accept(Visitor<T> visitor);
}
