package org.tierlon.system.helper;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringHelperTest {

	static Logger logger = Logger.getLogger(StringHelperTest.class);
	@BeforeClass
	public static void beforeTest() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}
	@Test
	public void testExtractInnerMost1() {
		
		String[] testCases=
			//INPUT     ANSWER: Part1, Part2, Part3 
			{ "ABCDEF",    "ABCDEF","","",
			  "AB(CD)EF",  "AB","CD","EF",
			  "(CD)EF",    "","CD","EF",
			  "AB(CD)",	   	"AB","CD","",
			  "AB(CD)EF(GH)","AB","CD","EF(GH)", 
			  "AB(CD(EF)GH)","AB(CD","EF","GH)"
			};
		
		for (int testCaseNum = 0; testCaseNum<testCases.length; testCaseNum+=4) {
			String  testCase = (String) testCases[testCaseNum];
			
			List<String> results=
					StringHelper.extractInnerMost('(',')',testCase);
			
			Assert.assertEquals(3,results.size());
			logger.info("\nTESTING>> "+testCase);
			for (int i=0; i<3; i++) {
				logger.debug("Expected: "+testCases[testCaseNum+i+1]+
								   " vs actual: "+results.get(i));
				
				Assert.assertEquals(testCases[testCaseNum+1+i],results.get(i));
			}
		}
		logger.info("DONE\n\n");
	}
	
	@Test
	public void safeSubstringTest() {
		
		Object[] testCases=
			//STRING  , IDX1, IDX2     >> ANSWER
			{ "ABCDEFGHIJK", 0,0, 			 "",
			  "ABCDEFGHIJK", 0,4, 			 "ABCD",
			  "ABCDEFGHIJK", -1,-2,			 "J",
			  "ABCDEFGHIJK", -1,-4,          "JIH",
			  "ABCDEFGHIJK", 3,-15,			 "CBA",
			  "ABCDEFGHIJK", 0,15,		     "ABCDEFGHIJK",
			  "ABCDEFGHIJK", -4,15,		     "HIJK",
			  "ABCDEFGHIJK", 5,7,			 "FG"
			};
		
		for (int testCaseNum = 0; testCaseNum<testCases.length; testCaseNum+=4) {
			String  testCase = (String) testCases[testCaseNum];
			Integer startIndex = (Integer) testCases[testCaseNum+1];
			Integer endIndex = (Integer) testCases[testCaseNum+2];
			String  expectedResult = (String) testCases[testCaseNum+3];
			
			String actualResult = 
					StringHelper.safeSubstring(testCase,startIndex,endIndex);
			logger.info("TESTING>> "+testCase+" , "+startIndex+" , "+endIndex);
			logger.debug("Expected: "+expectedResult+
								   " vs actual: "+actualResult);
				
			Assert.assertEquals(expectedResult,actualResult);
			
		}
		logger.info("DONE\n\n");
	}
	
	
}
