package compiler;

import ast.*;

import java.util.HashMap;

public class GlobalTable{
	private HashMap<String, Signature> table;

	public GlobalTable(){
		table = new HashMap<String, Signature>();
	}

	public void put(String symbol, Signature signature){
		table.put(symbol, signature);
	}

	public Signature get(String symbol){
		return table.get(symbol);
	}
}
