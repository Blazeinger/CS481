package nap;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class Perform {
    private Optional<Path> sourceFile;
    private List<String> commandLineArguments;
    private boolean parsing = false;
    private boolean buildingAST = false;
    private boolean buildingSymbolTable = false;
    private boolean typeChecking = false;
    private boolean printing = false;
    private boolean interpreting = false;
    private boolean compiling = false;
    private boolean eliminatingFor = false;
    private boolean simplifyingAssignment = false;
    private boolean translating = false;
    private boolean verbose = false;
    private boolean eliminatingAssignop = false;

    public Perform(){
        sourceFile = Optional.empty();
        commandLineArguments = new LinkedList<>();
    }

    public Optional<Path> getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(Path sourceFile) {
        this.sourceFile = Optional.of(sourceFile);
    }

    public List<String> getCommandLineArguments() {
        return commandLineArguments;
    }

    public boolean isEliminatingAssignop() {
        return eliminatingAssignop;
    }

    public void setEliminatingAssignop() {
        setBuildingAST();
        this.eliminatingAssignop = true;
    }

    public void setCommandLineArguments(List<String> commandLineArguments) {
        this.commandLineArguments = commandLineArguments;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose() {
        this.verbose = true;
    }

    public boolean isParsing() {
        return parsing;
    }

    public void setParsing() {
        this.parsing = true;
    }

    public boolean isBuildingAST() {
        return buildingAST;
    }

    public void setBuildingAST() {
        setParsing();
        this.buildingAST = true;
    }

    public boolean isBuildingSymbolTable() {
        return buildingSymbolTable;
    }

    public void setBuildingSymbolTable() {
        setBuildingAST();
        this.buildingSymbolTable = true;
    }

    public boolean isTypeChecking() {
        return typeChecking;
    }

    public void setTypeChecking() {
        setBuildingSymbolTable();
        this.typeChecking = true;
    }

    public boolean isPrinting() {
        return printing;
    }

    public void setPrinting() {
        setBuildingAST();
        this.printing = true;
    }

    public boolean isInterpreting() {
        return interpreting;
    }

    public void setInterpreting() {
        setTypeChecking();
        this.interpreting = true;
    }

    public boolean isCompiling() {
        return compiling;
    }

    public void setCompiling() {
        setTranslating();
        this.compiling = true;
    }

    public boolean isEliminatingFor() {
        return eliminatingFor;
    }

    public void setEliminatingFor() {
        setBuildingAST();
        this.eliminatingFor = true;
    }

    public boolean isSimplifyingAssignment() {
        return simplifyingAssignment;
    }

    public void setSimplifyingAssignment() {
        setBuildingAST();
        this.simplifyingAssignment = true;
    }

    public boolean isTranslating() {
        return translating;
    }

    public void setTranslating() {
        setTypeChecking();
        this.translating = true;
    }

    @Override
    public String toString() {
        return "Perform{" +
                "sourceFile=" + sourceFile +
                ", commandLineArguments=" + commandLineArguments +
                ", parsing=" + parsing +
                ", buildingAST=" + buildingAST +
                ", buildingSymbolTable=" + buildingSymbolTable +
                ", typeChecking=" + typeChecking +
                ", printing=" + printing +
                ", interpreting=" + interpreting +
                ", compiling=" + compiling +
                ", eliminatingFor=" + eliminatingFor +
                ", simplifyingAssignment=" + simplifyingAssignment +
                ", translating=" + translating +
                '}';
    }
}
