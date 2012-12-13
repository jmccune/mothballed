package org.tierlon.schema.support.parser;

import java.util.Map;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tierlon.evaluation.expression.IExpression;
import org.tierlon.transform.process.String2Token.IString2TokenProcessor;
import org.tierlon.transform.process.String2Token.String2TokenContext;

public class MultiRegexParserTest {
	static Logger logger = Logger.getLogger(MultiRegexParserTest.class);
	@BeforeClass
	public static void beforeTest() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMultiRegexParser() {
		
		Object testData[] = {
			"9 != 10 ", true,
			"9<10" , true,
			"10<9" , false,
			"10!=10", false,
			"9>=9", true,
			"10>=9", true,
			"9>=10", false,
			"10>9 && 9>8", true,
			"10<9 && 9<8", false,
			"10<9 && 9>8", false,
			"10<9 || 9>8", true,
			"10<9 || 9<8 || 8<7", false,
			"10<9 || 9<8 || 8>7", true,
			"10 < 9 ||  9 < 8 ||  8> 7", true,
			
		};
		
		MultiRegexParser<Object> parser = new MultiRegexParser<Object>(
				new IString2TokenProcessor() {
					@Override
					public Object processString2Token(
							String2TokenContext context) {
						
						String obj = context.getCurrentState();						
						return Double.valueOf(obj);
						
					}});
		
		RegexProcessorFactory<Object> factory = 
				new RegexProcessorFactory<Object>();
		
		//NOTE: This must come before comparison 
		Map<String, IString2TokenProcessor> booleanRegexProcessors = 
				factory.generateBooleanParsers();
		
		Map<String, IString2TokenProcessor> comparisonRegexProcessors = 
				factory.generateComparisonParsers();
		
		
		//parser.addRegexProcessorFamily("token",factory.generateTokenProcessor());
		parser.addRegexProcessorFamily("boolean",booleanRegexProcessors);
		parser.addRegexProcessorFamily("comparison", comparisonRegexProcessors);
		
		
		
		for (int i=0; i<testData.length; i+=2) {
			String parseTestString  = (String) testData[i];
			Boolean expectedResult = (Boolean) testData[i+1];
			
			Object parseResult = parser.parse(parseTestString);
			IExpression<Object> resultExpression = (IExpression<Object>)parseResult;
			Boolean result =resultExpression.evaluateForType(Boolean.class, null);
			logger.debug("TEST STRING: "+parseTestString);
			logger.debug("TEST RESULT expected: "+expectedResult+" vs. actual: "+result);
			Assert.assertEquals(expectedResult, result);
		}
		
	}
}
