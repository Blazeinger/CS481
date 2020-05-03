package mips;

import ir.Frame;
import ir.com.Command;
import ir.com.Label;
import util.ErrorReporter;
import util.Pair;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Program {

    static final int DEFAULT_SIZE = 4;

    private static final ErrorReporter<mips.Status> errorReporter =
	new ErrorReporter<>("MIPS Code Generation");

    public static class AsmWriter extends PrintWriter{

        AsmWriter(Path path) throws FileNotFoundException {
            super(new OutputStreamWriter(new FileOutputStream(path.toFile())));
        }

        void transferFrom(BufferedReader in) {
            try {
                while (in.ready())
                    println(in.readLine());
            } catch (IOException e) {
                errorReporter.report(Status.IO_ERROR, "Transferring resources");
            }
        }

        void writeAllLines(List<String> lines){
            for (String line : lines)
                println(line);
        }
    }

    static public void framesGeneration(AsmWriter output,
                                        List<Pair<Frame, List<Command>>> fragments) {
        mips.Frame frameGenerator = new mips.Frame(errorReporter);
        for (Pair<Frame, List<Command>> fragment : fragments)
            output.writeAllLines(frameGenerator.generate(fragment));
    }

    static private void mipsTextGeneration(AsmWriter output, Label mainLabel) {
        List<String> asmCode = new LinkedList<>();

	   asmCode.add(Asm.directive("data"));
        asmCode.add(Asm.directive("text"));
        asmCode.add(Asm.label("main"));
        asmCode.add( Asm.command("jal "+mainLabel) );
        asmCode.addAll( Asm.exit() );

        output.writeAllLines(asmCode);
    }

    static private void supportGeneration(AsmWriter output) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            URL url = classLoader.getResource("mips/resources/list.txt");
            List<String> files = Files.readAllLines(Paths.get(url.toURI()));
            BufferedReader in;
            for (String file : files) {
                in = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(file)));
                output.transferFrom(in);
                in.close();
            }
        } catch (URISyntaxException | IOException e) {
            errorReporter.report(Status.RESOURCES_ERROR);
        }
    }


    static public void generate(Path path, Label mainLabel,
                                List<Pair<Frame, List<Command>>> fragments) {
        try {
            AsmWriter output = new AsmWriter(path);
            mipsTextGeneration(output, mainLabel);
            framesGeneration(output, fragments);
            supportGeneration(output);
            output.close();
        } catch (FileNotFoundException e) {
            errorReporter.report(Status.FILE_NOT_FOUND, path.toString());
        }
    }
}
