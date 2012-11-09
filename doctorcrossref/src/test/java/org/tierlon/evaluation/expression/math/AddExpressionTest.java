package org.tierlon.evaluation.expression.math;

import junit.framework.Assert;

import org.junit.Test;
import org.tierlon.system.evaluation.expression.math.AddExpression;
import org.tierlon.system.evaluation.expression.math.ConstantExpression;

public class AddExpressionTest {

	static Object[][] testCases = { {0.0},   //0.0
			{1.0, 2.0, 3.0},          //6.0
	        {1 ,2, 3},                //6.0
	        {1, 2.0, 3.0, 4},         //10.0
	        {-1, -2, -3, -4},		  //-10
	        {-1.0, -2.0, -3.0,-4.0}   //-10
	};
	
	static Double [] answers = { 0.0, 6.0, 6.0, 10.0,-10.0,-10.0};
	
	@Test
	public void testPresetAdd() {
		
		for (int testNum =0; testNum<testCases.length; testNum++) {
			AddExpression<Object> expression =new AddExpression<Object>();
			Object[] operandArray = testCases[testNum];
			expression.addOperands(operandArray);
			Double result =expression.evaluateForType(Double.class,null);
			System.out.println("PRESET ADD>> CALCULATED ANSWER: "+result+ 
					" vs expected: "+answers[testNum]);
			Assert.assertEquals(answers[testNum],result);
			
		}
		System.out.println("DONE\n\n\n");
	}
	
	@Test
	public void testRuntimeAdd() {
		
		for (int testNum =0; testNum<testCases.length; testNum++) {
			AddExpression<Object> expression =new AddExpression<Object>();
			Object[] operandArray = testCases[testNum];
			//expression.addOperands(operandArray);
			Double result =expression.evaluateForType(Double.class,null,
													  operandArray);
			System.out.println("RUNTIME ADD>> CALCULATED ANSWER: "+result+ 
					" vs expected: "+answers[testNum]);
			Assert.assertEquals(answers[testNum],result);
		}
		System.out.println("DONE\n\n\n");
	}
	
	
	@Test
	public void testExpressionAdd() {
		
		for (int testNum =0; testNum<testCases.length; testNum++) {
			AddExpression<Object> expression =new AddExpression<Object>();
			Object[] operandArray = testCases[testNum];
			for (Object operand: operandArray) {
				expression.addOperands(new ConstantExpression<Object>(operand));	
			}
			
			Double result =expression.evaluateForType(Double.class,null);
			System.out.println("Expression ADD>> CALCULATED ANSWER: "+result+ 
					" vs expected: "+answers[testNum]);
			Assert.assertEquals(answers[testNum],result);
		}
		System.out.println("DONE\n\n\n");
	}
	
}
