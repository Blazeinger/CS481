package interpreter.value;

import java.util.ArrayList;
import java.util.List;

public class ValueArray extends Value {
    private List<Value> values;

    public ValueArray(List<Value> values) {
        this.values = values;
    }


    public ValueArray(type.Type cellType, int size) {
        assert (0 <= size);
        List<Value> values = new ArrayList<>(size);
        for (int index = 0; index < size; index++)
            values.add(Value.defaultValue(cellType));
        this.values = values;
    }

    public List<Value> getValues() {
        return values;
    }

    @Override
    public String toString() {
        if (values.size() > 0 && values.get(0) instanceof ValueChar) {
            StringBuilder string = new StringBuilder();
            for (Value c : values)
                string.append(((ValueChar) c).value);
            return string.toString();
        }
        return values.toString()
                .replace('[', '{')
                .replace(']', '}');
    }
}
