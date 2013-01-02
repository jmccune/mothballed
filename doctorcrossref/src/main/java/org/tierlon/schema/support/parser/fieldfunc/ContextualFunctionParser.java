package org.tierlon.schema.support.parser.fieldfunc;

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
