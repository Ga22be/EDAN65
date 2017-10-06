package lang;

import lang.ast.*;
import java.util.TreeSet;

/**
 * Calculates the MSN depth of a SimpliC file
 */
public class CalculateMSNVisitor extends TraversingVisitor{
    public static int result(ASTNode n) {
        CalculateMSNVisitor v = new CalculateMSNVisitor();
        n.accept(v, 0);
        Integer[] msnArray = v.biggestMSN.toArray(new Integer[v.biggestMSN.size()]);

        return msnArray[msnArray.length-1];
    }

    private TreeSet<Integer> biggestMSN = new TreeSet<>();

    public Object visit(Function node, Object data) {
        int currentMSN = (int) data;
        add(++currentMSN);
        return visitChildren(node, currentMSN);
    }

    private int max;
    private void add(int i) {
        if (i > max) max = i;
        biggestMSN.add(++i);
    }

    public Object visit(IfStatement node, Object data) {
        int currentMSN = (int) data;
        add(++currentMSN);
        return visitChildren(node, currentMSN);
    }

    public Object visit(WhileStatement node, Object data) {
        int currentMSN = (int) data;
        add(++currentMSN);
        return visitChildren(node, currentMSN);
    }
}
