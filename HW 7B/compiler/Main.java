package compiler;

import ast.Program;
import ir.Frame;
import ir.com.Command;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.napLexer;
import parser.napParser;
import util.Pair;

import java.io.*;
import java.util.List;

public class Main {

    private static void exitWithStatus(Status status){
        System.exit(-status.ordinal());
    }

    private static InputStream getStream(String name) throws FileNotFoundException {
        if (name == null)
            return System.in;
        else
            return new FileInputStream(new File(name));
    }

    private static ParseTree parse(InputStream stream) throws IOException {
        CharStream input = CharStreams.fromStream(stream);
        // Creation of the lexer for NAP programs
        napLexer lexer = new napLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Creation of the parser for NAP programs
        napParser parser = new napParser(tokens);
        // Parse the input: the result is a parse tree
        ParseTree tree = parser.program();
        if (parser.getNumberOfSyntaxErrors() != 0)
            exitWithStatus(Status.PARSE_ERROR);
        return tree;
    }

    private static Program buildAst(ParseTree tree){
        BuildAST astBuilder = new BuildAST();
        return (Program) astBuilder.visit(tree);
    }

    private static SymbolTable buildSymbolTable(Program program){
        SymbolTableBuilder symbolTableBuilder = new SymbolTableBuilder();
        program.accept(symbolTableBuilder);
        if (symbolTableBuilder.hasErrors()) {
            symbolTableBuilder.printErrors();
            exitWithStatus(Status.DECLARATION_ERROR);
        }
        /*ReturnChecker.checker.run(program);
        if(ReturnChecker.checker.has_errors()) {
            ReturnChecker.checker.printErrors();
            exitWithStatus(Status.RETURN_STATEMENT_ERROR);
        }*/
        return symbolTableBuilder.getSymbolTable();
    }

    private static void typeCheck(Program program, SymbolTable symbolTable){
        TypeChecker typeChecker = new TypeChecker(symbolTable);
        program.accept(typeChecker);
        if (typeChecker.hasErrors()) {
            typeChecker.printErrors();
            exitWithStatus(Status.TYPE_ERROR);
        }
    }

    private static void print(Program program){
        System.out.print(program.accept(new PrettyPrinter(2)));
    }

    private static void translate(SymbolTable symbolTable, Program program) {
        List<Pair<Frame, List<Command>>> translation = ir.translation.Translate.run(symbolTable, program);
        for (Pair<Frame, List<Command>> fragment : translation) {
            String entry = fragment.getFst().getEntryPoint().toString().replace(":","");
            System.out.println("==== FRAME " + entry + " ====");
            System.out.println("  " + fragment.getFst());
            System.out.println("  FRAGMENT: " + fragment.getSnd());
            System.out.println("==== END FRAME ====");
        }
    }

    public static void main(String [] args) throws IOException {
        String name = (args.length < 1)?null:args[0];
        InputStream stream = getStream(name);
        Program program = buildAst(parse(stream));
        SymbolTable symbolTable = buildSymbolTable(program);
        typeCheck(program, symbolTable);
        program = ExpAssignopElimination.transform(program);
        symbolTable = buildSymbolTable(program);
        translate(symbolTable, program);
    }
}
