package lang;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import lang.CheckInteractiveVisitor;
import lang.ast.Program;

/**
 * This is a parameterized test: one test case is generated for each input
 * file found in TEST_DIRECTORY. Input files should have the ".in" extension.
 */
@RunWith(Parameterized.class)
public class TestVisitor {
	/** Directory where the test input files are stored. */
	private static final File TEST_DIRECTORY = new File("testfiles/visitor");

	private final String filename;
	public TestVisitor(String testFile) {
		filename = testFile;
	}

	@Test public void runTest() throws Exception {
		try (ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				PrintStream outStream = new PrintStream(bytes)) {
			Program program = (Program) Util.parse(new File(TEST_DIRECTORY, filename));
			outStream.println("Found interactive statement(s): " + CheckInteractiveVisitor.result(program));
		  String actual = bytes.toString();
		  Util.compareOutput(actual,
				  new File(TEST_DIRECTORY, Util.changeExtension(filename, ".out")),
				  new File(TEST_DIRECTORY, Util.changeExtension(filename, ".expected")));
    }
	}

	@Parameters(name = "{0}")
	public static Iterable<Object[]> getTests() {
		return Util.getTestParameters(TEST_DIRECTORY, ".in");
	}
}
