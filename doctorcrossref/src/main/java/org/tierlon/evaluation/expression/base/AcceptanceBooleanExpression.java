package org.tierlon.evaluation.expression.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tierlon.evaluation.AcceptanceResult;
import org.tierlon.evaluation.IAcceptanceResult;
import org.tierlon.evaluation.expression.IExpression;
import org.tierlon.evaluation.expression.IllegalEvaluationTypeException;
import org.tierlon.evaluation.expression.IllegalOperandTypeException;

public abstract class AcceptanceBooleanExpression<Context> 
	implements IExpression<Context> {

	private List<Object> inherentOperands = new ArrayList<Object>();
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public AcceptanceBooleanExpression(Object... operands) {
		
	}
	
	// ==============================================================
	// PUBLIC
	// ==============================================================
	public void addOperands(Object...operands) {
		IAcceptanceResult validationResult =
				validateOperands(null,operands);
		if (validationResult.isUnacceptable())
			throw new IllegalOperandTypeException(validationResult.getReason());
		this.inherentOperands.addAll(Arrays.asList(operands));
	}
	
	public boolean removeOperand(Object... operand) {
		return this.inherentOperands.remove(operand);
	}
	// ==============================================================
	// INTERFACE IMPLEMENTATION
	// ==============================================================		
	@Override
	public Class<?> getExpectedResultType(Context context, Object... operands) {
		return AcceptanceResult.class;
	}

	@Override
	public IAcceptanceResult canGetResultAs(Context context,
			Class<?> resultType, Object... operands) {
		if (resultType.isAssignableFrom(Boolean.class)) { 
			return new AcceptanceResult(true,"Can return a boolean");
		}
		if (resultType.isAssignableFrom(IAcceptanceResult.class)) {
			return new AcceptanceResult(true,"Can return an acceptance result");
		}
		return new AcceptanceResult(false,"Cannot return a result of type: "+resultType);
	}

	
	
	// ==============================================================
	// PROTECTED
	// ==============================================================
	/**
	 * 
	 * @param context
	 * @param additionalOperands
	 * @return a list of acceptance results...  (for consistency 
	 * 	with other operations, this is not typed as 
	 * List&lt;IAcceptanceResult&gt; )
	 * 
	 */
	protected List<Object> getStraightOperands(Context context,
			Object... additionalOperands) {
		validateOperands(context,additionalOperands);
		List<Object> resultList = new ArrayList<Object>();
		
		for (Object operand : inherentOperands) {
			resultList.add(getAsAcceptanceResult(context,operand));
		}
		
		if (additionalOperands!=null) {
			for (Object operand : additionalOperands) {
				resultList.add(getAsAcceptanceResult(context,operand));
			}
		}
		return resultList;
	}
	

	protected IAcceptanceResult getAsAcceptanceResult(Context context, 
			Object operand) {
	
		Object booleanOrAcceptanceResult = getAsOperand(context,operand);
		if (booleanOrAcceptanceResult instanceof Boolean) {
			return new AcceptanceResult((Boolean)booleanOrAcceptanceResult,
					"Converted boolean value-- no more info available");
		} else if (booleanOrAcceptanceResult instanceof IAcceptanceResult) {
			return (IAcceptanceResult) booleanOrAcceptanceResult;
		}else
		{
			throw new IllegalStateException(
					"Require boolean/iacceptance result!");
		}
	}
	/** 
	 * Deals with operands that are actually expressions, and verifies
	 * that all operands are actually the type expected or throws
	 * an IllegalEvaluationTypeException/IllegalOperandTypeException
	 * depending upon whether it was a failed expression evaluation
	 * or operand type respectively.
	 * 
	 * @param context
	 * @param operand
	 * @return
	 */
	protected Object getAsOperand(Context context, Object operand) {
		if (Boolean.class.isInstance(operand) ||
			IAcceptanceResult.class.isInstance(operand)) {
			return operand;
		}
		
		if (!(operand instanceof IExpression)) {
			String reason = "Operand: "+operand+
					" is not of expected type (Boolean/IAcceptanceResult)";
			throw new IllegalOperandTypeException(reason);
		}
		
		
		@SuppressWarnings("unchecked")
		IExpression<Context> operandExpression = 
				((IExpression<Context>) operand);
		
		Object result=operand;
		//Note: This is the preferred (more informative) result.
		if (operandExpression.canGetResultAs(context,IAcceptanceResult.class).
				isAcceptable())
		{
			result = operandExpression.evaluateForType(
					IAcceptanceResult.class,context);
			
			if (IAcceptanceResult.class.isInstance(result)) {
				return result;
			}
		}
		else if (operandExpression.canGetResultAs(context,Boolean.class).
				isAcceptable())
		{
			result = operandExpression.evaluateForType(
					Boolean.class,context);
			
			if (Boolean.class.isInstance(result)) {
				return result;
			}
		}
		
		String reason ="Expected type: (Boolean/IAcceptanceResult) but received: "
					+ ((result == null) ? "NULL" : result.getClass());
		throw new IllegalEvaluationTypeException(reason);		
	}
	

	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param context  (null allowed) 
	 * @param operands (non-null)
	 * @return
	 */
	protected IAcceptanceResult validateOperands(Context context,
			Object... operands) {
		
		for (Object operand: operands) 
		{
			if (operand instanceof IExpression) {
				IAcceptanceResult result=((IExpression<Context>) operand).
						canGetResultAs(context, Boolean.class);
				
				if (result.isAcceptable())
					continue;
				result= ((IExpression<Context>) operand).canGetResultAs(context, 
							IAcceptanceResult.class);
				return result;
			}
			else {
				if (!Boolean.class.isInstance(operand) &&
					IAcceptanceResult.class.isInstance(operand)) {
					String reason = 
						"Expected operand of type (Boolean/IAcceptanceResult).";
					reason+= " Received operand of type: "+
							((operand==null) ? "NULL" : operand.getClass());
					return new AcceptanceResult(false, reason);
				}
			}
		}
		return new AcceptanceResult(true,"All operands legitimate");
	}
}
