package lang;

import lang.ast.*;

/**
 * Checks if there are any 'ask user' statements in a Calc program.
 */
public class CheckInteractiveVisitor extends TraversingVisitor {
	public static boolean result(ASTNode n) {
		CheckInteractiveVisitor v = new CheckInteractiveVisitor();
		n.accept(v, null);
		return v.isInteractive;
	}

	private boolean isInteractive = false;

	public Object visit(Ask node, Object data) {
		isInteractive = true;
		return data;
	}
}
