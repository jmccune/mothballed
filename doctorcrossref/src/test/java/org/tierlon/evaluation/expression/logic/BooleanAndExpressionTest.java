package org.tierlon.evaluation.expression.logic;

import junit.framework.Assert;

import org.junit.Test;
import org.tierlon.system.evaluation.AcceptanceResult;
import org.tierlon.system.evaluation.IAcceptanceResult;
import org.tierlon.system.evaluation.expression.logic.BooleanAndExpression;
import org.tierlon.system.evaluation.expression.math.ConstantExpression;

public class BooleanAndExpressionTest {

	
	static Object[][] testCases = { {false},  //false
		{true},          //true
        {false,true},    //false
        {true,false},    //false
        {true,true},     //true
        
        {true,true,true,false,true,true}, // false
        {true,true,true,true,true,true}, // true
        {true,false,true},//false
        {true,true,true},//true
	};
	
	static Boolean[] answers={false,true,false,false,true,
		false,true,false,true};
	
	@Test
	public void testPresetAdd() {
		
		for (int testNum =0; testNum<testCases.length; testNum++) {
			BooleanAndExpression<Object> expression =
					new BooleanAndExpression<Object>();
			
			Object[] operandArray = testCases[testNum];
			expression.addOperands(operandArray);
			Boolean result =expression.evaluateForType(Boolean.class,null);
			System.out.println("PRESET *AND*>> CALCULATED ANSWER: "+result+ 
					" vs expected: "+answers[testNum]);
			Assert.assertEquals(answers[testNum],result);
			
		}
		System.out.println("DONE\n\n\n");
	}
	
	@Test
	public void testPresetAdd2() {
		
		for (int testNum =0; testNum<testCases.length; testNum++) {
			BooleanAndExpression<Object> expression =
					new BooleanAndExpression<Object>();
			
			Object[] operandArray = testCases[testNum];
			expression.addOperands(operandArray);
			IAcceptanceResult result =expression.evaluateForType(
					IAcceptanceResult.class,null);
			System.out.println("PRESET *AND2*>> CALCULATED ANSWER: "+result+ 
					" vs expected: "+answers[testNum]);
			
			Assert.assertEquals(answers[testNum],
					(Boolean)result.isAcceptable());
			
		}
		System.out.println("DONE\n\n\n");
	}
	
	@Test 
	public void testRuntimeAdd2() {
		for (int testNum =0; testNum<testCases.length; testNum++) {
			BooleanAndExpression<Object> expression =
					new BooleanAndExpression<Object>();
			
			Object[] operandArray = testCases[testNum];
			//expression.addOperands(operandArray);
			IAcceptanceResult result =expression.evaluateForType(
					IAcceptanceResult.class,null,operandArray);
			System.out.println("RUNTIME *AND2*>> CALCULATED ANSWER: "+result+ 
					" vs expected: "+answers[testNum]);
			
			Assert.assertEquals(answers[testNum],
					(Boolean)result.isAcceptable());
			
		}
		System.out.println("DONE\n\n\n");
	}
	
	@Test 
	public void testExpressionAdd2() {
		for (int testNum =0; testNum<testCases.length; testNum++) {
			BooleanAndExpression<Object> expression =
					new BooleanAndExpression<Object>();
			
			Object[] operandArray = testCases[testNum];
			for (Object operand: operandArray) {
				AcceptanceResult testVal = 
						new AcceptanceResult((Boolean)operand,"Test");
				expression.addOperands(new ConstantExpression<Object>(testVal));	
			}
			IAcceptanceResult result =expression.evaluateForType(
					IAcceptanceResult.class,null);
			System.out.println("EXPRESSION *AND2*>> CALCULATED ANSWER: "+result+ 
					" vs expected: "+answers[testNum]);
			
			Assert.assertEquals(answers[testNum],
					(Boolean)result.isAcceptable());
			
		}
		System.out.println("DONE\n\n\n");
	}
	
	
	
	
}
