package ir.com;

public class Label extends Command{
    private String label;
    private static int lastLabel;



    @Override
    public String toString() {
        return "L" + label + ": ";
    }

    public Label() {
        this.label = ""+lastLabel;
        lastLabel += 1;
    }

    public Label( String label ){
        this.label = label;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
