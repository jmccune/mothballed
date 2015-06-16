package org.tierlon.schema.support.parser.fieldfunc;

import org.tierlon.transform.process.String2Token.IRegexString2TokenProcessor;
import org.tierlon.transform.process.String2Token.String2TokenContext;

public class StringFieldFuncParser<ExpressionContext> 
	implements IRegexString2TokenProcessor {

	public static final String REGEX_PATTERN="\\w*\\$((label)|(length))"; 
	
	@Override
	public Object processString2Token(String2TokenContext context) {

		String value = context.getCurrentState();
		System.out.println(">>>>>>>>>"+value);
		//***** WORKING HERE ****(2)
		//  Idea is to change this -- not to specifically link each type
		// But to do a composite
		//	
		// 		part 1 is before the $
		//		e.g.  an implicit this
		//            or an array []
		//			  or some function
		//			  or other thing
		//			  and then the following part 2
		//		part2 is after the $ 
		//			  e.g. length
		//			  	   isUpperCase   
		//				   etc.
		//      where the context of whether it works or not is purely context
		//      driven.
		//
		//      The idea is to allow us to arbitrarily define a set of 
		//      operations to do on strings:
		//		$length				->   ...expression.function.LengthFunction
		//		$isUpperCase		->	 ...expression.function.IsUpperCaseFunc
		//		$isSomeIdPatternValid -> ...custom.userdefined.IdValidatorFunc
		//		$orSomeOtherReallyCustomThing   ->custom.ReallyCustomProcessor
		//		Which can be defined by context & by loader.
		//	
		return null;
	}

	@Override
	public String getRegexPatternString() {
		return REGEX_PATTERN;
	}
	
	static public void main(String args[]) {
		
		String[] testCases={"abc$length","abc$false","abc$length"};
		for (String testCase : testCases) {
			boolean result= testCase.matches(REGEX_PATTERN);
			System.out.println("TEST: "+testCase+" >> "+result);
		}
	}
}
