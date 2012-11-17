package org.tierlon.transform.process;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class RegexBasedStringProcessor implements IString2TokenProcessor {

	static Logger logger = Logger.getLogger(RegexBasedStringProcessor.class);
	private IString2TokenProcessor defaultProcessor =null;
	private List<RegexEntry> processorList = new ArrayList<RegexEntry>();
	
	// ==============================================================
	// CONFIGURATION
	// ==============================================================
	public void addRegexProcessor(String regex,IString2TokenProcessor proc) {
		processorList.add(new RegexEntry(regex,proc));
	}
	
	// ==============================================================
	// IMPLEMENTATION
	// ==============================================================
	@Override
	public Object processString2Token(String2TokenContext context) {
		
		String processtext = context.getCurrentState();
		for (RegexEntry entry : processorList) {
			
			//logger.info("ENTRY REGEX: "+entry.getRegex());
			//logger.info("MATCHES? "+processtext);
			if (entry.matches(processtext)) {
				//logger.info("MATCHED!");
				context.pushStringContext(entry.getMostRecentMatch());
				Object result =
						entry.getProcessor().processString2Token(context);
				context.popState();
				return result;
			}
		}
		
		if (defaultProcessor!=null) 
			return defaultProcessor.processString2Token(context);
		
		return null;
	}

	// ==============================================================
	// Getter & Setters
	// ==============================================================
	public IString2TokenProcessor getDefaultProcessor() {
		return defaultProcessor;
	}

	public void setDefaultProcessor(IString2TokenProcessor defaultProcessor) {
		this.defaultProcessor = defaultProcessor;
	}
	// ==============================================================
	// OTHER
	// ==============================================================
	/**
	 * Mostly a simple pairing of the regular expression and the 
	 * processor that goes with it. 
	 * @author justinanddiana
	 *
	 */
	class RegexEntry {
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
}
