package org.tierlon.evaluation.expression.logic;

import java.util.List;

import org.tierlon.evaluation.AcceptanceResult;
import org.tierlon.evaluation.IAcceptanceResult;
import org.tierlon.evaluation.expression.IllegalEvaluationTypeException;
import org.tierlon.evaluation.expression.base.AcceptanceBooleanExpression;

public class BooleanOrExpression<Context> extends
		AcceptanceBooleanExpression<Context> {

	// ==============================================================
	// Interface Implementation
	// ==============================================================
	@SuppressWarnings("unchecked")
	@Override
	public <TYPE> TYPE evaluateForType(Class<TYPE> resultType, Context context,
			Object... operands) {

		if (!(resultType.isAssignableFrom(Boolean.class))
				&& !resultType.isAssignableFrom(IAcceptanceResult.class)) {
			String reason = "Asked for type: " + resultType
					+ " but only support " + "Boolean/IAcceptanceResult";
			throw new IllegalEvaluationTypeException(reason);
		}

		List<Object> operandList = super.getStraightOperands(context, operands);
		IAcceptanceResult result = null;
		for (Object operand : operandList) {
			IAcceptanceResult operandResult = (IAcceptanceResult) operand;
			if (operandResult.isAcceptable()) {
				result = new AcceptanceResult(true, "An operand was true");
				break;
			}
		}
		if (result == null) {
			String reason = "None of the " + operandList.size()
					+ " operands were acceptable/true";
			result = new AcceptanceResult(false, reason);
		}

		if (resultType.isAssignableFrom(IAcceptanceResult.class)) {
			return (TYPE) result;
		}

		return (TYPE) ((Object) result.isAcceptable());

	}

}
