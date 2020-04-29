package util;

import ast.Position;

public class ErrorReporter<Status extends Enum<Status>>
{
    private String label;

    public ErrorReporter(String label) {
        this.label = label;
    }

    public void report(Status status){
        System.exit(status.ordinal());
    }

    public void report(Status status, String message){
        System.err.println(label + ": " + status + " " + message);
        report(status);
    }

    public void report(Status status, Position pos) {
        report(status, "", pos);
    }

    public void report(Status status, String message, Position pos) {
        report(status, message + " " + pos);
    }
}
