package lang;

import java.io.*;
import java.util.ArrayList;

import beaver.Parser.Exception;

import lang.ast.Program;
import lang.ast.LangParser;
import lang.ast.LangScanner;

import static java.lang.System.out;

/**
 * Dumps the parsed Abstract Syntax Tree of a Calc program.
 */
public class Compiler {
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

			// Generate Assembly file.
			File assemblyFile = new File(filename.substring(0, filename.length() - 3) + ".s");
			program.genCode(out);
			out.close();

			// Generate object file.
			File objectFile = new File(filename.substring(0, filename.length() - 3) + ".o");
			ArrayList<String> cmdAs = new ArrayList<String>();
			cmdAs.add("as");
			cmdAs.add("--gstabs");
			cmdAs.add(assemblyFile.getAbsolutePath());
			cmdAs.add("-o");
			cmdAs.add(objectFile.getAbsolutePath());

			// Link object file and generate executable file.
			File execFile = new File(filename.substring(0, filename.length() - 3));
			ArrayList<String> cmdLd = new ArrayList<String>();
			cmdLd.add("ld");
			cmdLd.add(objectFile.getAbsolutePath());
			cmdLd.add("-o");
			cmdLd.add(execFile.getAbsolutePath());

			ProcessBuilder pb = new ProcessBuilder(cmdAs);
			Process process = pb.start();
			process.getOutputStream().close();

			ProcessBuilder pb2 = new ProcessBuilder(cmdLd);
			Process process2 = pb2.start();
			process2.getOutputStream().close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printUsage() {
		System.err.println("Usage: java -jar compiler.jar FILE");
		System.err.println("  where FILE is the file to be parsed");
	}
}

