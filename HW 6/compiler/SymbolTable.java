package compiler;

import ast.Block;

import java.util.HashMap;

public class SymbolTable{
	private Block block;
	private HashMap<String, type.Type> table;

	public SymbolTable( Block block){
		this.block = block;
		table = new HashMap<String, type.Type>();
	}

	public void put(String symbol, type.Type type){
		table.put(symbol, type);
	}

	public type.Type get(String symbol){
		return table.get(symbol);
	}
}
