package org.tierlon.transform.process.String2Token;

import java.util.ArrayList;
import java.util.List;

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
}
