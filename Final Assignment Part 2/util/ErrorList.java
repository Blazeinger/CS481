package util;

import java.util.LinkedList;
import java.util.List;

public class ErrorList
{
    private List<String> errors;

    public void addError(String error){
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void printErrors(){
        for (String error : errors)
            System.out.println(error);
    }

    public void resetErrors(){
        errors = new LinkedList<>();
    }

    public ErrorList() {
        errors = new LinkedList<>();
    }
}
