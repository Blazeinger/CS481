package ast;

import java.util.*; import javafx.util.*;

public class StmDecl extends Statement {
    public Pair<String, Type> binding;
    public Optional<Expression> initialization;
    // ...
    public StmDecl(Position pos, String name, Type type){
	this.pos = pos;
	this.binding = new Pair<String, Type>(name, type);
	this.initialization = Optional.empty();
    }
    public StmDecl(Position pos, String name,
		       Type type, Expression val){
	this.pos = pos;
	this.binding = new Pair<String, Type>(name, type);
	this.initialization = Optional.of(val);
    }
    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }
}
