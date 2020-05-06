package interpreter.value;

import java.util.Objects;

public class ValueChar extends Value {
    public char value;

    public ValueChar() {
        this.value = '\0';
    }

    public ValueChar(char value) {
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
        ValueChar valueChar = (ValueChar) o;
        return value == valueChar.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
