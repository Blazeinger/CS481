package type;

public class Byte extends Basic {
    Byte() {
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "byte";
    }
}
