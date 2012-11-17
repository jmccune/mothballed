package org.tierlon.schema.support.parser;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tierlon.evaluation.expression.IExpression;
import org.tierlon.schema.support.parser.BooleanExpressionPartialParser;
import org.tierlon.system.helper.StringHelperTest;
import org.tierlon.transform.process.IString2TokenProcessor;
import org.tierlon.transform.process.String2TokenContext;


/**
 * This may be used to make more common the processing of the boolean logic
 * that works across the various validations.  As it is generic, and a first
 * cut, it simplifies alot of things.
 * 
 * 
 * 
 * @author justinanddiana
 *
 */


/*
 * The grammar is expressed:
 * 
 * 	  <EXPRESSION> :   
 * 				<ATOMIC_EXPRESSION> | <NOT_EXPRESSION> | 
 * 				<OR_EXPRESSION>     | <AND_EXPRESSION>
 * 			
 * 						
 * 
 * 	  <NOT_EXPRESSION> :
 * 				NOT( <EXPRESSION> )
 * 
 * 	  <OR_EXPRESSION> :  
 *    			( <EXPRESSION> || <EXPRESSION> [<OR_EXPRESSION_ELLIPSIS>] )
 *    
 *    <OR_EXPRESSION_ELLIPSIS>:   
 *    			|| <EXPRESSION> [<OR_EXPRESSION_ELLIPSIS>]
 *    
 *    <AND_EXPRESSION> :  
 *    			( <EXPRESSION> && <EXPRESSION> [<AND_EXPRESSION_ELLIPSIS>] )
 *    
 *    <AND_EXPRESSION_ELLIPSIS>:   
 *    			&& <EXPRESSION> [<AND_EXPRESSION_ELLIPSIS>]
 *    
 *    
 *    
 * 	  <ATOMIC_EXPRESSION> :  **any pattern that does not match the above**
 * 	  
 */
public class BooleanExpressionPartialParserTest {

