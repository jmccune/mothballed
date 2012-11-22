package org.tierlon.evaluation.expression.logic;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComparisonExpressionTest {

	static Object[][] testCases = {
		{1,2},		
		{2,1},	
		{2,2},
		{-1,-2},
		{-2,-1},
		// Same tests with floating points.
		{1.1,2.1},
		{2.1,1.9},
		{2.0,2.0},
		{-1.9,-2.0},
		{-2.0,-1.9},
		//Variable Length
		{1,2,3,4,5},
		{-5,-4,-3,-2,-1,0,1,2,3,4,5},
		{5,4,3,2,1},
		{5,4,3,2,1,0,-1,-2},
		{3.14159,3.14159,3.14159,3.14159,3.14159,3.14159},
		{2.71,2.71,2.7099999,2.71,2.71},
		// Interesting Cases
		{2.0001,2.0},
		{2.0,2.0001},	
		{5,5,4,4,3,3},
		{3,3,4,4,5,5}
	};
	
	//NOTE: The Not EQUAL operator is a funny one when operating
	//      across multiple values.  What does it mean?
	//      (It probably should mean that no value is equal to any other
	//       value in the set...)
	
	static Object[] LtEqAnswers=
		{
		 true,false,true,false,true,   true,false,true,false,true,
		 true,true,false,false,true,false,
		 false,true,false,true
		};
	
	static Object[] LtAnswers=
		{
		 true,false,false,false,true,   true,false,false,false,true,
		 true,true,false,false,false,false,
		 false,true,false,false
		};
	
	
	static Object[] EqAnswers=
		{
		 false,false,true,false,false,   false,false,true,false,false,
		 false,false,false,false, true, false,
		 false,false,false,false,		 
		};
	
	static Object[] NotEqAnswers=
		{
		 true,true,false,true,true,     true,true,false,true,true,
		 true,true,true,true,false,false,   //Note: The last one here
		 true,true,false,false  //Note: These last 2		 
		};
	
	static Object[] GtAnswers=
		{
		  false,true,false,true,false,   false,true,false,true,false,
		  false,false,true,true,false,false,
		  true,false,false,false
		};
	
	static Object[] GtEqAnswers=
		{
		  false,true,true,true,false,   false,true,true,true,false,
		  false,false,true,true,true,false,
		  true,false,true,false
		};
	
	static Logger logger = Logger.getLogger(ComparisonExpressionTest.class);
	@BeforeClass
	public static void beforeTest() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}
	
	@Test
	public void testComparisonGreaterThanOrEqualExpression() {
		testOperation(">=",GtEqAnswers);
	}
	@Test
	public void testComparisonGreaterThanExpression() {
		testOperation(">",GtAnswers);
	}
	
	@Test
	public void testComparisonEqualExpression() {
		testOperation("==",EqAnswers);
	}
	@Test
	public void testComparisonLessThanOrEqualExpression() {
		testOperation("<=",LtEqAnswers);
	}
	
	@Test
	public void testComparisonLessThanExpression() {
		testOperation("<",LtAnswers);
	}
	
	@Test
	public void testComparisonNotEqualExpression() {
		testOperation("!=",NotEqAnswers);
	}
	
	@Test
	public void testComparisonNotEqualExpression2() {
		ComparisonExpression<Object,Number> expression =
				new ComparisonExpression<Object,Number>(Number.class,"!=");
		
		expression.addOperands(1.0);
		expression.addOperands(2.0);
		expression.addOperands(1.0);
		expression.addOperands(2.0);
		Boolean result =expression.evaluateForType(Boolean.class,null);	
		logger.debug("Testing: 1,2,1,2 >>> CUSTOM");
		logger.info("PRESET *Comparison(!=)*>> CALCULATED ANSWER: "+result+ 
				" vs expected: true");
		Assert.assertEquals(result,(Boolean)true);
	}
	
	

	
	
	
	// ==============================================================
	// Test Harness
	// ==============================================================
	public void testOperation(String comparatorType, Object[] answers) {
		for (int testNum =0; testNum<testCases.length; testNum++) {
			ComparisonExpression<Object,Number> expression =
					new ComparisonExpression<Object,Number>(Number.class,comparatorType);
			
			Object[] operandArray = testCases[testNum];
			expression.addOperands(operandArray);
			Boolean result =expression.evaluateForType(Boolean.class,null);	
			logger.debug("Testing: "+operandsAsString(operandArray));
			logger.info("PRESET *Comparison("+comparatorType+")*>> CALCULATED ANSWER: "+result+ 
					" vs expected: "+answers[testNum]);
			Assert.assertEquals(answers[testNum],result);
			
		}
		logger.info("DONE\n\n\n");
	}
	
	public String operandsAsString(Object... operands) {
		StringBuilder sb = new StringBuilder();
		for (Object operand: operands) 
			sb.append(operand).append(" ");
		return sb.toString();
	}
}
