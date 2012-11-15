package org.tierlon.schema.support;

import org.tierlon.transform.process.IString2TokenProcessor;
import org.tierlon.transform.process.RegexBasedStringProcessor;
import org.tierlon.transform.process.String2TokenContext;

public class BooleanExpressionPartialParser {

	public static final String NOT_REGEX = "NOT\\s*__Token[0-9]+__";
	
	// Simply || but escaped for strings+regex
	private static final String OR_TKN="\\|\\|";
	private static final String AND_TKN="&&";
	
	public static final String OR_REGEX = 
		".*?"+OR_TKN+"\\s*\\S.*?"+
		"("+OR_TKN+"\\s*\\S.*?)*";
	public static final String AND_REGEX=
			".*?"+AND_TKN+"\\s*\\S.*?"+
			"("+AND_TKN+"\\s*\\S.*?)*";
	
	public void parse(String string) {
		
	}



	class BooleanRuleProcessor extends RegexBasedStringProcessor {

		public BooleanRuleProcessor() {

			super();

			super.addRegexProcessor(NOT_REGEX, new NegationProcessor());

			super.addRegexProcessor(OR_REGEX, new OrProcessor());
		}
	}

	class NegationProcessor implements IString2TokenProcessor {

		@Override
		public Object processString2Token(String2TokenContext context) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	class OrProcessor implements IString2TokenProcessor {

		@Override
		public Object processString2Token(String2TokenContext context) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}