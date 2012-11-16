package org.tierlon.evaluation.expression.logic;


import java.util.List;

import org.tierlon.evaluation.AcceptanceResult;
import org.tierlon.evaluation.IAcceptanceResult;
import org.tierlon.evaluation.expression.IllegalEvaluationTypeException;
import org.tierlon.evaluation.expression.base.AcceptanceBooleanExpression;
 

public class BooleanNotExpression<Context> 
	extends AcceptanceBooleanExpression<Context> {

	// ==============================================================
	// Interface Implementation
	// ==============================================================
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
		
		List<Object> operandList = super.getStraightOperands(context,
											operands);
		if (operandList.size()!=1) {
			throw new IllegalEvaluationTypeException("Expected one operand...");
		}
		
		IAcceptanceResult operandResult = (IAcceptanceResult)operandList.get(0);
		IAcceptanceResult result=new AcceptanceResult(
				-operandResult.asNumber(),
				"Negated>> "+operandResult.getReason());
		
		if (resultType.isAssignableFrom(IAcceptanceResult.class)) {
			return (TYPE) result;
		}		
		return (TYPE) ((Object) result.isAcceptable());		
	}

}
