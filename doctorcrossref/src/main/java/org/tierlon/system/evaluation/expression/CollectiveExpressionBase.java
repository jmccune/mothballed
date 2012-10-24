package org.tierlon.system.evaluation.expression;

import java.util.ArrayList;
import java.util.List;

import org.tierlon.system.evaluation.IEvaluate;

public class CollectiveExpressionBase<Context> {

	protected List<IEvaluate<Context>> operandList;

	// ==============================================================
	// PROTECTED CONSTRUCTION
	// ==============================================================
	protected CollectiveExpressionBase() {
		operandList = new ArrayList<IEvaluate<Context>>();
	}
	
	// ==============================================================
	// PUBLIC METHODS
	// ==============================================================
	public void add(IEvaluate<Context> operand) {
		operandList.add(operand);
	}
	
	public void remove(IEvaluate<Context> operand) {
		operandList.remove(operand);
	}
	
	
	
}
