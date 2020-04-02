// Generated from nap.g4 by ANTLR 4.7.1
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class napParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Bool=1, Int=2, PInt=3, Char=4, String=5, Escape=6, AssignOp=7, WS=8, TRUE=9, 
		FALSE=10, FOR=11, WHILE=12, DO=13, IF=14, ELSE=15, ARROW=16, IN=17, AEQ=18, 
		SEQ=19, MEQ=20, DEQ=21, QUOTE=22, SQUOTE=23, BACKSLASH=24, LBLOCK=25, 
		RBLOCK=26, LBRACKET=27, RBRACKET=28, INCR=29, DECR=30, ADD=31, AND=32, 
		OR=33, NOT=34, MUL=35, SUB=36, DIV=37, MOD=38, ASSIGN=39, EQ=40, NEQ=41, 
		LT=42, GT=43, LE=44, GE=45, ARRAY=46, BOOL=47, BYTE=48, INT=49, FLOAT=50, 
		CHAR=51, FUNC=52, LPAR=53, RPAR=54, COMMA=55, REF=56, VAR=57, INPUT=58, 
		PRINT=59, NEW=60, Identifier=61, Comments=62;
	public static final int
		RULE_program = 0, RULE_function_definition = 1, RULE_parameter = 2, RULE_parameters = 3, 
		RULE_block = 4, RULE_type = 5, RULE_statement = 6, RULE_declaration = 7, 
		RULE_instruction = 8, RULE_expr = 9, RULE_expressions = 10;
	public static final String[] ruleNames = {
		"program", "function_definition", "parameter", "parameters", "block", 
		"type", "statement", "declaration", "instruction", "expr", "expressions"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, "'T'", "'F'", "'for'", 
		"'while'", "'do'", "'if'", "'else'", "'->'", "'in'", "'+='", "'-='", "'*='", 
		"'/='", "'''", "'\"'", "'\\'", "'{'", "'}'", "'['", "']'", "'++'", "'--'", 
		"'+'", "'&&'", "'||'", "'!'", "'*'", "'-'", "'/'", "'mod'", "'='", "'=='", 
		"'!='", "'<'", "'>'", "'<='", "'>='", "'array'", "'bool'", "'byte'", "'int'", 
		"'float'", "'char'", "'func'", "'('", "')'", "','", "'ref'", "'var'", 
		"'read'", "'print'", "'new'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Bool", "Int", "PInt", "Char", "String", "Escape", "AssignOp", "WS", 
		"TRUE", "FALSE", "FOR", "WHILE", "DO", "IF", "ELSE", "ARROW", "IN", "AEQ", 
		"SEQ", "MEQ", "DEQ", "QUOTE", "SQUOTE", "BACKSLASH", "LBLOCK", "RBLOCK", 
		"LBRACKET", "RBRACKET", "INCR", "DECR", "ADD", "AND", "OR", "NOT", "MUL", 
		"SUB", "DIV", "MOD", "ASSIGN", "EQ", "NEQ", "LT", "GT", "LE", "GE", "ARRAY", 
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

	@Override
	public String getGrammarFileName() { return "nap.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public napParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(napParser.EOF, 0); }
		public List<Function_definitionContext> function_definition() {
			return getRuleContexts(Function_definitionContext.class);
		}
		public Function_definitionContext function_definition(int i) {
			return getRuleContext(Function_definitionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUNC) {
				{
				{
				setState(22);
				function_definition();
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(28);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_definitionContext extends ParserRuleContext {
		public TypeContext returnType;
		public TerminalNode FUNC() { return getToken(napParser.FUNC, 0); }
		public TerminalNode Identifier() { return getToken(napParser.Identifier, 0); }
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(napParser.ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Function_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterFunction_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitFunction_definition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitFunction_definition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_definitionContext function_definition() throws RecognitionException {
		Function_definitionContext _localctx = new Function_definitionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_function_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(FUNC);
			setState(31);
			match(Identifier);
			setState(32);
			match(LPAR);
			setState(33);
			parameters();
			setState(34);
			match(RPAR);
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(35);
				match(ARROW);
				setState(36);
				((Function_definitionContext)_localctx).returnType = type();
				}
			}

			setState(39);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(napParser.Identifier, 0); }
		public TerminalNode REF() { return getToken(napParser.REF, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REF) {
				{
				setState(41);
				match(REF);
				}
			}

			setState(44);
			type();
			setState(45);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(napParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(napParser.COMMA, i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARRAY) | (1L << BOOL) | (1L << BYTE) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << REF))) != 0)) {
				{
				setState(47);
				parameter();
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(48);
					match(COMMA);
					setState(49);
					parameter();
					}
					}
					setState(54);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBLOCK() { return getToken(napParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(napParser.RBLOCK, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(LBLOCK);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Char) | (1L << String) | (1L << AssignOp) | (1L << FOR) | (1L << WHILE) | (1L << DO) | (1L << IF) | (1L << ARROW) | (1L << LBLOCK) | (1L << NOT) | (1L << SUB) | (1L << LPAR) | (1L << VAR) | (1L << INPUT) | (1L << PRINT) | (1L << NEW) | (1L << Identifier))) != 0)) {
				{
				{
				setState(58);
				statement();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			match(RBLOCK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TBoolContext extends TypeContext {
		public TerminalNode BOOL() { return getToken(napParser.BOOL, 0); }
		public TBoolContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterTBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitTBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitTBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TByteContext extends TypeContext {
		public TerminalNode BYTE() { return getToken(napParser.BYTE, 0); }
		public TByteContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterTByte(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitTByte(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitTByte(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TCharContext extends TypeContext {
		public TerminalNode CHAR() { return getToken(napParser.CHAR, 0); }
		public TCharContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterTChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitTChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitTChar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TArrayContext extends TypeContext {
		public TerminalNode ARRAY() { return getToken(napParser.ARRAY, 0); }
		public TerminalNode LT() { return getToken(napParser.LT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode GT() { return getToken(napParser.GT, 0); }
		public TArrayContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterTArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitTArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitTArray(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TIntContext extends TypeContext {
		public TerminalNode INT() { return getToken(napParser.INT, 0); }
		public TIntContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterTInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitTInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitTInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TFloatContext extends TypeContext {
		public TerminalNode FLOAT() { return getToken(napParser.FLOAT, 0); }
		public TFloatContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterTFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitTFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitTFloat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				_localctx = new TIntContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(INT);
				}
				break;
			case BOOL:
				_localctx = new TBoolContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				match(BOOL);
				}
				break;
			case CHAR:
				_localctx = new TCharContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				match(CHAR);
				}
				break;
			case FLOAT:
				_localctx = new TFloatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				match(FLOAT);
				}
				break;
			case BYTE:
				_localctx = new TByteContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				match(BYTE);
				}
				break;
			case ARRAY:
				_localctx = new TArrayContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				match(ARRAY);
				setState(72);
				match(LT);
				setState(73);
				type();
				setState(74);
				match(GT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SInsContext extends StatementContext {
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public SInsContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterSIns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitSIns(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitSIns(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SDeclContext extends StatementContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public SDeclContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterSDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitSDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitSDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				_localctx = new SDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				declaration();
				}
				break;
			case Bool:
			case Int:
			case Char:
			case String:
			case AssignOp:
			case FOR:
			case WHILE:
			case DO:
			case IF:
			case ARROW:
			case LBLOCK:
			case NOT:
			case SUB:
			case LPAR:
			case INPUT:
			case PRINT:
			case NEW:
			case Identifier:
				_localctx = new SInsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				instruction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(napParser.VAR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(napParser.Identifier, 0); }
		public TerminalNode ASSIGN() { return getToken(napParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(VAR);
			setState(83);
			type();
			setState(84);
			match(Identifier);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(85);
				match(ASSIGN);
				setState(86);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
	 
		public InstructionContext() { }
		public void copyFrom(InstructionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IForContext extends InstructionContext {
		public TerminalNode FOR() { return getToken(napParser.FOR, 0); }
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(napParser.Identifier, 0); }
		public TerminalNode IN() { return getToken(napParser.IN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IForContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterIFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitIFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitIFor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IReturnContext extends InstructionContext {
		public TerminalNode ARROW() { return getToken(napParser.ARROW, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IReturnContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterIReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitIReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitIReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IAssignContext extends InstructionContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(napParser.ASSIGN, 0); }
		public TerminalNode AEQ() { return getToken(napParser.AEQ, 0); }
		public TerminalNode MEQ() { return getToken(napParser.MEQ, 0); }
		public TerminalNode SEQ() { return getToken(napParser.SEQ, 0); }
		public TerminalNode DEQ() { return getToken(napParser.DEQ, 0); }
		public IAssignContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterIAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitIAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitIAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IWhileContext extends InstructionContext {
		public TerminalNode WHILE() { return getToken(napParser.WHILE, 0); }
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IWhileContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterIWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitIWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitIWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IIfContext extends InstructionContext {
		public TerminalNode IF() { return getToken(napParser.IF, 0); }
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(napParser.ELSE, 0); }
		public IIfContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterIIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitIIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitIIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IPrintContext extends InstructionContext {
		public TerminalNode PRINT() { return getToken(napParser.PRINT, 0); }
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(napParser.COMMA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public IPrintContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterIPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitIPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitIPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IInputContext extends InstructionContext {
		public TerminalNode INPUT() { return getToken(napParser.INPUT, 0); }
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(napParser.COMMA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public IInputContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterIInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitIInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitIInput(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IExprContext extends InstructionContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IExprContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterIExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitIExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitIExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IDoWhileContext extends InstructionContext {
		public TerminalNode DO() { return getToken(napParser.DO, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(napParser.WHILE, 0); }
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public IDoWhileContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterIDoWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitIDoWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitIDoWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_instruction);
		int _la;
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new IAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				expr(0);
				setState(90);
				((IAssignContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AEQ) | (1L << SEQ) | (1L << MEQ) | (1L << DEQ) | (1L << ASSIGN))) != 0)) ) {
					((IAssignContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(91);
				expr(0);
				}
				break;
			case 2:
				_localctx = new IForContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				match(FOR);
				setState(94);
				match(LPAR);
				setState(95);
				type();
				setState(96);
				match(Identifier);
				setState(97);
				match(IN);
				setState(98);
				expr(0);
				setState(99);
				match(RPAR);
				setState(100);
				block();
				}
				break;
			case 3:
				_localctx = new IWhileContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				match(WHILE);
				setState(103);
				match(LPAR);
				setState(104);
				expr(0);
				setState(105);
				match(RPAR);
				setState(106);
				block();
				}
				break;
			case 4:
				_localctx = new IDoWhileContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(108);
				match(DO);
				setState(109);
				block();
				setState(110);
				match(WHILE);
				setState(111);
				match(LPAR);
				setState(112);
				expr(0);
				setState(113);
				match(RPAR);
				}
				break;
			case 5:
				_localctx = new IInputContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				match(INPUT);
				setState(116);
				match(LPAR);
				setState(117);
				type();
				setState(118);
				match(COMMA);
				setState(119);
				expr(0);
				setState(120);
				match(RPAR);
				}
				break;
			case 6:
				_localctx = new IPrintContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(122);
				match(PRINT);
				setState(123);
				match(LPAR);
				setState(124);
				type();
				setState(125);
				match(COMMA);
				setState(126);
				expr(0);
				setState(127);
				match(RPAR);
				}
				break;
			case 7:
				_localctx = new IIfContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(129);
				match(IF);
				setState(130);
				match(LPAR);
				setState(131);
				expr(0);
				setState(132);
				match(RPAR);
				setState(133);
				block();
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(134);
					match(ELSE);
					setState(135);
					block();
					}
				}

				}
				break;
			case 8:
				_localctx = new IReturnContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(138);
				match(ARROW);
				setState(139);
				expr(0);
				}
				break;
			case 9:
				_localctx = new IExprContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(140);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ECmpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQ() { return getToken(napParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(napParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(napParser.LT, 0); }
		public TerminalNode LE() { return getToken(napParser.LE, 0); }
		public TerminalNode GT() { return getToken(napParser.GT, 0); }
		public TerminalNode GE() { return getToken(napParser.GE, 0); }
		public ECmpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterECmp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitECmp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitECmp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EBoolContext extends ExprContext {
		public TerminalNode Bool() { return getToken(napParser.Bool, 0); }
		public EBoolContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EMulsContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(napParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(napParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(napParser.MOD, 0); }
		public EMulsContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEMuls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEMuls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEMuls(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EPostfixContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode AssignOp() { return getToken(napParser.AssignOp, 0); }
		public EPostfixContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEPostfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEPostfix(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EOrContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(napParser.OR, 0); }
		public EOrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EArrayAccessContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LBRACKET() { return getToken(napParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(napParser.RBRACKET, 0); }
		public EArrayAccessContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEArrayAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEArrayAccess(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EOppContext extends ExprContext {
		public TerminalNode SUB() { return getToken(napParser.SUB, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public EOppContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEOpp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEOpp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEOpp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EIntContext extends ExprContext {
		public TerminalNode Int() { return getToken(napParser.Int, 0); }
		public EIntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ENotContext extends ExprContext {
		public TerminalNode NOT() { return getToken(napParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ENotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterENot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitENot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitENot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ECallContext extends ExprContext {
		public TerminalNode Identifier() { return getToken(napParser.Identifier, 0); }
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public ECallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterECall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitECall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitECall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EPrefixContext extends ExprContext {
		public TerminalNode AssignOp() { return getToken(napParser.AssignOp, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public EPrefixContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEPrefix(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EIdentifierContext extends ExprContext {
		public TerminalNode Identifier() { return getToken(napParser.Identifier, 0); }
		public EIdentifierContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EAndContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(napParser.AND, 0); }
		public EAndContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ECharContext extends ExprContext {
		public TerminalNode Char() { return getToken(napParser.Char, 0); }
		public ECharContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEChar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ENewContext extends ExprContext {
		public TerminalNode NEW() { return getToken(napParser.NEW, 0); }
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(napParser.COMMA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public ENewContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterENew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitENew(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitENew(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EParContext extends ExprContext {
		public TerminalNode LPAR() { return getToken(napParser.LPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(napParser.RPAR, 0); }
		public EParContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEPar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEPar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEPar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EAddsContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ADD() { return getToken(napParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(napParser.SUB, 0); }
		public EAddsContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEAdds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEAdds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEAdds(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EStringContext extends ExprContext {
		public TerminalNode String() { return getToken(napParser.String, 0); }
		public EStringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EArrayEnumerationContext extends ExprContext {
		public TerminalNode LBLOCK() { return getToken(napParser.LBLOCK, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public TerminalNode RBLOCK() { return getToken(napParser.RBLOCK, 0); }
		public EArrayEnumerationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterEArrayEnumeration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitEArrayEnumeration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitEArrayEnumeration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new EOppContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(144);
				match(SUB);
				setState(145);
				expr(17);
				}
				break;
			case 2:
				{
				_localctx = new ENotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146);
				match(NOT);
				setState(147);
				expr(13);
				}
				break;
			case 3:
				{
				_localctx = new EPrefixContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148);
				match(AssignOp);
				setState(149);
				expr(11);
				}
				break;
			case 4:
				{
				_localctx = new ECallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150);
				match(Identifier);
				setState(151);
				match(LPAR);
				setState(152);
				expressions();
				setState(153);
				match(RPAR);
				}
				break;
			case 5:
				{
				_localctx = new EIdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(155);
				match(Identifier);
				}
				break;
			case 6:
				{
				_localctx = new EIntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156);
				match(Int);
				}
				break;
			case 7:
				{
				_localctx = new EBoolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(157);
				match(Bool);
				}
				break;
			case 8:
				{
				_localctx = new ECharContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(158);
				match(Char);
				}
				break;
			case 9:
				{
				_localctx = new EStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(159);
				match(String);
				}
				break;
			case 10:
				{
				_localctx = new ENewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(160);
				match(NEW);
				setState(161);
				match(LPAR);
				setState(162);
				type();
				setState(163);
				match(COMMA);
				setState(164);
				expr(0);
				setState(165);
				match(RPAR);
				}
				break;
			case 11:
				{
				_localctx = new EArrayEnumerationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(167);
				match(LBLOCK);
				setState(168);
				expressions();
				setState(169);
				match(RBLOCK);
				}
				break;
			case 12:
				{
				_localctx = new EParContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171);
				match(LPAR);
				setState(172);
				expr(0);
				setState(173);
				match(RPAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(201);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(199);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new EMulsContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(177);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(178);
						((EMulsContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((EMulsContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(179);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new EAddsContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(180);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(181);
						((EAddsContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((EAddsContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(182);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new ECmpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(183);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(184);
						((ECmpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LT) | (1L << GT) | (1L << LE) | (1L << GE))) != 0)) ) {
							((ECmpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(185);
						expr(17);
						}
						break;
					case 4:
						{
						_localctx = new EAndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(186);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(187);
						match(AND);
						setState(188);
						expr(16);
						}
						break;
					case 5:
						{
						_localctx = new EOrContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(189);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(190);
						match(OR);
						setState(191);
						expr(15);
						}
						break;
					case 6:
						{
						_localctx = new EPostfixContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(192);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(193);
						match(AssignOp);
						}
						break;
					case 7:
						{
						_localctx = new EArrayAccessContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(194);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(195);
						match(LBRACKET);
						setState(196);
						expr(0);
						setState(197);
						match(RBRACKET);
						}
						break;
					}
					} 
				}
				setState(203);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(napParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(napParser.COMMA, i);
		}
		public ExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).enterExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof napListener ) ((napListener)listener).exitExpressions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof napVisitor ) return ((napVisitor<? extends T>)visitor).visitExpressions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionsContext expressions() throws RecognitionException {
		ExpressionsContext _localctx = new ExpressionsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Char) | (1L << String) | (1L << AssignOp) | (1L << LBLOCK) | (1L << NOT) | (1L << SUB) | (1L << LPAR) | (1L << NEW) | (1L << Identifier))) != 0)) {
				{
				setState(204);
				expr(0);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(205);
					match(COMMA);
					setState(206);
					expr(0);
					}
					}
					setState(211);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 19);
		case 1:
			return precpred(_ctx, 18);
		case 2:
			return precpred(_ctx, 16);
		case 3:
			return precpred(_ctx, 15);
		case 4:
			return precpred(_ctx, 14);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3@\u00d9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\5\3(\n\3\3\3\3\3\3\4\5\4-\n\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5\65"+
		"\n\5\f\5\16\58\13\5\5\5:\n\5\3\6\3\6\7\6>\n\6\f\6\16\6A\13\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7O\n\7\3\b\3\b\5\bS\n\b\3\t\3"+
		"\t\3\t\3\t\3\t\5\tZ\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\5\n\u008b\n\n\3\n\3\n\3\n\5\n\u0090\n\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00b2"+
		"\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00ca\n\13\f\13\16"+
		"\13\u00cd\13\13\3\f\3\f\3\f\7\f\u00d2\n\f\f\f\16\f\u00d5\13\f\5\f\u00d7"+
		"\n\f\3\f\2\3\24\r\2\4\6\b\n\f\16\20\22\24\26\2\6\4\2\24\27))\4\2%%\'("+
		"\4\2!!&&\3\2*/\2\u00f7\2\33\3\2\2\2\4 \3\2\2\2\6,\3\2\2\2\b9\3\2\2\2\n"+
		";\3\2\2\2\fN\3\2\2\2\16R\3\2\2\2\20T\3\2\2\2\22\u008f\3\2\2\2\24\u00b1"+
		"\3\2\2\2\26\u00d6\3\2\2\2\30\32\5\4\3\2\31\30\3\2\2\2\32\35\3\2\2\2\33"+
		"\31\3\2\2\2\33\34\3\2\2\2\34\36\3\2\2\2\35\33\3\2\2\2\36\37\7\2\2\3\37"+
		"\3\3\2\2\2 !\7\66\2\2!\"\7?\2\2\"#\7\67\2\2#$\5\b\5\2$\'\78\2\2%&\7\22"+
		"\2\2&(\5\f\7\2\'%\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\5\n\6\2*\5\3\2\2\2+-"+
		"\7:\2\2,+\3\2\2\2,-\3\2\2\2-.\3\2\2\2./\5\f\7\2/\60\7?\2\2\60\7\3\2\2"+
		"\2\61\66\5\6\4\2\62\63\79\2\2\63\65\5\6\4\2\64\62\3\2\2\2\658\3\2\2\2"+
		"\66\64\3\2\2\2\66\67\3\2\2\2\67:\3\2\2\28\66\3\2\2\29\61\3\2\2\29:\3\2"+
		"\2\2:\t\3\2\2\2;?\7\33\2\2<>\5\16\b\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@"+
		"\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7\34\2\2C\13\3\2\2\2DO\7\63\2\2EO\7\61"+
		"\2\2FO\7\65\2\2GO\7\64\2\2HO\7\62\2\2IJ\7\60\2\2JK\7,\2\2KL\5\f\7\2LM"+
		"\7-\2\2MO\3\2\2\2ND\3\2\2\2NE\3\2\2\2NF\3\2\2\2NG\3\2\2\2NH\3\2\2\2NI"+
		"\3\2\2\2O\r\3\2\2\2PS\5\20\t\2QS\5\22\n\2RP\3\2\2\2RQ\3\2\2\2S\17\3\2"+
		"\2\2TU\7;\2\2UV\5\f\7\2VY\7?\2\2WX\7)\2\2XZ\5\24\13\2YW\3\2\2\2YZ\3\2"+
		"\2\2Z\21\3\2\2\2[\\\5\24\13\2\\]\t\2\2\2]^\5\24\13\2^\u0090\3\2\2\2_`"+
		"\7\r\2\2`a\7\67\2\2ab\5\f\7\2bc\7?\2\2cd\7\23\2\2de\5\24\13\2ef\78\2\2"+
		"fg\5\n\6\2g\u0090\3\2\2\2hi\7\16\2\2ij\7\67\2\2jk\5\24\13\2kl\78\2\2l"+
		"m\5\n\6\2m\u0090\3\2\2\2no\7\17\2\2op\5\n\6\2pq\7\16\2\2qr\7\67\2\2rs"+
		"\5\24\13\2st\78\2\2t\u0090\3\2\2\2uv\7<\2\2vw\7\67\2\2wx\5\f\7\2xy\79"+
		"\2\2yz\5\24\13\2z{\78\2\2{\u0090\3\2\2\2|}\7=\2\2}~\7\67\2\2~\177\5\f"+
		"\7\2\177\u0080\79\2\2\u0080\u0081\5\24\13\2\u0081\u0082\78\2\2\u0082\u0090"+
		"\3\2\2\2\u0083\u0084\7\20\2\2\u0084\u0085\7\67\2\2\u0085\u0086\5\24\13"+
		"\2\u0086\u0087\78\2\2\u0087\u008a\5\n\6\2\u0088\u0089\7\21\2\2\u0089\u008b"+
		"\5\n\6\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0090\3\2\2\2\u008c"+
		"\u008d\7\22\2\2\u008d\u0090\5\24\13\2\u008e\u0090\5\24\13\2\u008f[\3\2"+
		"\2\2\u008f_\3\2\2\2\u008fh\3\2\2\2\u008fn\3\2\2\2\u008fu\3\2\2\2\u008f"+
		"|\3\2\2\2\u008f\u0083\3\2\2\2\u008f\u008c\3\2\2\2\u008f\u008e\3\2\2\2"+
		"\u0090\23\3\2\2\2\u0091\u0092\b\13\1\2\u0092\u0093\7&\2\2\u0093\u00b2"+
		"\5\24\13\23\u0094\u0095\7$\2\2\u0095\u00b2\5\24\13\17\u0096\u0097\7\t"+
		"\2\2\u0097\u00b2\5\24\13\r\u0098\u0099\7?\2\2\u0099\u009a\7\67\2\2\u009a"+
		"\u009b\5\26\f\2\u009b\u009c\78\2\2\u009c\u00b2\3\2\2\2\u009d\u00b2\7?"+
		"\2\2\u009e\u00b2\7\4\2\2\u009f\u00b2\7\3\2\2\u00a0\u00b2\7\6\2\2\u00a1"+
		"\u00b2\7\7\2\2\u00a2\u00a3\7>\2\2\u00a3\u00a4\7\67\2\2\u00a4\u00a5\5\f"+
		"\7\2\u00a5\u00a6\79\2\2\u00a6\u00a7\5\24\13\2\u00a7\u00a8\78\2\2\u00a8"+
		"\u00b2\3\2\2\2\u00a9\u00aa\7\33\2\2\u00aa\u00ab\5\26\f\2\u00ab\u00ac\7"+
		"\34\2\2\u00ac\u00b2\3\2\2\2\u00ad\u00ae\7\67\2\2\u00ae\u00af\5\24\13\2"+
		"\u00af\u00b0\78\2\2\u00b0\u00b2\3\2\2\2\u00b1\u0091\3\2\2\2\u00b1\u0094"+
		"\3\2\2\2\u00b1\u0096\3\2\2\2\u00b1\u0098\3\2\2\2\u00b1\u009d\3\2\2\2\u00b1"+
		"\u009e\3\2\2\2\u00b1\u009f\3\2\2\2\u00b1\u00a0\3\2\2\2\u00b1\u00a1\3\2"+
		"\2\2\u00b1\u00a2\3\2\2\2\u00b1\u00a9\3\2\2\2\u00b1\u00ad\3\2\2\2\u00b2"+
		"\u00cb\3\2\2\2\u00b3\u00b4\f\25\2\2\u00b4\u00b5\t\3\2\2\u00b5\u00ca\5"+
		"\24\13\26\u00b6\u00b7\f\24\2\2\u00b7\u00b8\t\4\2\2\u00b8\u00ca\5\24\13"+
		"\25\u00b9\u00ba\f\22\2\2\u00ba\u00bb\t\5\2\2\u00bb\u00ca\5\24\13\23\u00bc"+
		"\u00bd\f\21\2\2\u00bd\u00be\7\"\2\2\u00be\u00ca\5\24\13\22\u00bf\u00c0"+
		"\f\20\2\2\u00c0\u00c1\7#\2\2\u00c1\u00ca\5\24\13\21\u00c2\u00c3\f\16\2"+
		"\2\u00c3\u00ca\7\t\2\2\u00c4\u00c5\f\5\2\2\u00c5\u00c6\7\35\2\2\u00c6"+
		"\u00c7\5\24\13\2\u00c7\u00c8\7\36\2\2\u00c8\u00ca\3\2\2\2\u00c9\u00b3"+
		"\3\2\2\2\u00c9\u00b6\3\2\2\2\u00c9\u00b9\3\2\2\2\u00c9\u00bc\3\2\2\2\u00c9"+
		"\u00bf\3\2\2\2\u00c9\u00c2\3\2\2\2\u00c9\u00c4\3\2\2\2\u00ca\u00cd\3\2"+
		"\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\25\3\2\2\2\u00cd\u00cb"+
		"\3\2\2\2\u00ce\u00d3\5\24\13\2\u00cf\u00d0\79\2\2\u00d0\u00d2\5\24\13"+
		"\2\u00d1\u00cf\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4"+
		"\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00ce\3\2\2\2\u00d6"+
		"\u00d7\3\2\2\2\u00d7\27\3\2\2\2\22\33\',\669?NRY\u008a\u008f\u00b1\u00c9"+
		"\u00cb\u00d3\u00d6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}