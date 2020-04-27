package ast;

/*@ This visit methods of this visitor always return
    the default value specified while calling the
    constructor of the class. All the nodes are
    visited, and for nodes with several children,
    only the value obtained for the last visited child
    is returned. The order is the same than [VisitorBase].
 */
public class VisitorDefault<T> extends VisitorBase<T> {

    private T defaultValue;

    public VisitorDefault(T defaultValue){
        this.defaultValue = defaultValue;
    }

    private T toDefault(T value){
        if (value == null)
            return defaultValue;
        return value;
    }

    @Override
    public T visit(ExpBool exp) {
        return defaultValue;
    }

    @Override
    public T visit(ExpChar exp) {
        return defaultValue;
    }

    @Override
    public T visit(ExpInt exp) {
        return defaultValue;
    }

    @Override
    public T visit(ExpString exp) {
        return defaultValue;
    }

    @Override
    public T visit(ExpVar exp) {
        return defaultValue;
    }

    @Override
    public T visit(Type type) {
        return defaultValue;
    }

    @Override
    public T visit(ExpFuncCall exp) {
        return toDefault(super.visit(exp));
    }

    @Override
    public T visit(ExpPredefinedCall exp) {
        return toDefault(super.visit(exp));
    }

    @Override
    public T visit(ExpArrEnum array) {
        return toDefault(super.visit(array));
    }

    @Override
    public T visit(Block block) {
        return toDefault(super.visit(block));
    }

    @Override
    public T visit(Program program) {
        return toDefault(super.visit(program));
    }
}
