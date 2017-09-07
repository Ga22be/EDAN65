package lang;

import lang.ast.LangParser.Terminals;
import lang.ast.LangParser;
import lang.ast.LangScanner;

public class RecursiveDescentParser extends RDPTemplate {

	protected void parseProgram(){
		ensureStmt();
		accept(Terminals.EOF);
	}

	protected void ensureStmt() {
		switch(peek()) {
			case Terminals.IF:
				accept(Terminals.IF);
				ensureExpr();
				accept(Terminals.THEN);
				ensureStmt();
				accept(Terminals.FI);
				break;
			case Terminals.FOR:
				accept(Terminals.FOR);
				accept(Terminals.ID);
				accept(Terminals.ASSIGN);
				ensureExpr();
				accept(Terminals.UNTIL);
				ensureExpr();
				accept(Terminals.DO);
				ensureStmt();
				accept(Terminals.OD);
				break;
			case Terminals.ID:
				accept(Terminals.ID);
				accept(Terminals.ASSIGN);
				ensureExpr();
				break;
			default: error("unexpected token: stmt");
		}
	}

	protected void ensureExpr(){
		switch(peek()){
			case Terminals.ID:
				accept(Terminals.ID);
				break;
			case Terminals.NUMERAL:
				accept(Terminals.NUMERAL);
				break;
			case Terminals.NOT:
				accept(Terminals.NOT);
				ensureExpr();
				break;
			default: error("unexpected token: expr");
		}
	}

}
