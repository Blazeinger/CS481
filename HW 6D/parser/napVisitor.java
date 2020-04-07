// Generated from nap.g4 by ANTLR 4.7.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link napParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface napVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link napParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(napParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link napParser#function_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_definition(napParser.Function_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link napParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(napParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link napParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(napParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link napParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(napParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TInt}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTInt(napParser.TIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TBool}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTBool(napParser.TBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TChar}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTChar(napParser.TCharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TFloat}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTFloat(napParser.TFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TByte}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTByte(napParser.TByteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TArray}
	 * labeled alternative in {@link napParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTArray(napParser.TArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SDecl}
	 * labeled alternative in {@link napParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSDecl(napParser.SDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SIns}
	 * labeled alternative in {@link napParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSIns(napParser.SInsContext ctx);
	/**
	 * Visit a parse tree produced by {@link napParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(napParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IAssign}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIAssign(napParser.IAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IFor}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIFor(napParser.IForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IWhile}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIWhile(napParser.IWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IDoWhile}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIDoWhile(napParser.IDoWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IInput}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIInput(napParser.IInputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IPrint}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIPrint(napParser.IPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IIf}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIIf(napParser.IIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IReturn}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIReturn(napParser.IReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IExpr}
	 * labeled alternative in {@link napParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIExpr(napParser.IExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ECmp}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitECmp(napParser.ECmpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EBool}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEBool(napParser.EBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EMuls}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEMuls(napParser.EMulsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EPostfix}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEPostfix(napParser.EPostfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EOr}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEOr(napParser.EOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EArrayAccess}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEArrayAccess(napParser.EArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EOpp}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEOpp(napParser.EOppContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EInt}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEInt(napParser.EIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ENot}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitENot(napParser.ENotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ECall}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitECall(napParser.ECallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EPrefix}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEPrefix(napParser.EPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EIdentifier}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEIdentifier(napParser.EIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EAnd}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEAnd(napParser.EAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EChar}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEChar(napParser.ECharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ENew}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitENew(napParser.ENewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EPar}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEPar(napParser.EParContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EAdds}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEAdds(napParser.EAddsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EString}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEString(napParser.EStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EArrayEnumeration}
	 * labeled alternative in {@link napParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEArrayEnumeration(napParser.EArrayEnumerationContext ctx);
	/**
	 * Visit a parse tree produced by {@link napParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(napParser.ExpressionsContext ctx);
}