package org.tierlon.system.evaluation.expression;

import org.tierlon.system.evaluation.AcceptanceResult;
import org.tierlon.system.evaluation.IAcceptanceResult;
import org.tierlon.system.evaluation.IEvaluate;

public class AndExpression<Context> 
	extends CollectiveExpressionBase<Context>
	implements IEvaluate<Context> {

	
	@Override
	public IAcceptanceResult evaluate(Context object, Object... contextualInfo) 
	{
		for (IEvaluate<Context> operand: operandList) {
			IAcceptanceResult result = operand.evaluate(object, contextualInfo);
			if (!result.isAcceptable())
				return result;
		}
		return new AcceptanceResult(true,"All operands worked");
	}

}
