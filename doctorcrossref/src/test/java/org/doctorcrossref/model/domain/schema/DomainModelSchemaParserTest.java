package org.doctorcrossref.model.domain.schema;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;


public class DomainModelSchemaParserTest {

	@BeforeClass
	public static void beforeTest() {
		BasicConfigurator.configure();
	}
	
	@Test
	public void testModelParser() throws IOException {
		Object testCases[] = {
				"alpha: \n"+
				"    label:string@($length>0 && $length<128)\n"+
				"    description:string\n"+
				"    createdDate:date\n"+
				"    type:string@(org.tierlon.etc.TypeValidator)#\"NONE\"",true
		};
		
		for (int i=0; i<testCases.length; i+=2)
		{
			String testString = (String) testCases[i];
			boolean expectedResult  = (Boolean) testCases[i+1];
			
			
			DomainModelSchemaParser parser = new DomainModelSchemaParser();
		
			try {
				//ModelSchema schema = 
				parser.parseModels(testString);
				
			}
			catch (Exception x) {
				x.printStackTrace();
				Assert.assertEquals(expectedResult,false);
			}
						
		}
	}
}
