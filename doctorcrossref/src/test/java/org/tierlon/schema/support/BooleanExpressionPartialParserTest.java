package org.tierlon.schema.support;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;


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
 * 				!( <EXPRESSION> )
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

	@BeforeClass
	public static void beforeTest() {
		BasicConfigurator.configure();
	}
	
	@Test
	public void testExpressionParser() throws IOException {
 
		Object hasNextLineTestCases[] = {
				"",false,
				"ABCDEF",true,
				"#ABCDEF",false,
				"   #ABCDEF",false,
				"ABC#DEF", true,
				"\t",false,
				"   ",false
		};
		
		for (int i=0; i<hasNextLineTestCases.length; i+=2)
		{
			String testString = (String) hasNextLineTestCases[i];
			boolean expectedResult  = (Boolean) hasNextLineTestCases[i+1];
			SimpleLineLexer lexer = new SimpleLineLexer(testString);
			boolean result = lexer.hasNextLine();
			if (expectedResult!=result) {
				System.out.println("FAILED: "+testString+" expected: "+expectedResult+ " actual: "+result);
			}
			Assert.assertEquals(result,expectedResult);
			
		}
	}
	
}
