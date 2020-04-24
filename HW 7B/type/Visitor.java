package type;

public interface Visitor<T>
{
    public T visit(Array type);
    public T visit(Basic.Int type);
    public T visit(Basic.Bool type);
    public T visit(Basic.Char type);
    public T visit(Basic.Byte type);
    public T visit(Basic.Float type);
}
