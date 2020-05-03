package type;

public class Char extends Basic {
    Char() {
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "char";
    }
}
