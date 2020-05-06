package interpreter.value;

import java.util.Objects;

public class ValueByte extends Value {
    public byte value;

    public ValueByte() {
        this.value = 0;
    }

    public ValueByte(byte value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueByte valueByte = (ValueByte) o;
        return value == valueByte.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
