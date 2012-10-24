package org.tierlon.system.evaluation.expression;

import org.tierlon.system.evaluation.AcceptanceResult;
import org.tierlon.system.evaluation.IAcceptanceResult;
import org.tierlon.system.evaluation.IEvaluate;

public class NotExpression<Context> implements IEvaluate<Context>{

	private IEvaluate<Context> operand;
	
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public NotExpression(IEvaluate<Context> operand) {
		if (operand==null) {
			throw new NullPointerException();
		}
		this.operand = operand;
	}
	
	// ==============================================================
	// PUBLIC 
	// ==============================================================	
	@Override
	public IAcceptanceResult evaluate(Context object, Object... contextualInfo) {
		
		IAcceptanceResult result = operand.evaluate(object, contextualInfo);
		return AcceptanceResult.negate(result);
	}

}
