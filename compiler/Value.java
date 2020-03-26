package compiler;

import ast.TypBasic;

abstract class Value
{
    public boolean isType(){ return false; }
    public boolean isBool(){ return false; }
    public boolean isInt(){ return false; }

    public static class Type extends Value
    {
	public TypBasic value;
	@Override
	public boolean isType() { return true; }
	public Type(TypBasic type){ this.value = type; }
    }

    public static class Int extends Value
    {
	public int value;
	public boolean equals(Object o){
	    if(o instanceof Int)
		return ((Int)o).value == this.value;
	    else
		return false;
	}

	public boolean isInt() { return true; }
	public Int(int value){ this.value = value; }
    }

    public static class Bool extends Value
    {
	public boolean value;
	public boolean equals(Object o){
	    if(o instanceof Bool)
		return ((Bool)o).value == this.value;
	    else
		return false;
	}
	@Override
	public boolean isBool() { return true; }
	public Bool(boolean value){ this.value = value; }
    }

    public static final Value none = new None();

    private static class None extends Value
    { private None(){} }
}
