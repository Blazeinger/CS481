package interpreter.value;

import java.util.Objects;

public class ValueBool extends Value
{
    public boolean value;

    public ValueBool(){
        this.value = false;
    }

    public ValueBool(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value?"T":"F";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueBool valueBool = (ValueBool) o;
        return value == valueBool.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
