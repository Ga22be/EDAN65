package lang;

import lang.ast.*;

/**
 * Traverses each node, passing the data to the children.
 * Returns the data unchanged.
 * Overriding methods may change the data passed and the data returned.
 */

public abstract class TraversingVisitor implements lang.ast.Visitor {

    protected Object visitChildren(ASTNode node, Object data) {
        for (int i = 0; i < node.getNumChild(); ++i) {
            node.getChild(i).accept(this, data);
        }
        return data;
    }

    public Object visit(List node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Opt node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Program node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Function node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Parameter node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Return node, Object data) {
        return visitChildren(node, data);
    }

    public Object visit(IntType node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(BoolType node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(UnknownType node, Object data) {
        return visitChildren(node, data);
    }

    public Object visit(IfStatement node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Else node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(WhileStatement node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(FunctionCall node, Object data) {
        return visitChildren(node, data);
    }

    public Object visit(LesserThan node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(GreaterThan node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Equal node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(NotEqual node, Object data) {
        return visitChildren(node, data);
    }

    public Object visit(Declaration node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Assignment node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(DeclAssignment node, Object data) {
        return visitChildren(node, data);
    }

    public Object visit(Add node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Sub node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Mul node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Div node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(Mod node, Object data) {
        return visitChildren(node, data);
    }

    public Object visit(Numeral node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(IdDecl node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(IdUse node, Object data) {
        return visitChildren(node, data);
    }
    public Object visit(FunctionUse node, Object data) {
        return visitChildren(node, data);
    }
}