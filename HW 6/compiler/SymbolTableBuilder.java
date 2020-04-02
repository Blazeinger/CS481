package compiler;

import ast.*;

import java.util.HashMap;

public class SymbolTableBuilder implements ast.Visitor<Void>{
	private GlobalTable globTable; // stores functions
	private HashMap<Block, Block> blockParentDic; // <child, parent>
	private HashMap<Block, SymbolTable> localTables; // associates a block
											  // to a symbol table

	public SymbolTableBuilder(){
		globTable = new GlobalTable();
		blockParentDic = new HashMap<Block, Block>();
		localTables = new HashMap<Block, SymbolTable>();
	}

	public type.Type getType(String symbol, Block block){
		SymbolTable tempTable = localTables.get(block);
		type.Type tempType = tempTable.get(symbol);
		return tempType;
	}

	public SymbolTable getLocalTable(Block block){
		return localTables.get(block);
	}

	public GlobalTable getGlobalTable(){
		return globTable;
	}

	public Block getParentBlock(Block block){
		return blockParentDic.get(block);
	}

	public void createLocalTable(Block block, Block parentBlock){
		localTables.put(block, new SymbolTable(block));
		blockParentDic.put(block, parentBlock);
	}

	public void addToLocalTable(Block block, String symbol, type.Type type){
		localTables.get(block).put(symbol, type);
	}

	public void addFuncSig(String symbol, Signature signature){
		globTable.put(symbol, signature);
	}

	public boolean has_errors(){
		return false; // TODO: implement
	}

	public void printErrors(){
		// TODO: implement
	}

	public Void visit(ExpBool exp){
		return null;
	}

	public Void visit(ExpChar exp){
		return null;
	}

	public Void visit(ExpInt exp){
		return null;
	}

	public Void visit(ExpString exp){
		return null;
	}

	public Void visit(ExpVar exp){
		return null;
	}

	public Void visit(ExpBinop exp){
	  return null;
	}

	public Void visit(ExpUnop exp){
		// TODO:
	  return null;
	}

	public Void visit(ExpAssignop exp){
		// TODO:
	  return null;
	}

	public Void visit(ExpFuncCall exp){
		// TODO:
	  return null;
	}

	public Void visit(ExpPredefinedCall exp){
		// TODO:
	  return null;
	}

	public Void visit(ExpNew exp){
		// TODO:
	  return null;
	}

	public Void visit(ExpArrAccess array){
		// TODO:
	  return null;
	}

	public Void visit(ExpArrEnum array){
		// TODO:
	  return null;
	}

	public Void visit(StmIf stm){
		// TODO:
	  return null;
	}

	public Void visit(StmAssign stm){
		// TODO:
	  return null;
	}

	public Void visit(StmExp stm){
		// TODO:
	  return null;
	}

	public Void visit(StmRead stm){
		// TODO:
	  return null;
	}

	public Void visit(StmPrint stm){
		// TODO:
	  return null;
	}

	public Void visit(StmReturn stm){
		// TODO:
	  return null;
	}

	public Void visit(StmWhile stm){
		// TODO:
	  return null;
	}

	public Void visit(StmFor stm){
		// TODO: add to table <k,v> -> <String, type>
		return null;
	}

	public Void visit(StmDecl stm){
		return null;
	}

	public Void visit(Type type){
		return null;
	}

	public Void visit(Block block){
		// TODO: create new symbol table for block
		//
		return null;
	}

	public Void visit(FunctionDefinition fun){
		// TODO: add symbol to global table
		return null;
	}

	public Void visit(Program program){
		// TODO: initializations of global table, etc...
		return null;
	}

}
