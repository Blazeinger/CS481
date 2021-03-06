// Generated from nap.g4 by ANTLR 4.7.1
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class napLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Bool=1, Int=2, PInt=3, Char=4, String=5, Escape=6, AssignOp=7, WS=8, TRUE=9, 
		FALSE=10, FOR=11, WHILE=12, DO=13, IF=14, ELSE=15, ARROW=16, IN=17, AEQ=18, 
		SEQ=19, MEQ=20, DEQ=21, QUOTE=22, SQUOTE=23, BACKSLASH=24, LBLOCK=25, 
		RBLOCK=26, LBRACKET=27, RBRACKET=28, INCR=29, DECR=30, ADD=31, AND=32, 
		OR=33, MUL=34, SUB=35, DIV=36, MOD=37, EQ=38, NEQ=39, LT=40, GT=41, LE=42, 
		GE=43, ASSIGN=44, NOT=45, ARRAY=46, BOOL=47, BYTE=48, INT=49, FLOAT=50, 
		CHAR=51, FUNC=52, LPAR=53, RPAR=54, COMMA=55, REF=56, VAR=57, INPUT=58, 
		PRINT=59, NEW=60, Identifier=61, Comments=62;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"Bool", "Int", "PInt", "Char", "String", "Escape", "AssignOp", "WS", "TRUE", 
		"FALSE", "FOR", "WHILE", "DO", "IF", "ELSE", "ARROW", "IN", "AEQ", "SEQ", 
		"MEQ", "DEQ", "QUOTE", "SQUOTE", "BACKSLASH", "LBLOCK", "RBLOCK", "LBRACKET", 
		"RBRACKET", "INCR", "DECR", "ADD", "AND", "OR", "MUL", "SUB", "DIV", "MOD", 
		"EQ", "NEQ", "LT", "GT", "LE", "GE", "ASSIGN", "NOT", "ARRAY", "BOOL", 
		"BYTE", "INT", "FLOAT", "CHAR", "FUNC", "LPAR", "RPAR", "COMMA", "REF", 
		"VAR", "INPUT", "PRINT", "NEW", "Identifier", "Comments"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, "'T'", "'F'", "'for'", 
		"'while'", "'do'", "'if'", "'else'", "'->'", "'in'", "'+='", "'-='", "'*='", 
		"'/='", "'''", "'\"'", "'\\'", "'{'", "'}'", "'['", "']'", "'++'", "'--'", 
		"'+'", "'&&'", "'||'", "'*'", "'-'", "'/'", "'mod'", "'=='", "'!='", "'<'", 
		"'>'", "'<='", "'>='", "'='", "'!'", "'array'", "'bool'", "'byte'", "'int'", 
		"'float'", "'char'", "'func'", "'('", "')'", "','", "'ref'", "'var'", 
		"'read'", "'print'", "'new'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Bool", "Int", "PInt", "Char", "String", "Escape", "AssignOp", "WS", 
		"TRUE", "FALSE", "FOR", "WHILE", "DO", "IF", "ELSE", "ARROW", "IN", "AEQ", 
		"SEQ", "MEQ", "DEQ", "QUOTE", "SQUOTE", "BACKSLASH", "LBLOCK", "RBLOCK", 
		"LBRACKET", "RBRACKET", "INCR", "DECR", "ADD", "AND", "OR", "MUL", "SUB", 
		"DIV", "MOD", "EQ", "NEQ", "LT", "GT", "LE", "GE", "ASSIGN", "NOT", "ARRAY", 
		"BOOL", "BYTE", "INT", "FLOAT", "CHAR", "FUNC", "LPAR", "RPAR", "COMMA", 
		"REF", "VAR", "INPUT", "PRINT", "NEW", "Identifier", "Comments"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public napLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "nap.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2@\u0168\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\3\2\3\2\5\2\u0082\n\2\3\3\3\3\3\3\3\3\5\3\u0088\n\3\3\4\6"+
		"\4\u008b\n\4\r\4\16\4\u008c\3\5\3\5\3\5\5\5\u0092\n\5\3\5\3\5\3\6\3\6"+
		"\3\6\7\6\u0099\n\6\f\6\16\6\u009c\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\5\7\u00a6\n\7\3\b\3\b\5\b\u00aa\n\b\3\t\6\t\u00ad\n\t\r\t\16\t\u00ae"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3$\3$"+
		"\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,"+
		"\3-\3-\3.\3.\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61"+
		"\3\61\3\61\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64"+
		"\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39"+
		"\39\39\39\3:\3:\3:\3:\3;\3;\3;\3;\3;\3<\3<\3<\3<\3<\3<\3=\3=\3=\3=\3>"+
		"\3>\7>\u015b\n>\f>\16>\u015e\13>\3?\3?\7?\u0162\n?\f?\16?\u0165\13?\3"+
		"?\3?\2\2@\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65"+
		"i\66k\67m8o9q:s;u<w=y>{?}@\3\2\n\3\2\62;\4\2\"(*\u0080\4\2\"#%\u0080\4"+
		"\2ppvv\5\2\13\f\17\17\"\"\5\2C\\aac|\6\2\62;C\\aac|\3\2\f\f\2\u0175\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2"+
		"\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{"+
		"\3\2\2\2\2}\3\2\2\2\3\u0081\3\2\2\2\5\u0087\3\2\2\2\7\u008a\3\2\2\2\t"+
		"\u008e\3\2\2\2\13\u0095\3\2\2\2\r\u009f\3\2\2\2\17\u00a9\3\2\2\2\21\u00ac"+
		"\3\2\2\2\23\u00b2\3\2\2\2\25\u00b4\3\2\2\2\27\u00b6\3\2\2\2\31\u00ba\3"+
		"\2\2\2\33\u00c0\3\2\2\2\35\u00c3\3\2\2\2\37\u00c6\3\2\2\2!\u00cb\3\2\2"+
		"\2#\u00ce\3\2\2\2%\u00d1\3\2\2\2\'\u00d4\3\2\2\2)\u00d7\3\2\2\2+\u00da"+
		"\3\2\2\2-\u00dd\3\2\2\2/\u00df\3\2\2\2\61\u00e1\3\2\2\2\63\u00e3\3\2\2"+
		"\2\65\u00e5\3\2\2\2\67\u00e7\3\2\2\29\u00e9\3\2\2\2;\u00eb\3\2\2\2=\u00ee"+
		"\3\2\2\2?\u00f1\3\2\2\2A\u00f3\3\2\2\2C\u00f6\3\2\2\2E\u00f9\3\2\2\2G"+
		"\u00fb\3\2\2\2I\u00fd\3\2\2\2K\u00ff\3\2\2\2M\u0103\3\2\2\2O\u0106\3\2"+
		"\2\2Q\u0109\3\2\2\2S\u010b\3\2\2\2U\u010d\3\2\2\2W\u0110\3\2\2\2Y\u0113"+
		"\3\2\2\2[\u0115\3\2\2\2]\u0117\3\2\2\2_\u011d\3\2\2\2a\u0122\3\2\2\2c"+
		"\u0127\3\2\2\2e\u012b\3\2\2\2g\u0131\3\2\2\2i\u0136\3\2\2\2k\u013b\3\2"+
		"\2\2m\u013d\3\2\2\2o\u013f\3\2\2\2q\u0141\3\2\2\2s\u0145\3\2\2\2u\u0149"+
		"\3\2\2\2w\u014e\3\2\2\2y\u0154\3\2\2\2{\u0158\3\2\2\2}\u015f\3\2\2\2\177"+
		"\u0082\5\23\n\2\u0080\u0082\5\25\13\2\u0081\177\3\2\2\2\u0081\u0080\3"+
		"\2\2\2\u0082\4\3\2\2\2\u0083\u0088\5\7\4\2\u0084\u0085\5G$\2\u0085\u0086"+
		"\5\7\4\2\u0086\u0088\3\2\2\2\u0087\u0083\3\2\2\2\u0087\u0084\3\2\2\2\u0088"+
		"\6\3\2\2\2\u0089\u008b\t\2\2\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2"+
		"\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\b\3\2\2\2\u008e\u0091\5"+
		"-\27\2\u008f\u0092\t\3\2\2\u0090\u0092\5\r\7\2\u0091\u008f\3\2\2\2\u0091"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\5-\27\2\u0094\n\3\2\2\2"+
		"\u0095\u009a\5/\30\2\u0096\u0099\t\4\2\2\u0097\u0099\5\r\7\2\u0098\u0096"+
		"\3\2\2\2\u0098\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u009e\5/"+
		"\30\2\u009e\f\3\2\2\2\u009f\u00a5\5\61\31\2\u00a0\u00a6\t\5\2\2\u00a1"+
		"\u00a6\5-\27\2\u00a2\u00a6\5/\30\2\u00a3\u00a6\5\61\31\2\u00a4\u00a6\7"+
		"\62\2\2\u00a5\u00a0\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a5\u00a2\3\2\2\2\u00a5"+
		"\u00a3\3\2\2\2\u00a5\u00a4\3\2\2\2\u00a6\16\3\2\2\2\u00a7\u00aa\5;\36"+
		"\2\u00a8\u00aa\5=\37\2\u00a9\u00a7\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa\20"+
		"\3\2\2\2\u00ab\u00ad\t\6\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\b\t"+
		"\2\2\u00b1\22\3\2\2\2\u00b2\u00b3\7V\2\2\u00b3\24\3\2\2\2\u00b4\u00b5"+
		"\7H\2\2\u00b5\26\3\2\2\2\u00b6\u00b7\7h\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9"+
		"\7t\2\2\u00b9\30\3\2\2\2\u00ba\u00bb\7y\2\2\u00bb\u00bc\7j\2\2\u00bc\u00bd"+
		"\7k\2\2\u00bd\u00be\7n\2\2\u00be\u00bf\7g\2\2\u00bf\32\3\2\2\2\u00c0\u00c1"+
		"\7f\2\2\u00c1\u00c2\7q\2\2\u00c2\34\3\2\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5"+
		"\7h\2\2\u00c5\36\3\2\2\2\u00c6\u00c7\7g\2\2\u00c7\u00c8\7n\2\2\u00c8\u00c9"+
		"\7u\2\2\u00c9\u00ca\7g\2\2\u00ca \3\2\2\2\u00cb\u00cc\7/\2\2\u00cc\u00cd"+
		"\7@\2\2\u00cd\"\3\2\2\2\u00ce\u00cf\7k\2\2\u00cf\u00d0\7p\2\2\u00d0$\3"+
		"\2\2\2\u00d1\u00d2\7-\2\2\u00d2\u00d3\7?\2\2\u00d3&\3\2\2\2\u00d4\u00d5"+
		"\7/\2\2\u00d5\u00d6\7?\2\2\u00d6(\3\2\2\2\u00d7\u00d8\7,\2\2\u00d8\u00d9"+
		"\7?\2\2\u00d9*\3\2\2\2\u00da\u00db\7\61\2\2\u00db\u00dc\7?\2\2\u00dc,"+
		"\3\2\2\2\u00dd\u00de\7)\2\2\u00de.\3\2\2\2\u00df\u00e0\7$\2\2\u00e0\60"+
		"\3\2\2\2\u00e1\u00e2\7^\2\2\u00e2\62\3\2\2\2\u00e3\u00e4\7}\2\2\u00e4"+
		"\64\3\2\2\2\u00e5\u00e6\7\177\2\2\u00e6\66\3\2\2\2\u00e7\u00e8\7]\2\2"+
		"\u00e88\3\2\2\2\u00e9\u00ea\7_\2\2\u00ea:\3\2\2\2\u00eb\u00ec\7-\2\2\u00ec"+
		"\u00ed\7-\2\2\u00ed<\3\2\2\2\u00ee\u00ef\7/\2\2\u00ef\u00f0\7/\2\2\u00f0"+
		">\3\2\2\2\u00f1\u00f2\7-\2\2\u00f2@\3\2\2\2\u00f3\u00f4\7(\2\2\u00f4\u00f5"+
		"\7(\2\2\u00f5B\3\2\2\2\u00f6\u00f7\7~\2\2\u00f7\u00f8\7~\2\2\u00f8D\3"+
		"\2\2\2\u00f9\u00fa\7,\2\2\u00faF\3\2\2\2\u00fb\u00fc\7/\2\2\u00fcH\3\2"+
		"\2\2\u00fd\u00fe\7\61\2\2\u00feJ\3\2\2\2\u00ff\u0100\7o\2\2\u0100\u0101"+
		"\7q\2\2\u0101\u0102\7f\2\2\u0102L\3\2\2\2\u0103\u0104\7?\2\2\u0104\u0105"+
		"\7?\2\2\u0105N\3\2\2\2\u0106\u0107\7#\2\2\u0107\u0108\7?\2\2\u0108P\3"+
		"\2\2\2\u0109\u010a\7>\2\2\u010aR\3\2\2\2\u010b\u010c\7@\2\2\u010cT\3\2"+
		"\2\2\u010d\u010e\7>\2\2\u010e\u010f\7?\2\2\u010fV\3\2\2\2\u0110\u0111"+
		"\7@\2\2\u0111\u0112\7?\2\2\u0112X\3\2\2\2\u0113\u0114\7?\2\2\u0114Z\3"+
		"\2\2\2\u0115\u0116\7#\2\2\u0116\\\3\2\2\2\u0117\u0118\7c\2\2\u0118\u0119"+
		"\7t\2\2\u0119\u011a\7t\2\2\u011a\u011b\7c\2\2\u011b\u011c\7{\2\2\u011c"+
		"^\3\2\2\2\u011d\u011e\7d\2\2\u011e\u011f\7q\2\2\u011f\u0120\7q\2\2\u0120"+
		"\u0121\7n\2\2\u0121`\3\2\2\2\u0122\u0123\7d\2\2\u0123\u0124\7{\2\2\u0124"+
		"\u0125\7v\2\2\u0125\u0126\7g\2\2\u0126b\3\2\2\2\u0127\u0128\7k\2\2\u0128"+
		"\u0129\7p\2\2\u0129\u012a\7v\2\2\u012ad\3\2\2\2\u012b\u012c\7h\2\2\u012c"+
		"\u012d\7n\2\2\u012d\u012e\7q\2\2\u012e\u012f\7c\2\2\u012f\u0130\7v\2\2"+
		"\u0130f\3\2\2\2\u0131\u0132\7e\2\2\u0132\u0133\7j\2\2\u0133\u0134\7c\2"+
		"\2\u0134\u0135\7t\2\2\u0135h\3\2\2\2\u0136\u0137\7h\2\2\u0137\u0138\7"+
		"w\2\2\u0138\u0139\7p\2\2\u0139\u013a\7e\2\2\u013aj\3\2\2\2\u013b\u013c"+
		"\7*\2\2\u013cl\3\2\2\2\u013d\u013e\7+\2\2\u013en\3\2\2\2\u013f\u0140\7"+
		".\2\2\u0140p\3\2\2\2\u0141\u0142\7t\2\2\u0142\u0143\7g\2\2\u0143\u0144"+
		"\7h\2\2\u0144r\3\2\2\2\u0145\u0146\7x\2\2\u0146\u0147\7c\2\2\u0147\u0148"+
		"\7t\2\2\u0148t\3\2\2\2\u0149\u014a\7t\2\2\u014a\u014b\7g\2\2\u014b\u014c"+
		"\7c\2\2\u014c\u014d\7f\2\2\u014dv\3\2\2\2\u014e\u014f\7r\2\2\u014f\u0150"+
		"\7t\2\2\u0150\u0151\7k\2\2\u0151\u0152\7p\2\2\u0152\u0153\7v\2\2\u0153"+
		"x\3\2\2\2\u0154\u0155\7p\2\2\u0155\u0156\7g\2\2\u0156\u0157\7y\2\2\u0157"+
		"z\3\2\2\2\u0158\u015c\t\7\2\2\u0159\u015b\t\b\2\2\u015a\u0159\3\2\2\2"+
		"\u015b\u015e\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d|\3"+
		"\2\2\2\u015e\u015c\3\2\2\2\u015f\u0163\7%\2\2\u0160\u0162\n\t\2\2\u0161"+
		"\u0160\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2"+
		"\2\2\u0164\u0166\3\2\2\2\u0165\u0163\3\2\2\2\u0166\u0167\b?\2\2\u0167"+
		"~\3\2\2\2\16\2\u0081\u0087\u008c\u0091\u0098\u009a\u00a5\u00a9\u00ae\u015c"+
		"\u0163\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}