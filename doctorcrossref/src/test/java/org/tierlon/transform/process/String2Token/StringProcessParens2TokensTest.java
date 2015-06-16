package org.tierlon.transform.process.String2Token;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tierlon.transform.process.String2Token.IString2TokenProcessor;
import org.tierlon.transform.process.String2Token.String2TokenContext;
import org.tierlon.transform.process.String2Token.StringProcessParens2Tokens;

public class StringProcessParens2TokensTest {

	static Logger logger = 
		Logger.getLogger(StringProcessParens2TokensTest.class);
	
	@BeforeClass
	public static void beforeTest() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}
	@Test
	public void testParenProcessing1() {
		
		Object testCases[]= {
				"ABCD","(ABCD)",
				"(ABCD)","(ABCD)(T0)",
				"(AB)||(CD)","(AB)(CD)(T0||T1)",
				"(AB)||(CD)&&(EF)||(GH)","(AB)(CD)(EF)(GH)(T0||T1&&T2||T3)",
				"(AB||CD)&&(EF||GH)",  "(AB||CD)(EF||GH)(T0&&T1)",	
				"((AB||CD)&&(EF||GH))",  "(AB||CD)(EF||GH)(T0&&T1)(T2)",				
		};
		
		for (int i=0; i<testCases.length; i+=2) {
			
			String testString = (String) testCases[i];
			String answer = (String) testCases[i+1];
			
			TestParenProcessing1Helper subprocessor = 
					new TestParenProcessing1Helper();
			StringProcessParens2Tokens processor = 
					new StringProcessParens2Tokens(subprocessor);
			
			String2TokenContext context = new String2TokenContext(testString);			
			processor.processString2Token(context);
			String actualResult = subprocessor.getFinalResult(context);
			
			logger.debug("Expected: "+answer+" >> Received: "+actualResult);
			Assert.assertEquals(answer, actualResult);
		}
		
	}
	
	
	
	
}

class TestParenProcessing1Helper implements IString2TokenProcessor {

	StringBuilder sb =new StringBuilder();
	int count;
	
	@Override
	public Object processString2Token(String2TokenContext context) {
		sb.append('(').append(context.getCurrentState()).append(')');
		String tokenNum = "T"+count;
		count++;
		return tokenNum;
	}
	
	public String getFinalResult(String2TokenContext context) {
		String originalResult= sb.toString();
		String result = originalResult;
		for (String tokenName : context.getAllTokenNames())
		{
			result = result.replace(tokenName,
					context.getToken(tokenName).toString());
		}
		result =result.replace(" ","");
		return result;
	}
}
