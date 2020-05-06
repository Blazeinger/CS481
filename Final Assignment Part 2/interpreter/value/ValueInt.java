package interpreter.value;

import java.util.Objects;

public class ValueInt extends Value
{
    public int value;

    public ValueInt() {
        this.value = 0;
    }

    public ValueInt(int value){
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
        ValueInt valueInt = (ValueInt) o;
        return value == valueInt.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}