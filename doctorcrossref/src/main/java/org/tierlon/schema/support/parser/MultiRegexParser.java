package org.tierlon.schema.support.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tierlon.transform.process.String2Token.IString2TokenProcessor;
import org.tierlon.transform.process.String2Token.RegexBasedStringProcessor;
import org.tierlon.transform.process.String2Token.RegexEntry;
import org.tierlon.transform.process.String2Token.String2TokenContext;

public class MultiRegexParser<ExpressionContext> {

	private Map<String,List<RegexEntry>> ruleProcessingMap;	
	private RegexBasedStringProcessor    regexProcessor;
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public MultiRegexParser(IString2TokenProcessor defaultProc) {
		regexProcessor = new RegexBasedStringProcessor();
		if (defaultProc!=null) {
			regexProcessor.setDefaultProcessor(defaultProc);
		}			
		ruleProcessingMap = new HashMap<String,List<RegexEntry>>();
	}
	// ==============================================================
	// CONFIGURATION
	// ==============================================================
	public void addRegexProcessorFamily(String familyName,
			Map<String,IString2TokenProcessor> familyRegexProcessors) {
		
		for (Map.Entry<String,IString2TokenProcessor> proc :
			familyRegexProcessors.entrySet()) {
			addRegexProcessor(familyName,proc.getKey(),proc.getValue());
		}
		
	}
	
	public void addRegexProcessor(String familyName,String regex, 
			IString2TokenProcessor proc4regex) {
		
		List<RegexEntry> list = ruleProcessingMap.get(familyName);
		if (list==null) {
			list = new ArrayList<RegexEntry>();
			ruleProcessingMap.put(familyName, list);
		}
		//TODO:  Check for duplicate regexes, etc.
		list.add(new RegexEntry(regex,proc4regex));
		
		regexProcessor.addRegexProcessor(regex, proc4regex);		
	}
	
	//TODO: The Removal operations
	
	// ==============================================================
	// PUBLIC Methods
	// ==============================================================
	public Object parse(String string) {
		String2TokenContext s2Tcontext = new String2TokenContext(string);
		s2Tcontext.setRootProcessor(regexProcessor);

		return regexProcessor.processString2Token(s2Tcontext);		
	}
}
