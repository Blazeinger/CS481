package nap;

import ast.BuildAST;
import semantic_analysis.ReturnChecker;
import semantic_analysis.SymbolTable;
import semantic_analysis.SymbolTableBuilder;
import semantic_analysis.TypeChecker;
import transform.AssignSimplifier;
import transform.ExpAssignopElimination;
import transform.ForElimination;
import ast.Program;
import printing.*;
//import interpreter.Interpreter;
import ir.Frame;
import ir.com.Command;
import ir.com.Label;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.napLexer;
import parser.napParser;
import util.ErrorReporter;
import util.Pair;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Main {

    private static ErrorReporter<nap.Status> error = new ErrorReporter<>("NAP system error");

    private static ParseTree parse(InputStream stream) throws IOException {
        CharStream input = CharStreams.fromStream(stream);
        // Creation of the lexer for pico programs
        napLexer lexer = new napLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Creation of the parser for pico programs
        napParser parser = new napParser(tokens);
        // Parse the input: the result is a parse tree
        ParseTree tree = parser.program();
        if (parser.getNumberOfSyntaxErrors() != 0)
            error.report(Status.PARSE_ERROR);
        return tree;
    }

    private static Program buildAst(ParseTree tree) {
        BuildAST astBuilder = new BuildAST();
        return (Program) astBuilder.visit(tree);
    }

    private static SymbolTable buildSymbolTable(Program program) {
        SymbolTableBuilder symbolTableBuilder = new SymbolTableBuilder();
        program.accept(symbolTableBuilder);
        if (symbolTableBuilder.hasErrors()) {
            symbolTableBuilder.printErrors();
            error.report(Status.DECLARATION_ERROR);
        }
        ReturnChecker.checker.run(program);
        if (ReturnChecker.checker.hasErrors()) {
            ReturnChecker.checker.printErrors();
            error.report(Status.RETURN_STATEMENT_ERROR);
        }
        return symbolTableBuilder.getSymbolTable();
    }

    private static void typeCheck(Program program, SymbolTable symbolTable) {
        TypeChecker typeChecker = new TypeChecker(symbolTable);
        program.accept(typeChecker);
        if (typeChecker.hasErrors()) {
            typeChecker.printErrors();
            error.report(Status.TYPE_ERROR);
        }
    }

    private static void print(Program program) {
        System.out.print(program.accept(new PrettyPrinter(2)));
    }

    private static Path changeExtension(Path path, String oldExt, String newExt) {
        PathMatcher pm = FileSystems.getDefault()
                .getPathMatcher("glob:*" + oldExt);
        if (pm.matches(path.getFileName())) {
            String nameWithExtension = path.getFileName().toString();
            int endIndex = nameWithExtension.length() - oldExt.length();
            String name = nameWithExtension.substring(0, endIndex);
            if (path.getParent() != null)
                return path.getParent().resolve(name + newExt);
            else
                return FileSystems.getDefault().getPath(name + newExt);
        }
        return path;
    }

    private static void compile(Path path,
                                Label mainLabel,
                                List<Pair<Frame, List<Command>>> fragments) {
        Path newPath = FileSystems.getDefault().getPath(
                changeExtension(path, ".nap", ".asm").getFileName().toString());
        mips.Program.generate(newPath, mainLabel, fragments);
    }


    private static void printIR(Pair<Label, List<Pair<Frame, List<Command>>>> translation) {
        for (Pair<Frame, List<Command>> fragment : translation.getSnd()) {
            System.out.println("==== FRAME: " + fragment.getFst().getEntryPoint());
            System.out.println("\tFRAME INFO: " + fragment.getFst());
            System.out.println("\tCODE: " + fragment.getSnd());
            System.out.println("==== END FRAME");
        }
    }

    private static InputStream getStream(Optional<Path> name) {
        try {
            if (name.isPresent())
                return new FileInputStream(new File(name.get().toUri()));
        } catch (FileNotFoundException e) {
            error.report(Status.FILE_NOT_FOUND, name.get().toString());
        }
        return System.in;
    }

    private static void printUsage(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            URL url = classLoader.getResource("nap/usage.txt");
            List<String> lines = Files.readAllLines(Paths.get(url.toURI()));
            for(String line: lines) System.out.println(line);
        } catch (URISyntaxException | IOException e) {
            error.report(Status.RESOURCE_ERROR);
        }
        error.report(Status.SUCCESS);
    }

    public static Perform parseArguments(String[] args) {
        Perform perform = new Perform();
        if (args.length == 0) perform.setPrinting();
        for (int i = 0; i < args.length; i++) {
            String argument = args[i];
            switch (argument) {
                case "-p":
                    perform.setParsing();
                    break;
                case "-b":
                    perform.setBuildingAST();
                    break;
                case "-pp":
                    perform.setPrinting();
                    break;
                case "-s":
                    perform.setBuildingSymbolTable();
                    break;
                case "-t":
                    perform.setTypeChecking();
                    break;
                case "-tr":
                    perform.setTranslating();
                    break;
                case "-ef":
                    perform.setEliminatingFor();
                    break;
                case "-ea":
                    perform.setEliminatingAssignop();
                    break;
                case "-i":
		    		perform.setInterpreting();
                    break;
                case "-sa":
                    perform.setSimplifyingAssignment();
                    break;
                case "-c":
                    perform.setCompiling();
                    break;
                case "-v":
                    perform.setVerbose();
                    break;
                case "-h":
                    printUsage();
                    break;
                default:
                    Path path = FileSystems.getDefault().getPath(argument);
                    PathMatcher pm = FileSystems.getDefault()
                            .getPathMatcher("glob:*.nap");
                    if (pm.matches(path.getFileName()) && !perform.getSourceFile().isPresent()) {
                        perform.setSourceFile(path);
                        List<String> commandLineArguments = new LinkedList<>();
                        for (int k = i + 1; k < args.length; k++)
                            commandLineArguments.add(args[i]);
                        perform.setCommandLineArguments(commandLineArguments);
                        break;
                    }
                    if (pm.matches(path.getFileName()) && perform.getSourceFile().isPresent())
                        error.report(Status.COMMAND_LINE_ERROR, "NAP source file already given");
                    error.report(Status.COMMAND_LINE_ERROR, "Unknown command line argument: " + argument);
            }
        }
        return perform;
    }

    public static void main(String[] args) throws Exception {
        Perform perform = parseArguments(args);
        ParseTree tree = null;
        Program program = null;
        SymbolTable symbolTable = null;
        Pair<Label, List<Pair<Frame, List<Command>>>> translation = null;
        if (perform.isParsing()) {
            if (perform.isVerbose()) System.out.print("[Parsing]: ");
            tree = parse(getStream(perform.getSourceFile()));
            if (perform.isVerbose()) System.out.println("done");
        }
        if (perform.isBuildingAST()) {
            if (perform.isVerbose()) System.out.print("[BuildingAST]: ");
            program = buildAst(tree);
            if (perform.isVerbose()) System.out.println("done");
        }
        if (perform.isBuildingSymbolTable()) {
            if (perform.isVerbose()) System.out.print("[BuildingSymbolTable]: ");
            symbolTable = buildSymbolTable(program);
            if (perform.isVerbose()) System.out.println("done");
        }
        if (perform.isTypeChecking()) {
            if (perform.isVerbose()) System.out.print("[TypeChecking]: ");
            typeCheck(program, symbolTable);
            if (perform.isVerbose()) System.out.println("done");
        }
        if (perform.isEliminatingFor()) {
            if (perform.isVerbose()) System.out.print("[EliminatingFor]: ");
            program = ForElimination.transform(program);
            symbolTable = buildSymbolTable(program);
            if (perform.isVerbose()) System.out.println("done");
        }
        if (perform.isEliminatingAssignop()) {
            if (perform.isVerbose()) System.out.print("[EliminatingExpAssignop]: ");
            program = ExpAssignopElimination.transform(program);
            symbolTable = buildSymbolTable(program);
            if (perform.isVerbose()) System.out.println("done");
        }
        if (perform.isSimplifyingAssignment()) {
            if (perform.isVerbose()) System.out.print("[SimplifyingAssignment]: ");
            program = AssignSimplifier.transform(program);
            symbolTable = buildSymbolTable(program);
            if (perform.isVerbose()) System.out.println("done");
        }
        if (perform.isPrinting())
            print(program);
        if (perform.isInterpreting())
            //Interpreter.run(program, perform.getCommandLineArguments());
        if (perform.isTranslating()) {
            if (perform.isVerbose()) System.out.print("[TranslationToIR]: ");
            translation = ir.translation.Translate.run(symbolTable, program);
            if (!perform.isCompiling() && perform.isVerbose())
                printIR(translation);
            if (perform.isVerbose()) System.out.println("done");
        }
        if (perform.isCompiling()) {
            if (perform.isVerbose()) System.out.print("[CompilationToMIPS]: ");
            Path path = perform.getSourceFile().isPresent() ?
                    perform.getSourceFile().get() :
                    FileSystems.getDefault().getPath("a.out.asm");
            compile(path, translation.getFst(), translation.getSnd());
            if (perform.isVerbose()) System.out.println("done");
        }
        error.report(Status.SUCCESS);
    }
}
