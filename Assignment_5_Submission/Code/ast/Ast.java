package ast;

public abstract class Ast {
    Position pos;
    abstract public <T> T accept(Visitor<T> visitor);
}
