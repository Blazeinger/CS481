package type;

import java.util.Objects;

public class Array extends Type {
    public Type type;

    public Array(Type type) {
        this.type = type;
    }

    public final static Type stringType = new type.Array(Basic.CHAR);

    @Override
    public String toString() {
        return "array<" + this.type + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Array arrayType = (Array) obj;
        return type.equals(arrayType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
