package org.tierlon.system.evaluation.expression;

@SuppressWarnings("serial")
public class IllegalEvaluationTypeException extends RuntimeException {

	public IllegalEvaluationTypeException() {
		super();
	}

	public IllegalEvaluationTypeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegalEvaluationTypeException(String arg0) {
		super(arg0);
	}

	public IllegalEvaluationTypeException(Throwable arg0) {
		super(arg0);
	}

}
