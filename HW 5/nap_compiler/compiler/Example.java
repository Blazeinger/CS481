package compiler;

import ast.*;
import compiler.*;
import java.util.*;
import javafx.util.*;

class Example {

    // Fake position as the AST is not obtained from
    // a parse tree
    static final Position pos = new Position(0,0);
    
    static Statement exampleNestedIf(Block doSomething,
				     Block doSomethingElse,
				     Block doSomethingDifferent){
	// Object for NAP Syntax: x == 1
	Expression condition1 =
	    new ExpBinop(pos,
			 new ExpVar(pos, "x"),
			 OpBinary.EQ,
			 new ExpInt(pos, 1));
	// Object for NAP Syntax: x == 2
	Expression condition2 =
	    new ExpBinop(pos,
			 new ExpVar(pos, "x"),
			 OpBinary.EQ,
			 new ExpInt(pos, 2));
	// Object for NAP Syntax:
	//  if (x == 2){ # do something else
	    //  } else { # do something different
	    //  }
	List<Statement> stms = new ArrayList<>(); // Empty List of Statements
	stms.add(new StmIf(pos, condition2,
			   doSomethingElse, doSomethingDifferent));
	return new StmIf(pos, condition1, doSomething, new Block(pos, stms));
    }
    
    static Program exampleFunctions(){
	Type int_t = new TypBasic(BasicType.INT);
	List<FunctionDefinition> functions =
	    new ArrayList<>();
	List<Pair<Pair<String, Type>, Boolean>> arguments =
	    new ArrayList<>();
	List<Statement> body =
	    new ArrayList<>();
	// Swap
	arguments.add(new Pair<>(new Pair<>("x", int_t),
				 Boolean.TRUE));
	arguments.add(new Pair<>(new Pair<>("y", int_t),
				 Boolean.TRUE));
	body.add(new StmDecl(pos, "tmp", int_t, new ExpVar(pos, "x")));
	body.add(new StmAssign(pos, "x", new ExpVar(pos, "y")));
	body.add(new StmAssign(pos, "y", new ExpVar(pos, "tmp")));
	functions.add(new FunctionDefinition(pos, "swap", arguments,
					     new Block(pos, body)));
	// Double
	arguments = new ArrayList<>();
	body = new ArrayList<>();
	arguments.add(new Pair<>(new Pair<>("x", int_t),
				 Boolean.FALSE));
	body.add(new StmReturn(pos,
			       new ExpBinop(pos,
					    new ExpInt(pos, 2),
					    OpBinary.MUL,
					    new ExpVar(pos, "x"))));
	functions.add(new FunctionDefinition(pos,
					     "double",
					     arguments,
					     new Block(pos,body),
					     int_t));

	// Incr
	arguments = new ArrayList<>();
	body = new ArrayList<>();
	arguments.add(new Pair<>(new Pair<>("x", int_t),
				 Boolean.TRUE));
	body.add(new StmAssign(pos, "x", new ExpInt(pos, 1), OpBinary.ADD));
	functions.add(new FunctionDefinition(pos, "incr", arguments,
					     new Block(pos, body)));
	return new Program(pos, functions);
    }

    //public static void main(String [] argv){
    //PrettyPrinter pp = new PrettyPrinter(2);
    //System.out.print(exampleFunctions().accept(pp));
    //}
}
