package type;

public class Bool extends Basic {
    Bool() {
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "bool";
    }
}
