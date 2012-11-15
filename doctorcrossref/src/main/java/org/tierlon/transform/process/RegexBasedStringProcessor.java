package org.tierlon.transform.process;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexBasedStringProcessor implements IString2TokenProcessor {

	private IString2TokenProcessor defaultProcessor =null;
	
	private List<RegexEntry> processorList;
	
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
			if (entry.matches(processtext)) {
				
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

	
	class RegexEntry {
		private String regex;
		private IString2TokenProcessor processor;
		private Pattern pattern;
		private Matcher matcher;
		
		public RegexEntry(String regex, IString2TokenProcessor processor) {
			this.regex = regex;
			this.processor = processor;
			this.pattern = Pattern.compile(regex);
		}
		
		public String getRegex() {
			return regex;
		}
		public IString2TokenProcessor getProcessor() {
			return processor;
		}
		
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