	static Logger logger = Logger.getLogger(StringHelperTest.class);
	@BeforeClass
	public static void beforeTest() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}
	@Test
	public void testNotRegularExpressions() throws IOException {
 
		Object testCases[] = {
				
				"ABCDEF",false,
				"NOT__Token12__",true,
				"NOT __Token1__",true,
				"NOT __TOKEN2__",false,
				"NOT __TokenX__",false,
				"NOT Token3",false
		};
		
		logger.info("TESTING REGEX: "+BooleanExpressionPartialParser.NOT_REGEX);
		for (int i=0; i<testCases.length; i+=2)
		{
			String testString = (String) testCases[i];
			boolean expectedResult  = (Boolean) testCases[i+1];
			boolean actualResult = testString.matches(
					BooleanExpressionPartialParser.NOT_REGEX);
			logger.debug("Testing: "+testString);
			logger.debug("Expected: "+expectedResult+
					" vs. actual: "+actualResult);
			Assert.assertEquals(expectedResult,actualResult);
				
		}
		logger.info("DONE\n\n");
	}
	
	@Test
	public void testOrRegularExpressions() throws IOException {
 
		Object testCases[] = {
				
				"ABCDEF",false,
				"ABC || DEF",true,
				"ABC||DEF",true,
				"ABC|DEF",false,
				"ABC||D",true,
				
				//TODO: Rewrite regexp to pass this case...
				//"ABC|||DEF",false,
				"ABC || DEF || GHI",true,
				" ABC || DEF || GHI ",true,
		};
		
		logger.info("TESTING REGEX: "+BooleanExpressionPartialParser.OR_REGEX);
		for (int i=0; i<testCases.length; i+=2)
		{
			String testString = (String) testCases[i];
			boolean expectedResult  = (Boolean) testCases[i+1];
			boolean actualResult = testString.matches(
					BooleanExpressionPartialParser.OR_REGEX);
			logger.debug("Testing: "+testString);
			logger.debug("Expected: "+expectedResult+
					" vs. actual: "+actualResult);
			Assert.assertEquals(expectedResult,actualResult);
				
		}
		logger.info("DONE\n\n");
	}
	
	
	@Test
	public void testAndRegularExpressions() throws IOException {
 
		Object testCases[] = {
				
				"ABCDEF",false,
				"ABC && DEF",true,
				"ABC&&DEF",true,
				"ABC&DEF",false,
				"ABC&&D",true,
				
				//TODO: Rewrite regexp to pass this case...
				//"ABC&&&DEF",false,
				"ABC && DEF && GHI",true,
				" ABC && DEF && GHI ",true,
		};
		
		logger.info("TESTING REGEX: "+BooleanExpressionPartialParser.OR_REGEX);
		for (int i=0; i<testCases.length; i+=2)
		{
			String testString = (String) testCases[i];
			boolean expectedResult  = (Boolean) testCases[i+1];
			boolean actualResult = testString.matches(
					BooleanExpressionPartialParser.AND_REGEX);
			logger.debug("Testing: "+testString);
			logger.debug("Expected: "+expectedResult+
					" vs. actual: "+actualResult);
			Assert.assertEquals(expectedResult,actualResult);
				
		}
		logger.info("DONE\n\n");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testPartialParserExpression() {
		
		Object testCases[] = {
				//Test String					//Test Result
				"True"							,true,
				"False"							,false,
				"NOT(True)"						,false,
				"NOT(False)"					,true,
				"(NOT(True))"					,false,
				"(NOT(False))"					,true,
				"True || False"					,true,
				"False || False"				,false,
				"(True || False)"				,true,
				"(False || False)"				,false,
				"(False || True || False)"		,true,
				"(False || False || False)"		,false,
				"NOT(False || True)"			,false,
				"(False || NOT(True))"			,false,
				"(NOT(False) || False)"			,true,
				"(NOT(False) || NOT(False))"	,true,
				"NOT(False || False)"			,true,
				"NOT(False || True)"			,false,
				"NOT(True || False)"			,false,
				"NOT(True || True)"				,false,
				"NOT(True || True || False)"	,false,
				"NOT(False || False || False)"	,true,
				"True && True"					,true,
				"True && False"					,false,
				"False && False"				,false,
				"False && True"					,false,
				"NOT(True && True)"				,false,
				"NOT(True && False)"			,true,
				"(NOT(False && False))"			,true,
				"(NOT(False && True))"			,true,
				"(NOT(NOT(False) && True))"		,false,
				"(NOT(NOT(False) && NOT(True)))",true,
				"((NOT(True) || NOT(False) || NOT(True)) &&"+
				"(True || False) && " +
				"NOT(False || False ||False))", true,
				"((NOT(True) || NOT(False) || NOT(True)) &&"+
				"(True || False) && " +
				"NOT(False || NOT(False) ||False))", false,
		};
		
		PartialParserTestHelper1 testHelper =new PartialParserTestHelper1();
		BooleanExpressionPartialParser boolParser = 
				new BooleanExpressionPartialParser(testHelper);
		
		for (int i=0; i<testCases.length; i+=2) {
			String testCase = (String) testCases[i];
			Boolean expectedResult = (Boolean) testCases[i+1];
		
			//String2TokenContext context = new String2TokenContext(testCase);
			//Object actualResult = testHelper.processString2Token(context);
			Object actualResult = boolParser.parse(testCase);
			if (actualResult instanceof IExpression) {
				actualResult = ((IExpression)actualResult).evaluateForType(Boolean.class, null);
			}
			logger.debug("Testing: "+testCase);
			logger.debug("Expected: "+expectedResult+
					" vs. actual: "+actualResult);
			Assert.assertEquals(expectedResult,actualResult);
		
		}		
		logger.info("DONE\n\n");
	
	}
	
}





class PartialParserTestHelper1 implements IString2TokenProcessor {

	static Logger logger = Logger.getLogger(PartialParserTestHelper1.class);
	@SuppressWarnings("unchecked")
	@Override
	public Object processString2Token(String2TokenContext context) {
		
		String string = context.getCurrentState();
		logger.debug("Received: "+string);
		if (string.trim().startsWith("__Token")) {			
			Object value = context.getToken(string.trim());
			logger.debug("    which By Context is : "+value);
			if (value instanceof IExpression) {
				value = ((IExpression)value).evaluateForType(Boolean.class, null);
				logger.debug("   evaluating to: "+value);
				return value;
			}
		}
		return Boolean.valueOf(string);
	}
	
}