package compiler;

import ast.Block;

import java.util.Stack;

public class VisitedBlocks {

    // Each time this visitor enters a block, it will push it on this
    // visitedBlocks stack. Each time it exits a block, it will pop
    // the stack. Therefore this stack contains at its top the current
    // visited block, and after its parent block, then its grand-parent
    // block, and so on.
    private Stack<Block> visitedBlocks;

    public VisitedBlocks() {
        this.visitedBlocks = new Stack<>();
    }

    public Stack<Block> getStack(){
        return visitedBlocks;
    }

    public void enter(Block b){
        visitedBlocks.push(b);
    }

    public void exit(){
        visitedBlocks.pop();
    }

    public Block current(){
        return visitedBlocks.peek();
    }

}
