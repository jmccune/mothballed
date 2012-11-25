package org.tierlon.schema.support.parser;

import java.util.HashMap;
import java.util.Map;

import org.tierlon.transform.process.String2Token.IString2TokenProcessor;
import org.tierlon.transform.process.String2Token.String2TokenContext;

public class RegexProcessorFactory {

	Map<String,IString2TokenProcessor> generateTokenProcessor() {
		Map<String,IString2TokenProcessor> contextMap = 
				new HashMap<String,IString2TokenProcessor>();
		
		contextMap.put(String2TokenContext.getTokenRegex(),
				new TokenProcessor2());
		
		return contextMap;
	}
	
	
	private final String CHAR_REGEX="[\\sA-Za-z_-0-9!@#$%^*()~\".,?/\\\\{}\\[\\]']*?";
	private final String LT_REGEX=".*?<.*?";
	private final String GT_REGEX=".*?>.*?";	
	
	public Map<String,IString2TokenProcessor> generateComparisonParsers() {
		Map<String,IString2TokenProcessor> contextMap = 
				new HashMap<String,IString2TokenProcessor>();
		
		contextMap.put(String2TokenContext.getTokenRegex(),
				new TokenProcessor2());
		
		return contextMap;
	}
	
}

class TokenProcessor2 implements IString2TokenProcessor {
	@Override
	public Object processString2Token(String2TokenContext context) {
		String tokenName = context.getCurrentState().trim();
		return context.getToken(tokenName);
	}
}

class ComparisonProcessor implements IString2TokenProcessor {

	String[] comparisons={"<=",">=","==","!=",">","<"};
	
	@Override
	public Object processString2Token(String2TokenContext context) {
		String token = context.getCurrentState().trim();
			
		int index=-1;
		for (String comparison : comparisons) {
			index= token.indexOf(comparison); 
			if (index>-1) {
				break;
			}
		}
		
		//Break apart on the found comparison token
		return context.getToken(tokenName);
	}
	
}
