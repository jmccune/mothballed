package org.tierlon.system.evaluation.expression.math;

import java.util.List;

import org.tierlon.system.evaluation.expression.IllegalEvaluationTypeException;
import org.tierlon.system.evaluation.expression.base.SingleTypeExpression;

public class AddExpression<Context> extends SingleTypeExpression<Context> {

	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public AddExpression() {
		super(Number.class);		
	}

	// ==============================================================
	// Interface Implementation
	// ==============================================================
	@SuppressWarnings("unchecked")
	@Override
	public <TYPE> TYPE evaluateForType(Class<TYPE> resultType,
			Context context,
			Object... operands) {
		
		if (!Number.class.isAssignableFrom(resultType)) {
			throw new IllegalEvaluationTypeException(
					"Can't return a number result to: "+resultType);
		}
		
		List<Object> operandList = getStraightOperands(context,operands);
		
		double sum = 0.0;
		for (Object operand : operandList) {
			Number number = (Number) operand;
			sum = sum + number.doubleValue();
		}
		return (TYPE) new Double(sum);
	}

}
