package ast;
import java.util.*;
public class StmAssign extends Statement {
    public String var;
    public Expression exp;
    public Optional<OpBinary> op;
    public StmAssign(Position pos, String var, Expression exp){
	this.pos = pos;
	this.var = var;
	this.exp = exp;
	this.op = Optional.empty();
    }
    public StmAssign(Position pos, String var, Expression exp, OpBinary op){
	assert( op == OpBinary.ADD || op == OpBinary.SUB ||
		op == OpBinary.MUL || op == OpBinary.DIV );
	this.pos = pos;
	this.var = var;
	this.exp = exp;
	this.op = Optional.of(op);
    }
    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }
}
