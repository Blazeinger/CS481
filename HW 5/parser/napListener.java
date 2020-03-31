// Generated from nap.g4 by ANTLR 4.7.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link napParser}.
 */
public interface napListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link napParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(napParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link napParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(napParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link napParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void enterFunction_definition(napParser.Function_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link napParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void exitFunction_definition(napParser.Function_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link napParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(napParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link napParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(napParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link napParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(napParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link napParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(napParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link napParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(napParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link napParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(napParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TInt}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTInt(napParser.TIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TInt}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTInt(napParser.TIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TBool}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTBool(napParser.TBoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TBool}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTBool(napParser.TBoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TChar}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTChar(napParser.TCharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TChar}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTChar(napParser.TCharContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TFloat}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTFloat(napParser.TFloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TFloat}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTFloat(napParser.TFloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TByte}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTByte(napParser.TByteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TByte}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTByte(napParser.TByteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TArray}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTArray(napParser.TArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TArray}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTArray(napParser.TArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SDecl}
	 * labeled alternative in {@link napParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSDecl(napParser.SDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SDecl}
	 * labeled alternative in {@link napParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSDecl(napParser.SDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SIns}
	 * labeled alternative in {@link napParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSIns(napParser.SInsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SIns}
	 * labeled alternative in {@link napParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSIns(napParser.SInsContext ctx);
	/**
	 * Enter a parse tree produced by {@link napParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(napParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link napParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(napParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IAssign}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIAssign(napParser.IAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IAssign}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIAssign(napParser.IAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IFor}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIFor(napParser.IForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IFor}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIFor(napParser.IForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IWhile}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIWhile(napParser.IWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IWhile}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIWhile(napParser.IWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IDoWhile}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIDoWhile(napParser.IDoWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IDoWhile}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIDoWhile(napParser.IDoWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IInput}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIInput(napParser.IInputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IInput}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIInput(napParser.IInputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IPrint}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIPrint(napParser.IPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IPrint}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIPrint(napParser.IPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IIf}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIIf(napParser.IIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IIf}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIIf(napParser.IIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IReturn}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIReturn(napParser.IReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IReturn}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIReturn(napParser.IReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IExpr}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIExpr(napParser.IExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IExpr}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIExpr(napParser.IExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ECmp}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterECmp(napParser.ECmpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ECmp}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitECmp(napParser.ECmpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EBool}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEBool(napParser.EBoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EBool}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEBool(napParser.EBoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EMuls}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEMuls(napParser.EMulsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EMuls}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEMuls(napParser.EMulsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EPostfix}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEPostfix(napParser.EPostfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EPostfix}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEPostfix(napParser.EPostfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EOr}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEOr(napParser.EOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EOr}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEOr(napParser.EOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EArrayAccess}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEArrayAccess(napParser.EArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EArrayAccess}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEArrayAccess(napParser.EArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EOpp}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEOpp(napParser.EOppContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EOpp}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEOpp(napParser.EOppContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EInt}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEInt(napParser.EIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EInt}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEInt(napParser.EIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ENot}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterENot(napParser.ENotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ENot}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitENot(napParser.ENotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ECall}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterECall(napParser.ECallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ECall}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitECall(napParser.ECallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EPrefix}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEPrefix(napParser.EPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EPrefix}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEPrefix(napParser.EPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EIdentifier}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEIdentifier(napParser.EIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EIdentifier}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEIdentifier(napParser.EIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EAnd}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEAnd(napParser.EAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EAnd}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEAnd(napParser.EAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EChar}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEChar(napParser.ECharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EChar}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEChar(napParser.ECharContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ENew}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterENew(napParser.ENewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ENew}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitENew(napParser.ENewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EPar}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEPar(napParser.EParContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EPar}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEPar(napParser.EParContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EAdds}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEAdds(napParser.EAddsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EAdds}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEAdds(napParser.EAddsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EString}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEString(napParser.EStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EString}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEString(napParser.EStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EArrayEnumeration}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEArrayEnumeration(napParser.EArrayEnumerationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EArrayEnumeration}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEArrayEnumeration(napParser.EArrayEnumerationContext ctx);
	/**
	 * Enter a parse tree produced by {@link napParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(napParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link napParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(napParser.ExpressionsContext ctx);
}