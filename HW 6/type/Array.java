package type;

public class Array extends Type {
    public Type type;

    public Array(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "array<" + type + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Array arrayType = (Array) obj;
        return type.equals(arrayType.type);
    }
}
