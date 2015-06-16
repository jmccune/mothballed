package org.tierlon.evaluation.expression.function;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.tierlon.evaluation.AcceptanceResult;
import org.tierlon.evaluation.IAcceptanceResult;
import org.tierlon.evaluation.expression.IExpression;
import org.tierlon.evaluation.expression.IllegalOperandTypeException;

public class LengthFunction<Context> implements IExpression<Context> {

	Object object;

	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public LengthFunction(Object takeLengthOfThis) {
		if (takeLengthOfThis == null )
			throw  new NullPointerException();
		object = takeLengthOfThis;
	}
	// ==============================================================
	// INTERFACE IMPLEMENTATION
	// ==============================================================
	@Override
	public Class<?> getExpectedResultType(Context context, Object... operands) {
		return Integer.class;
	}

	@Override
	public IAcceptanceResult canGetResultAs(Context context,
			Class<?> resultType, Object... operands) {
		
		if (resultType.isAssignableFrom(Integer.class)) {
			return new AcceptanceResult(true,"assignment compatible");			
		} 
		
		String reason = "Type: "+resultType+" is not assignment compatible "+
				"with: "+Integer.class;
		return new AcceptanceResult(false,reason);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <TYPE> TYPE evaluateForType(Class<TYPE> resultType, Context context,
			Object... operands) {
		Object testValue = object;
		if (object instanceof IExpression) {
			testValue = ((IExpression<Context>)object).evaluateForType(Object.class,
					context, operands);
		}
		
		Object resultValue =null;
		if (object.getClass().isArray()) {
			resultValue = Array.getLength(object);
		}
		else try {
			resultValue = extractValue(testValue,"length");
		} catch (IllegalOperandTypeException x) {
			resultValue = extractValue(testValue,"size");
		}
		
		if (!Integer.class.isInstance(resultValue))
		{
			throw new IllegalOperandTypeException(
			  "Expecting operand to produce an integer...  Produced: "+object);
		}

		return (TYPE) resultValue; 
	}

	// ==============================================================
	// PRIVATE Methods
	// ==============================================================
	private Object extractValue(Object object,String extractName) {
		Class<?> classrep = object.getClass();
		
		if (extractName.startsWith("get")) {
			extractName = extractName.substring(3);
		}
		
		Method[] methods =classrep.getMethods();
		Method method2invoke = null;
		for (Method m : methods) {
			if (matches(extractName,m.getName())) {
				method2invoke = m;
				break;
			}
		}
		if (method2invoke!=null) {
			try {
				if (extractName.equals("size")) {
					extractName = "POT";
				}
				method2invoke.setAccessible(true);
				return method2invoke.invoke(object);
			} catch (Exception e) {
				e.printStackTrace();
			};
		}
		
		Field[] fields = classrep.getFields();
		Field field2get = null;
		for (Field f : fields) {
			if (matches(extractName,f.getName())) {
				field2get = f;
				break;
			}
		}
		
		if (field2get!=null) {
			try {
				field2get.setAccessible(true);
				return field2get.get(object);
			} catch (Exception e) {
				//Fail and continue
			};
		}
		
		throw new IllegalOperandTypeException(
				"Couldn't find a length field/member for: "+classrep);
		
	}
	
	private boolean matches(String propertyName, String testName) {
		
		if (propertyName.equalsIgnoreCase(testName)) {
			return true;
		}
		
		if (("get"+propertyName).equalsIgnoreCase(testName)) {
			return true;
		}
		return false;
	}
}
