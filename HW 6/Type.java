package compiler;
import ast.TypBasic;
import java.util.*;
public class Type
{
    private Optional<TypBasic> type;

    public static final Type integer = new Type(TypBasic.INT);
    public static final Type bool = new Type(TypBasic.BOOL);
    public static final Type byte = new Type(TypBasic.BYTE);
    public static final Type float = new Type(TypBasic.FLOAT);
    public static final Type char = new Type(TypBasic.CHAR);
    public static final Type none = new Type();

    private Type(){ this.type = Optional.empty(); }
    private Type(TypBasic type){ this.type = Optional.of(type); }

    public boolean isType() { return type.isPresent(); }
    public TypBasic get() { return type.get(); }

    @Override
    public String toString(){
	if (type.isPresent())
	    return type.get().toString();
	else
	    return "none";
    }
}
