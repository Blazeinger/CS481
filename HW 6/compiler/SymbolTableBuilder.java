package compiler;

import ast.*;
import util.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

public class SymbolTableBuilder implements ast.Visitor<Void>{
	private GlobalTable globTable; // stores functions
	private HashMap<Block, Block> blockParentDic; // <child, parent>
	private HashMap<Block, SymbolTable> localTables; // associates a block
										    // to a symbol table
	private ArrayList<String> errors;
	private Block currentBlock = null;

	public SymbolTableBuilder(){
		globTable = new GlobalTable();
		blockParentDic = new HashMap<Block, Block>();
		localTables = new HashMap<Block, SymbolTable>();
		errors = new ArrayList<String>();
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
		if(localTables.get(block).get(symbol) != null){
			localTables.get(block).put(symbol, type);
		}
		errors.add("Symbol " + symbol + " already declared\n");
	}

	public void addFuncSig(String symbol, Signature signature){
		if(globTable.get(symbol) != null){
			globTable.put(symbol, signature);
		}
		errors.add("Function " + symbol + " already declared.\n");
	}

	public boolean has_errors(){
		return !errors.isEmpty();
	}

	public void printErrors(){
		for(int index = 0; index < errors.size(); index++){
			System.out.println(errors.get(index));
		}
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
		// TODO: ?
	  	return null;
	}

	public Void visit(ExpUnop exp){
		// TODO: ?
		return null;
	}

	public Void visit(ExpAssignop exp){
		// TODO: ?
	  	return null;
	}

	public Void visit(ExpFuncCall exp){
	  	return null;
	}

	public Void visit(ExpPredefinedCall exp){
	  	return null;
	}

	public Void visit(ExpNew exp){
		// TODO: ?
	  	return null;
	}

	public Void visit(ExpArrAccess array){
	  	return null;
	}

	public Void visit(ExpArrEnum array){
		// TODO: ?
	  	return null;
	}

	public Void visit(StmIf stm){
		// TODO: ?
	  	return null;
	}

	public Void visit(StmAssign stm){
		// TODO: ?
	  	return null;
	}

	public Void visit(StmExp stm){
		// TODO: ?
	  	return null;
	}

	public Void visit(StmRead stm){
		// TODO: ?
	  	return null;
	}

	public Void visit(StmPrint stm){
		// TODO: ?
	  	return null;
	}

	public Void visit(StmReturn stm){
		// TODO: ?
	  	return null;
	}

	public Void visit(StmWhile stm){
		// TODO: ?
	  	return null;
	}

	public Void visit(StmFor stm){
		// TODO: add to table <k,v> -> <String, type>
		return null;
	}

	public Void visit(StmDecl stm){
		addToLocalTable(currentBlock, stm.binding.getFst(), stm.binding.getSnd().type);
		return null;
	}

	public Void visit(Type type){
		return null;
	}

	// TODO: Figure out how to change the current block pointer when
	// "unvisiting" a block
	public Void visit(Block block){
		createLocalTable(block, currentBlock);
		currentBlock = block;
		return null;
	}

	public Void visit(FunctionDefinition fun){
		List<Pair<Pair<String, ast.Type>, Boolean>> tempArgs = fun.arguments;
		List<Pair<Pair<String, type.Type>, Boolean>> newArgs =
			new ArrayList<Pair<Pair<String, type.Type>, Boolean>>();
		for(int index = 0; index < tempArgs.size(); index++){
			// TODO: convert ast.Type to type.Type
		}
		try{
			addFuncSig(fun.name, Signature.buildFunction(newArgs, fun.returnType.get().type));
		}
		catch(NoSuchElementException e){
			addFuncSig(fun.name, Signature.buildFunction(newArgs, null));
		}
		return null;
	}

	public Void visit(Program program){
		return null;
	}

}
