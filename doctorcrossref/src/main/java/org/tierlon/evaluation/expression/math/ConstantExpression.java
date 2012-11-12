package org.tierlon.evaluation.expression.math;

import org.tierlon.evaluation.AcceptanceResult;
import org.tierlon.evaluation.IAcceptanceResult;
import org.tierlon.evaluation.expression.IExpression;
import org.tierlon.evaluation.expression.IllegalEvaluationTypeException;

public class ConstantExpression<Context> implements IExpression<Context> {

	Object constantValue;
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public ConstantExpression(Object constantValue) {
		if (constantValue==null)
			throw new NullPointerException();
		this.constantValue = constantValue;
	}
	// ==============================================================
	// INTERFACE IMPLEMENTATION
	// ==============================================================
	@Override
	public Class<?> getExpectedResultType(Context context, Object... operands) {
		return constantValue.getClass();
	}

	@Override
	public IAcceptanceResult canGetResultAs(Context context,
			Class<?> resultType, Object... operands) {
		
		if (operands!=null && operands.length>0) {
			return new AcceptanceResult(false,
					"Can't use operands for a constant expression");
		}
		if (resultType.isAssignableFrom(constantValue.getClass())) {
			return new AcceptanceResult(true,"");
		}
		String reason ="Don't know how to convert our constant: "+
				constantValue.getClass()+" to : "+resultType;
		return new AcceptanceResult(false,reason);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <TYPE> TYPE evaluateForType(Class<TYPE> resultType, Context context,
			Object... operands) {
		if (operands!=null && operands.length>0) {
			throw new IllegalEvaluationTypeException("Can't use additional operands...");
		}
		
		if (!resultType.isAssignableFrom(constantValue.getClass())) {
			String reason ="Don't know how to convert our constant: "+
					constantValue.getClass()+" to : "+resultType;
			throw new IllegalEvaluationTypeException(reason);
		}
		return (TYPE) constantValue;
	}

}
