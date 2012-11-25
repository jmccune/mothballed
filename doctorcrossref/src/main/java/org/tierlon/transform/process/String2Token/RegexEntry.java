package org.tierlon.transform.process.String2Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Mostly a simple pairing of the regular expression and the 
 * processor that goes with it. 
 * @author justinanddiana
 *
 */
public class RegexEntry {
	private String regex;
	private IString2TokenProcessor processor;
	private Pattern pattern;
	private Matcher matcher;

	// --------------------------------------
	// CONSTRUCTION
	// --------------------------------------
	public RegexEntry(String regex, IString2TokenProcessor processor) {
		this.regex = regex;
		this.processor = processor;
		this.pattern = Pattern.compile(regex);
	}

	// --------------------------------------
	// PUBLIC methods
	// --------------------------------------
	public boolean matches(String text) {
		matcher = pattern.matcher(text);
		return matcher.matches();				
	}
	
	public String getMostRecentMatch() {
		if (matcher==null) {
			return null;
		}
		return matcher.group();
	}
	
	// --------------------------------------
	// PUBLIC Getters
	// --------------------------------------
	public String getRegex() {
		return regex;
	}
	public IString2TokenProcessor getProcessor() {
		return processor;
	}
	

	// --------------------------------------
	// Object methods
	// --------------------------------------
	@Override
	public int hashCode() {
		return regex.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof RegexEntry)) {
			return false;
		}
		return ((RegexEntry)other).regex.equals(this.regex);
	}
}
