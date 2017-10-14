package lang;

import beaver.Parser;
import lang.ast.LangParser;
import lang.ast.LangScanner;
import lang.ast.Program;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Interpreter {
    /**
     * Entry point
     * @param args
     */

    public static Object DrAST_root_node; //Enable debugging with DrAST

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.err.println(
                        "You must specify a source file on the command line!");
                printUsage();
                System.exit(1);
                return;
            }

            String filename = args[0];
            LangScanner scanner = new LangScanner(new FileReader(filename));
            LangParser parser = new LangParser();
            Program program = (Program) parser.parse(scanner);
            DrAST_root_node = program; //Enable debugging with DrAST

            if(program.errors().isEmpty()) {
                program.eval();
            }
            //System.out.println(program.dumpTree());
            //program.prettyPrint(System.out);
            //program.checkNames(System.err);
            //System.out.println("The Maximal Statement Nesting for the program is: " + CalculateMSNVisitor.result(program));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } catch (Parser.Exception e) {
            e.printStackTrace();
        }
    }

    private static void printUsage() {
        System.err.println("Usage: DumpTree FILE");
        System.err.println("  where FILE is the file to be parsed");
    }
}
