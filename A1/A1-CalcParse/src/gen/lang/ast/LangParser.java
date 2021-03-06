package lang.ast;

import beaver.*;
import java.util.ArrayList;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.1
 * from the grammar specification "parser.beaver".
 */
public class LangParser extends Parser {
	static public class Terminals {
		static public final short EOF = 0;
		static public final short MUL = 1;
		static public final short IN = 2;
		static public final short END = 3;
		static public final short ID = 4;
		static public final short LET = 5;
		static public final short NUMERAL = 6;
		static public final short ASSIGN = 7;

		static public final String[] NAMES = {
			"EOF",
			"MUL",
			"IN",
			"END",
			"ID",
			"LET",
			"NUMERAL",
			"ASSIGN"
		};
	}

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9oLa3iFWa0GXES0bOSe64iB2miB2mjDJ4nCJ8o5lyfVBVZYd8K5YQ7n9j$jxktaE1R01GE" +
		"CqKCVZhqrUTHaiSrK5YvIcx7ZISU#LI$LK$LGtLM5Ag#402by91sxYo49CM8LC4QiurR#pG" +
		"03Xrw97bzKTzhd$qI$#gUENVg6Nn3lv8foRScKxCYCpCc2BCL3ZcHFJkH0pcH5rcH34hd7M" +
		"flhVMNTHhpgARHsVprwBYlWb0odOihv1Omr8LyUCVfaz04Vxbi2");

	static public class SyntaxError extends RuntimeException { public SyntaxError(String msg) {super(msg);}}
	// Disable syntax error recovery
	protected void recoverFromError(Symbol token, TokenStream in) {
		throw new SyntaxError("Cannot recover from the syntax error");
	}

	public LangParser() {
		super(PARSING_TABLES);
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		switch(rule_num) {
			case 1: // exp = factor
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1]); return new Symbol(lst);
			}
			case 2: // exp = exp MUL factor
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3]); return _symbols[offset + 1];
			}
			case 0: // program = exp
			case 3: // factor = let
			case 4: // factor = numeral
			case 5: // factor = id
			case 7: // numeral = NUMERAL
			case 8: // id = ID
			{
				return _symbols[offset + 1];
			}
			case 6: // let = LET id ASSIGN exp IN exp END
			{
				return _symbols[offset + 7];
			}
			default:
				throw new IllegalArgumentException("unknown production #" + rule_num);
		}
	}
}
