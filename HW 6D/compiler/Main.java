package compiler;

import ast.*;
import parser.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

	   // takes nap program directory from command line
        InputStream stream;
        if (args.length == 0)
            stream = System.in;
        else
            stream = new FileInputStream(new File(args[0]));

	   // into charater stream for parsing
        CharStream input = CharStreams.fromStream(stream);

	   // Creation of the lexer for nap programs
        napLexer lexer = new napLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

	   // Creation of the parser for nap programs
        napParser parser = new napParser(tokens);

	   // Parse the input: the result is a parse tree
        ParseTree tree = parser.program();
        if (parser.getNumberOfSyntaxErrors() != 0)
            System.exit(-1);

	   // Walk the parse tree in order to create an
        // abstract syntax tree
        napVisitor<Ast> buildAST = new BuildAST();
        Program program = (Program) buildAST.visit(tree);
        SymbolTableBuilder symbolTableBuilder = new SymbolTableBuilder();
        program.accept(symbolTableBuilder);

	   // From there your code may be different
        if (symbolTableBuilder.has_errors()) {
            symbolTableBuilder.printErrors();
            System.exit(-2);
        }

	   // Perform type checking
        SymbolTable symbolTable = symbolTableBuilder.getSymbolTable();
        TypeChecker typeChecker = new TypeChecker(symbolTable);
        program.accept(typeChecker);
        if (typeChecker.has_errors()) {
            typeChecker.printErrors();
            System.exit(-3);
        }

        System.out.print(program.accept(new PrettyPrinter(2)));
    }
}
