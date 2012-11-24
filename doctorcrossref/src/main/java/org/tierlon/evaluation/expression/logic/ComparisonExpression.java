package org.tierlon.evaluation.expression.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.tierlon.evaluation.AcceptanceResult;
import org.tierlon.evaluation.IAcceptanceResult;
import org.tierlon.evaluation.expression.IExpression;
import org.tierlon.evaluation.expression.IllegalEvaluationTypeException;
import org.tierlon.evaluation.expression.IllegalOperandTypeException;

/**
 * Compare two objects that are orderable and return a boolean result.
 * @author justinanddiana
 *
 */
public class ComparisonExpression<Context,CompTYPE> implements IExpression<Context> {

	private List<Object> inherentOperands = new ArrayList<Object>();
	private Class<CompTYPE>	comparatorType =null;
	private Comparator<CompTYPE> comparator =null;
	
	static public enum NumericalComparison {
		GreaterThan(">"),
		GreaterThanOrEqual(">="),
		Equal("=="),
		LessThanOrEqual("<="),
		LessThan("<");
		
		private String stringRepresentation;
		NumericalComparison(String value) {
			stringRepresentation = value;
		}
		
		public static NumericalComparison findRepresentation(String val) {
			for (NumericalComparison ctype : NumericalComparison.values()) {
				if (ctype.stringRepresentation.equalsIgnoreCase(val)) {
					return ctype;
				}
									
			}
			return NumericalComparison.valueOf(val);
		}
		public  String getStringRepresentation() {
			return stringRepresentation;
		}
	}
	static Comparator<Number> generateComparator(NumericalComparison operation){
		return new NumberComparator(operation);
		
	}
	static Comparator<Number> generateComparator(String spec) {
		return generateComparator(NumericalComparison.findRepresentation(spec));
	}
	
	
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	@SuppressWarnings("unchecked")
	public ComparisonExpression(Class<CompTYPE> comparatorType,
			String numericalComparator) {
		this.comparatorType= comparatorType;
		this.comparator = (Comparator<CompTYPE>) 
				generateComparator(numericalComparator);		
		
	}
	public ComparisonExpression(Class<CompTYPE> comparatorType,
									   Comparator<CompTYPE> comp) {
		
		if (comparatorType==null || comp==null) {
			throw new NullPointerException();
		}
		this.comparator = comp;
		this.comparatorType = comparatorType;

	}
	
	// ==============================================================
	// PUBLIC Methods
	// ==============================================================
	public void addOperands(Object...operands) {
		IAcceptanceResult validationResult =
				validateOperands(null,operands);
		if (validationResult.isUnacceptable())
			throw new IllegalOperandTypeException(validationResult.getReason());
		this.inherentOperands.addAll(Arrays.asList(operands));
	}
	
	public boolean removeOperand(Object... operand) {
		return this.inherentOperands.remove(operand);
	}
	
	// ==============================================================
	// INTERFACE IMPLEMENTATION
	// ==============================================================
	@Override
	public Class<?> getExpectedResultType(Context context, Object... operands) {
		return AcceptanceResult.class;
	}

	@Override
	public IAcceptanceResult canGetResultAs(Context context,
			Class<?> resultType, Object... operands) {
		if (resultType.isAssignableFrom(Boolean.class)) { 
			return new AcceptanceResult(true,"Can return a boolean");
		}
		if (resultType.isAssignableFrom(IAcceptanceResult.class)) {
			return new AcceptanceResult(true,"Can return an acceptance result");
		}
		return new AcceptanceResult(false,"Cannot return a result of type: "+resultType);
	}

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
		
		List<Object> operandList = getStraightOperands(context,operands);		
		if (operandList.size() < 2) {
			throw new IllegalEvaluationTypeException("Expected two or more operands...");
		}
		
		CompTYPE o1=null;
		int finalResult=1;
		for (Object operand : operandList) {
		
			if (o1!=null) {
				CompTYPE o2 = (CompTYPE) operand;
				int result = comparator.compare(o1, o2);
				if (result<=0) {
					finalResult = result;
				}
			}
			o1= (CompTYPE) operand;			
		}		
		if (finalResult<-1) {
			finalResult=-1;
		}
		AcceptanceResult operandResult = 
				new AcceptanceResult(finalResult,"Result of comparisons");
		
		if (resultType.isAssignableFrom(Boolean.class)) 
			return (TYPE) ((Object) operandResult.isAcceptable());

		return (TYPE) ((Object) operandResult);
	}

	// ==============================================================
	// PROTECTED METHODS
	// ==============================================================

	
	protected List<Object> getStraightOperands(Context context,
			Object... additionalOperands) {
		
		validateOperands(context,additionalOperands);
		
		List<Object> resultList = new ArrayList<Object>();
		
		for (Object operand : inherentOperands) {
			resultList.add(getAsDesiredType(context,operand));
		}
		
		if (additionalOperands!=null) {
			for (Object operand : additionalOperands) {
				resultList.add(getAsDesiredType(context,operand));
			}
		}
		return resultList;
	}
	
	protected Object getAsDesiredType(Context context, Object operand) {
		if (comparatorType.isInstance(operand)) {
			return operand;
		}

		if (!(operand instanceof IExpression)) {
			String reason = "Operand: " + operand
					+ " is not of expected type: "+comparatorType;
			throw new IllegalOperandTypeException(reason);
		}

		@SuppressWarnings("unchecked")
		IExpression<Context> operandExpression = 
			((IExpression<Context>) operand);

		Object result = operand;
		result = operandExpression.evaluateForType(comparatorType,context);
		
		if (comparatorType.isInstance(result)) {
			return result;
		}
		String reason = "Operand converted to: " + result
				+ " is not of expected type: "+comparatorType;
		throw new IllegalOperandTypeException(reason);		
	}
	
	protected IAcceptanceResult validateOperands(Context context,
			Object... operands) {
		
		for (Object operand: operands) 
		{
			if (operand instanceof IExpression) {
				@SuppressWarnings("unchecked")
				IAcceptanceResult result=((IExpression<Context>) operand).
						canGetResultAs(context, comparatorType);
				if (result.isAcceptable())
					continue;
				return result;
			}
			else {
				if (!comparatorType.isInstance(operand)) {
					String reason = 
						"Expected operand of type "+comparatorType;
					reason+= " Received operand of type: "+
							((operand==null) ? "NULL" : operand.getClass());
					return new AcceptanceResult(false, reason);
				}
			}
		}
		return new AcceptanceResult(true,"All operands legitimate");
	}
	

	
	// ==============================================================
	// ==============================================================
	static class NumberComparator implements Comparator<Number> 
	{
		
		private NumericalComparison operationField;
		NumberComparator(NumericalComparison operationValue) {
			this.operationField = operationValue;
		}
		
		@Override
		public String toString() {
			return "NumericalComparison[operation: "+
					operationField.getStringRepresentation()+"]";
		}
		@Override
		public int compare(Number o1, Number o2) {
			double diff= o1.doubleValue() - o2.doubleValue();
			switch (operationField) {			
			case GreaterThan:
				if (diff>0) return 1;
				return -1;						
			case GreaterThanOrEqual:
				if (diff>=0) return 1;
				return -1;				
			case LessThan:	
				if (diff<0) return 1;
				return -1;
			case LessThanOrEqual:
				if (diff<=0) return 1;
				return -1;
			case Equal:
				if (diff==0) return 1;
				return -1;
			
			default:
				throw new IllegalStateException("Programmer Error"); 
			}
		}
		
	};
}
