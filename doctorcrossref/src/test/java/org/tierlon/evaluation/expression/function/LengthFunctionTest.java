package org.tierlon.evaluation.expression.function;

import java.io.IOException;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;
import org.tierlon.system.evaluation.expression.function.LengthFunction;
import org.tierlon.system.evaluation.expression.math.ConstantExpression;

public class LengthFunctionTest {

	Object[] testStringCases = {
			"ABC",3,
			"AbcDef",6,
			"",0,
			"This is a test of the functional length method",46
	};

	Object[][] testArrayCases = {
			{1.0,2.0,3.0}, {3},
			{"A","B"},{2},
			{}, {0},
			{new ConstantExpression<Object>("A"),
			 new ConstantExpression<Object>(new Double(3))
			}, {2}
	};
	
	@Test
	public void testStringLength() {
 		
		for (int testCaseNum = 0; testCaseNum<testStringCases.length; testCaseNum+=2) {
			String  testCase = (String) testStringCases[testCaseNum];
			Integer answer = (Integer) testStringCases[testCaseNum+1];
			
			LengthFunction<Object> lf = new LengthFunction<Object>(testCase);
			Object result =lf.evaluateForType(Object.class,null);
			System.out.println("testString $length  result: "+result+ 
					" vs expected: "+answer);
			Assert.assertEquals(answer, result);
		}
		System.out.println("DONE\n\n");
		
	}
	
	@Test
	public void testStringExpressionLength() {
 		
		for (int testCaseNum = 0; testCaseNum<testStringCases.length; testCaseNum+=2) {
			String  testCase = (String) testStringCases[testCaseNum];
			Integer answer = (Integer) testStringCases[testCaseNum+1];
			
			Object operand = new ConstantExpression<Object>(testCase);
			LengthFunction<Object> lf = new LengthFunction<Object>(operand);
			Object result =lf.evaluateForType(Object.class,null);
			System.out.println("testString *Expression* $length  result: "+result+ 
					" vs expected: "+answer);
			Assert.assertEquals(answer, result);
		}
		System.out.println("DONE\n\n");
		
	}
	
	@Test
	public void testArrayLengths() {
		for (int testCaseNum = 0; testCaseNum<testArrayCases.length; testCaseNum+=2) {
			Object[]  testCase = (Object[]) testArrayCases[testCaseNum];
			Integer answer =(Integer) 
					((Object[]) testArrayCases[testCaseNum+1])[0];
			
			LengthFunction<Object> lf = new LengthFunction<Object>(testCase);
			Object result =lf.evaluateForType(Object.class,null);
			System.out.println("test*ARRAY* $length  result: "+result+ 
					" vs expected: "+answer);
			Assert.assertEquals(answer, result);
		}
		
		System.out.println("DONE\n\n");
	}
	
	@Test
	public void testListLengths() {
		for (int testCaseNum = 0; testCaseNum<testArrayCases.length; testCaseNum+=2) {
			Object[]  testCase = (Object[]) testArrayCases[testCaseNum];
			Integer answer =(Integer) 
					((Object[]) testArrayCases[testCaseNum+1])[0];
			
			LengthFunction<Object> lf = 
					new LengthFunction<Object>(Arrays.asList(testCase));
			Object result =lf.evaluateForType(Object.class,null);
			System.out.println("test*LIST* $length  result: "+result+ 
					" vs expected: "+answer);
			Assert.assertEquals(answer, result);
		}
		
		System.out.println("DONE\n\n");
	}
}
