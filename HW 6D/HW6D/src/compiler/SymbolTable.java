package compiler;

import ast.Block;
import ast.OpPredefined;

import javax.swing.text.html.Option;
import java.util.*;

class SymbolTable
{
    // functions is the symbol table for NAP function definitions
    private Map<String, Signature> functions;
    // blocks is a map that associate each block with its local table
    private Map<Block, Map<String, type.Type>> blocks;

    protected SymbolTable(){
        functions = new HashMap<>();
        blocks = new HashMap<>();
    }

    // Add a function's signature to the global symbol table and return
    // the previous signature, null is there was none.
    public Signature addFunction(String functionName, Signature signature){
        Signature previous = functions.put(functionName, signature);
        return previous;
    }

    // Add a variable's type to the local table of the given block and
    // return the previous type if any.
    public type.Type addVariable(Block block, String variable, type.Type type){
        return blocks.get(block).put(variable, type);
    }

    // If a local table already exists for the block, do nothing.
    // Otherwise create a new empty local table.
    public void createLocalTable(Block block){
       if (blocks.get(block) == null){
           Map<String, type.Type> localTable = new HashMap<>();
           blocks.put(block, localTable);
       }
    }

    public Optional<Signature> funcLookup(String functionName){
        Signature signature = functions.get(functionName);
        if (signature != null)
            return Optional.of(signature);
        return Optional.empty();
    }

    // lookup tries to get the type associated with a name.
    // It looks first in the current block, then its parent, etc.
    // Optional.empty() indicates here the search was unsuccessful.
    public Optional<type.Type> varLookup(String variable, VisitedBlocks visitedBlocks){
        for(Block block : visitedBlocks.getStack()){
            Map<String, type.Type> blockSymbolTable = blocks.get(block);
            assert(blockSymbolTable != null) :
                    "No local table associated to the block: please report";
            type.Type type = blockSymbolTable.get(variable);
            if (type != null)
                return Optional.of(type);
        }
        return Optional.empty();
    }
}
