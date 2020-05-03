package type;

public class Float extends Basic {
    Float() {
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "float";
    }
}
