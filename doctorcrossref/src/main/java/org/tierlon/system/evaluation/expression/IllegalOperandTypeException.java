package org.tierlon.system.evaluation.expression;

@SuppressWarnings("serial")
public class IllegalOperandTypeException extends RuntimeException {

	public IllegalOperandTypeException() {
		super();
	}

	public IllegalOperandTypeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegalOperandTypeException(String arg0) {
		super(arg0);
	}

	public IllegalOperandTypeException(Throwable arg0) {
		super(arg0);
	}

}
