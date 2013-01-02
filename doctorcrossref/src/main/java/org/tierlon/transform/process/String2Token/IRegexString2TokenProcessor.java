package org.tierlon.transform.process.String2Token;

public interface IRegexString2TokenProcessor extends IString2TokenProcessor {

	/** 
	 * Return the regular expression string that is assumed to have been
	 * matched before this TokenProcessor is invoked.
	 * 
	 * @return (non-null) the string that represents the regular expression
	 * that will validate this Processor as being able to process the 
	 * data.
	 */
	public String getRegexPatternString();
}
