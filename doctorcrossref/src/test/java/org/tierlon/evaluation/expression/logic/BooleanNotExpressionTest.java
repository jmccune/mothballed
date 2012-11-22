package org.tierlon.evaluation.expression.logic;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tierlon.evaluation.IAcceptanceResult;
import org.tierlon.evaluation.expression.math.ConstantExpression;
import org.tierlon.system.helper.StringHelperTest;

public class BooleanNotExpressionTest {

	
	static Logger logger = Logger.getLogger(StringHelperTest.class);
	@BeforeClass
	public static void beforeTest() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}
	
	
	static Object[] testCases = { 
		//Test    >> Answer
		true	,   false,
		false 	,   true
	};
	
	@Test
	public void testPresetNot() {
		
		for (int testNum =0; testNum<testCases.length; testNum+=2) {
			BooleanNotExpression<Object> expression =
					new BooleanNotExpression<Object>();
			
			Boolean operand = (Boolean) testCases[testNum];
			Boolean expectedResult = (Boolean) testCases[testNum+1];
			
			expression.addOperands(operand);
			Boolean result =expression.evaluateForType(Boolean.class,null);
			logger.debug("Expected: "+expectedResult+ " vs actual: "+result);
			Assert.assertEquals(expectedResult,result);
			
		}
		logger.debug("DONE\n\n\n");
	}
	
	@Test 
	public void testExpressionNot() {
		for (int testNum =0; testNum<testCases.length; testNum+=2) {
			BooleanNotExpression<Object> expression =
					new BooleanNotExpression<Object>();
			
			Boolean operand = (Boolean) testCases[testNum];
			Boolean expectedResult = (Boolean) testCases[testNum+1];
			
			expression.addOperands(new ConstantExpression<Object>(operand));				
			IAcceptanceResult result =expression.evaluateForType(
					IAcceptanceResult.class,null);
			
			Boolean actualResult = result.isAcceptable();
			logger.debug("Expected: "+expectedResult+ " vs actual: "+result);
			Assert.assertEquals(expectedResult,actualResult);
		}
		System.out.println("DONE\n\n\n");
	}
	
}
