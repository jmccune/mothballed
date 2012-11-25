package org.tierlon.evaluation.expression.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tierlon.evaluation.AcceptanceResult;
import org.tierlon.evaluation.IAcceptanceResult;
import org.tierlon.evaluation.expression.IllegalEvaluationTypeException;


public class NotEqualComparisonExpression<Context>
	extends ComparisonExpression<Context, Number> {

	public NotEqualComparisonExpression() {
		super(Number.class,"==");
	}

	/**
	 * Mathematically speaking, not equals doesn't work the same
	 * as the other comparison types, as they can compare the current
	 * value only to the previous value.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <TYPE> TYPE evaluateForType(Class<TYPE> resultType, Context context,
			Object... operands) {
		
		if (!(resultType.isAssignableFrom(Boolean.class)) &&
				!resultType.isAssignableFrom(IAcceptanceResult.class)) {
				String reason= "Asked for type: "+resultType+" but only support "+
						"Boolean/IAcceptanceResult";
				throw new IllegalEvaluationTypeException(reason);
			}
		
		List<Object> operandList = super.getStraightOperands(context,operands);		
		if (operandList.size() < 2) {
			throw new IllegalEvaluationTypeException("Expected two or more operands...");
		}
	
		int finalResult =1;
		String reason="No duplicates found";
		Set<Number> numberSet = new HashSet<Number>();
		for (Object operand : operandList) {
			if (numberSet.contains(operand)) {
				finalResult=-1;
				reason="At least one set of duplicates("+operand+") found.";
				break;
			}
			numberSet.add((Number)operand);			
		}				
		AcceptanceResult operandResult = 
				new AcceptanceResult(finalResult,reason);
		
		if (resultType.isAssignableFrom(Boolean.class)) 
			return (TYPE) ((Object) operandResult.isAcceptable());

		return (TYPE) ((Object) operandResult);
	}
	
	 

}
