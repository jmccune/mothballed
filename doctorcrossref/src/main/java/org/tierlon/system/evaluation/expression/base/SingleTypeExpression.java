package org.tierlon.system.evaluation.expression.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tierlon.system.evaluation.AcceptanceResult;
import org.tierlon.system.evaluation.IAcceptanceResult;
import org.tierlon.system.evaluation.expression.IExpression;
import org.tierlon.system.evaluation.expression.IllegalEvaluationTypeException;
import org.tierlon.system.evaluation.expression.IllegalOperandTypeException;

public abstract class SingleTypeExpression<Context> 
	implements IExpression<Context> {

	/** All operands must be of this type, or be expressions capable
	 * of generating this type.
	 */
	private Class<?> type;
	private List<Object> inherentOperands = new ArrayList<Object>();
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public SingleTypeExpression(Class<?> type) {
		if (type == null) 
			throw new NullPointerException();
		this.type = type;
	}
	
	public SingleTypeExpression(Class<?> type, 
			Object...operands) {
		if (type == null) 
			throw new NullPointerException();
		this.type = type;
		IAcceptanceResult validationResult =
				validateOperands(null,operands);
		if (validationResult.isUnacceptable())
			throw new IllegalArgumentException(validationResult.getReason());
		
		inherentOperands.addAll(Arrays.asList(operands));
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
		return type;
	}
	
	@Override
	public IAcceptanceResult canGetResultAs(Context context,
			Class<?> resultType, Object... operands) {
		
		IAcceptanceResult result = validateOperands(context,operands);
		if (!result.isAcceptable())
			return result;
		
		if  (resultType==null || resultType.isAssignableFrom(type))
			return new AcceptanceResult(true,"assignment compatible");
		
		String reason = "Type: "+resultType+" is not assignment compatible "+
				"with: "+type;
		return new AcceptanceResult(false,reason);
	}

	
	// ==============================================================
	// PROTECTED
	// ==============================================================
	protected List<Object> getStraightOperands(Context context,
			Object... additionalOperands) {
		validateOperands(context,additionalOperands);
		List<Object> resultList = new ArrayList<Object>();
		
		for (Object operand : inherentOperands) {
			resultList.add(getAsOperand(context,operand));
		}
		
		if (additionalOperands!=null) {
			for (Object operand : additionalOperands) {
				resultList.add(getAsOperand(context,operand));
			}
		}
		return resultList;
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
		if (type.isInstance(operand)) {
			return operand;
		}
		
		if (!(operand instanceof IExpression)) {
			String reason = "Operand: "+operand+" is not of expected type: "+
					type;
			throw new IllegalOperandTypeException(reason);
		}
		@SuppressWarnings("unchecked")
		Object result = ((IExpression<Context>) operand).evaluateForType(type,
				context);

		if (!type.isInstance(result)) {
			String reason = "Expected type: " + type + " but received: "
					+ result;
			try {
				reason += " from expression: " + operand.toString();
			} catch (Exception x) {
			}
			throw new IllegalEvaluationTypeException(reason);
		}
		return result;
	}
	

	/**
	 * 
	 * @param context  (null allowed) 
	 * @param operands (non-null)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected IAcceptanceResult validateOperands(Context context,
			Object... operands) {
		
		for (Object operand: operands) 
		{
			if (operand instanceof IExpression) {
				IAcceptanceResult result=
				 ((IExpression<Context>) operand).canGetResultAs(context, type);
				
				if (result.isUnacceptable())
					return result;
			}
			else {
				if (!type.isInstance(operand)) {
					String reason = "Expected operand of type: "+type;
					reason+= " Received operand of type: "+
							((operand==null) ? "NULL" : operand.getClass());
					return new AcceptanceResult(false, reason);
				}
			}
		}
		return new AcceptanceResult(true,"");
	}
}