package compiler;

import ast.*;
import parser.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // FIRST STEP: analysis
        // Creation of the stream of characters
        InputStream stream;
        if (args.length == 0)
            stream = System.in;
        else
            stream = new FileInputStream(new File(args[0]));
        CharStream input = CharStreams.fromStream(stream);
        // Creation of the lexer for pico programs
        napLexer lexer = new napLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Creation of the parser for pico programs
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
        // Your code for this conditional may be different
        if (symbolTableBuilder.has_errors()) {
            symbolTableBuilder.printErrors();
            System.exit(-2);
        }
        System.out.print(program.accept(new PrettyPrinter(2)));
    }
}
