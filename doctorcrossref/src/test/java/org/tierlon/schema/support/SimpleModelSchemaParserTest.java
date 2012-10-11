package org.tierlon.schema.support;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.tierlon.schema.ModelSchema;

public class SimpleModelSchemaParserTest {

	@Test
	public void testLexer() throws IOException {
 
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
	
	@Test
	public void testEntityRegex() {
		
		Object testCases[] = {
				"Alpha:",true,
				"Alpha:Bravo:",true,
				"Alpha:Bravo:Charlie:",false,
				"Alpha>Bravo:",true,
				"Alpha>Bravo>Charlie:",false,
				"Alpha:Bravo>Charlie:",true,
				"Alpha:Bravo>Charlie:Delta:",true,
				" Alpha:",true,
				"  Alpha:Bravo:",true,
				" Alpha:Bravo:Charlie:",false,
				"  Alpha>Bravo:",true,
				" Alpha>Bravo>Charlie:",false,
				"  Alpha:Bravo>Charlie:",true,
				" Alpha:Bravo>Charlie:Delta:",true,
				"    Alpha:",false,
				"    Alpha:Bravo:",false,
				"    Alpha:Bravo:Charlie:",false,
				"    Alpha>Bravo:",false,
				"    Alpha>Bravo>Charlie:",false,
				"    Alpha:Bravo>Charlie:",false,
				"    Alpha:Bravo>Charlie:Delta:",false,
				" Alpha | Bravo > Charlie:",true,
				" Alpha:Bravo | Charlie:Delta > Echo:Foxtrot:",true,
				" Alpha:Bravo | Charlie:Delta | Echo:Foxtrot > Golf:Hotel:",true,
		};
		
		
		for (int i=0; i<testCases.length; i+=2)
		{
			String testString = (String) testCases[i];
			boolean expectedResult  = (Boolean) testCases[i+1];
			boolean result = testString.matches(ModelParser.ENTITY_REGEX);
			if (expectedResult!=result) {
				System.out.println("FAILED: >>>>"+testString+"<<<< expected: "+expectedResult+ " actual: "+result);
			}
			Assert.assertEquals(result,expectedResult);
			
		}
		
	}

	@Test
	public void testModelParser() throws IOException {
		Object testCases[] = {
				"alpha > bravo : \n"+
				"    label:string@($length>0 && $length<128)\n"+
				"    description:string\n"+
				"    createdDate:date\n"+
				"    type:string@(org.tierlon.etc.TypeValidator)#\"NONE\"",true
		};
		
		for (int i=0; i<testCases.length; i+=2)
		{
			String testString = (String) testCases[i];
			boolean expectedResult  = (Boolean) testCases[i+1];
			
			SimpleLineLexer lexer = new SimpleLineLexer(testString);
			ModelParser parser = new ModelParser();
			
			try {
				ModelSchema schema = parser.parseData(lexer);
				Assert.assertEquals(expectedResult,true);
			}
			catch (Exception x) {
				x.printStackTrace();
				Assert.assertEquals(expectedResult,false);
			}
						
		}
	}
}
