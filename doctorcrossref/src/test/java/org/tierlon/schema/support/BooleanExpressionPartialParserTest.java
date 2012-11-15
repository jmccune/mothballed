package org.tierlon.schema.support;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tierlon.system.helper.StringHelperTest;


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
		Logger.getRootLogger().setLevel(Level.INFO);
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
	
}
