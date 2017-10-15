package lang;

import lang.ast.ErrorMessage;
import lang.ast.Function;
import lang.ast.Program;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

/**
 * This is a parameterized test: one test case is generated for each input
 * file found in TEST_DIRECTORY. Input files should have the ".in" extension.
 */
@RunWith(Parameterized.class)
public class TestReachability {
    /** Directory where the test input files are stored. */
    private static final File TEST_DIRECTORY = new File("testfiles/reachability");

    private final String filename;
    public TestReachability(String testFile) {
        filename = testFile;
    }


    @Test public void runTest() throws Exception {
        PrintStream out = System.out;
        try {
            Program program = (Program) Util.parse(new File(TEST_DIRECTORY, filename));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));

            StringBuilder sb = new StringBuilder();
            for (ErrorMessage m : program.errors()) {
                sb.append(m).append("\n");
            }

            if (!program.errors().isEmpty())
                System.out.println(sb.toString());
            else {
                for (Function f : program.getFunctions()) {
                    for (Function _f : f.reachability()) {
                        System.out.println(f.getIdDecl().getID() + ": " + _f.getIdDecl().getID());
                    }
                }
            }

            Util.compareOutput(baos.toString(),
                    new File(TEST_DIRECTORY, Util.changeExtension(filename, ".out")),
                    new File(TEST_DIRECTORY, Util.changeExtension(filename, ".expected")));

        } finally {
            System.setOut(out);
        }
    }

    @Parameters(name = "{0}")
    public static Iterable<Object[]> getTests() {
        return Util.getTestParameters(TEST_DIRECTORY, ".in");
    }

}