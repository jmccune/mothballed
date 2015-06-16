package org.tierlon.schema.support.parser;

import org.tierlon.transform.process.String2Token.IRegexString2TokenProcessor;
import org.tierlon.transform.process.String2Token.String2TokenContext;

public class ContextualFunctionParser  
	implements IRegexString2TokenProcessor {

	public static final String REGEX_PATTERN="\\w*\\$\\w+"; 

	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public ContextualFunctionParser() {
		//TODO: Map of the various functions that we can use.
		// Question is what does it produce or take?
		// This is something like a factory function that produces
		//   another factory that consumes a context to produce the
		//   actual token.
	}
	// ==============================================================
	// CONFIGURATION
	// ==============================================================
	
	
	// ==============================================================
	// IMPLEMENTATION
	// ==============================================================
	@Override
	public Object processString2Token(String2TokenContext context) {

		String value = context.getCurrentState();
		if (value.startsWith("$")) {
			value=value.substring(1);
			throw new IllegalArgumentException(
				"Unexpected input-- don't have operation defined for: "+value);
		}
		else  {
			throw new UnsupportedOperationException("Not yet supported");
		}
	}

	@Override
	public String getRegexPatternString() {
		return REGEX_PATTERN;
	}
}


//Idea is to change this -- not to specifically link each type
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
