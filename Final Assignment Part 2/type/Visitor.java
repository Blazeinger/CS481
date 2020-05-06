package type;

public interface Visitor<T>
{
    public T visit(Array type);
    public T visit(Int type);
    public T visit(Bool type);
    public T visit(Char type);
    public T visit(Byte type);
    public T visit(Float type);
}
