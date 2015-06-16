package org.tierlon.evaluation.expression;

import org.tierlon.evaluation.IAcceptanceResult;


/** 
 * Note that an expression can itself be an operand.
 * 
 * @author justin
 *
 * @param <TYPE> 
 * 		
 * @param <ExpressionContext> the context (if any) that the expressions
 * 	operate in.   Expression context can (among other things) indicate a 
 * tolerance for which to accept something as the "same" or equivalent. For
 * strings this might be whether to ignore case or not, for numbers the
 * tolerance at which two numbers might be considered the same, etc.
 * The context is assumed to be known when the expression is created and
 * is assumed to be known when used. 
 * 
 * It also could be used to provide the mapping of variables used in 
 * an expression, or in any of many myriad ways.
 * 
 */
public interface IExpression<ExpressionContext> {

	/**
	 * 
	 * Expected result type when evaluating the expression in the given context.
	 * 
	 * Please note that if a context is required to determine the answer then
	 * the implementation must return INDETERMINATE as the AcceptanceResult 
	 * when given a null context.
	 * 
	 * @param context **NULL ALLOWED** 
	 * @param operands
	 * @return
	 */
	public Class<?> getExpectedResultType(ExpressionContext context,
								   Object... operands );	
	
	
	/** 
	 *  Give the operator a chance to pre-approve/reject the operand(s)
	 * that it is to operate on and specify whether it can return information
	 * in the desired result format.
	 * 
	 * Allow clients of the operand to ask if it can return the
	 * information as a particular type. This is necessary when
	 * an expression needs to evaluate an operand that is itself 
	 * an expression...
	 * 
	 * Please note that if a context is required to determine the answer then
	 * the implementation must return INDETERMINATE as the AcceptanceResult 
	 * when given a null context.
	 * 
	 * @param context **NULL ALLOWED** 
	 * @param resultType
	 * @return
	 */
	public IAcceptanceResult canGetResultAs(ExpressionContext context,
				Class<?> resultType, Object... operands);
	
	
	/**
	 * @param  resultType - the expected result ty
	 * @param context - the expression context, or null if ** all ** the
	 * 	operands (including sub-expressions) do not need an expression context.
	 *  ** Can be required for evaluation **
	 * @param operands
	 * @return the result of the evaluation of the expression.
	 * 
	 * 		This really should be one of Object, String, Boolean, Number or
	 * 		derived or equivalent types (such as IAcceptanceResult for Boolean)
	 * 
	 * @throws IllegalOperandTypeException when an operand is illegal. 
	 *  This could be because the operand is the wrong type, or is non-sensical 
	 *  in  the context given.   For example, 3<5 makes sense and "a"<"b" 
	 *  also makes sense (using alphabetical ordering), but 3<"b"  does not...
	 *  
	 * @throws IllegalEvaluationType when the result type desired cannot be
	 * 	 produced.
	 * 
	 */
	public <TYPE> TYPE evaluateForType(Class<TYPE> resultType,
								ExpressionContext context,
								Object... operands);

	
}

