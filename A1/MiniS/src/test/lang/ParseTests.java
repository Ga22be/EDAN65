package lang;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.fail;

public class ParseTests {
	/** Directory where the test input files are stored. */
	private static final File TEST_DIRECTORY = new File("testfiles");

	@Test public void allLanguage() {
		Util.testValidSyntax(TEST_DIRECTORY, "exampleProgram.in");
	}

	@Test public void sh0rt() {
		Util.testValidSyntax(TEST_DIRECTORY, "short.in");
	}

	@Test public void errorOnParse() {
		Util.testSyntaxError(TEST_DIRECTORY, "errorParse.in");
	}

	@Test public void l2t() {
		Util.testSyntaxError(TEST_DIRECTORY, "lolLearnToType.in");
	}
}
