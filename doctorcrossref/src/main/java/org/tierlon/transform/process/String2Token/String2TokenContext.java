package org.tierlon.transform.process.String2Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * Provides a standardized context shared across multiple
 * transform steps that are transforming data (e.g. a string) into
 * something more meaningful.  (E.g. a string that represents a 
 * mathematical expression into the functionality that will 
 * represent that expression).
 * @author justinanddiana
 *
 */
public class String2TokenContext {
	
	private String 		 originalString;
	private List<String> stringXformStack;
	private int 		 nextTokenNumber=0;
	private String 		 tokenBase = "__TokenZZZ__";
	private IString2TokenProcessor rootProcessor;
	private Map<String,Object> tokenMap;
	
	
	static public String getTokenRegex() {
		return "__Token[0-9]+__";
	}
	public IString2TokenProcessor getRootProcessor() {
		return rootProcessor;
	}

	public void setRootProcessor(IString2TokenProcessor rootProcessor) {
		this.rootProcessor = rootProcessor;
	}

	
	public String2TokenContext(String startString) {
		this.originalString = startString;
		this.stringXformStack = new ArrayList<String>();
		stringXformStack.add(startString);
		this.tokenMap = new HashMap<String,Object>();
	}
	
	public String pushStringContext(String nextState) {
		String currentState = getCurrentState();
		stringXformStack.add(nextState);
		return currentState;
	}
	
	public String getOriginalString() {
		return originalString;
	}
	
	public String popState() {
		if (stringXformStack.size()==1) {
			return getCurrentState();
		}
		return stringXformStack.remove(stringXformStack.size()-1);
	}
	
	public String getCurrentState() {
		return stringXformStack.get(stringXformStack.size()-1);
	}
	public String generateNextTokenNameString() {
		int tokenNumber = nextTokenNumber++;
		return tokenBase.replace("ZZZ",""+tokenNumber);		
	}
	
	public Object setToken(String name, Object value) {
		return tokenMap.put(name, value);
	}
	
	public Object getToken(String name) {
		
		return tokenMap.get(name);
	}
	
	public Set<String> getAllTokenNames() {
		return tokenMap.keySet();
	}
}
