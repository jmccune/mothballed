package org.tierlon.system.helper;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {

	static public String objectToString(String objectName,Object... kvArgs) {
		if (kvArgs == null) 
			return objectName;
		
		StringBuilder sb = new StringBuilder();
		sb.append(objectName).append('[');
		for (int i=0; i<kvArgs.length; i+=2) {
			if (i>0) {
				sb.append("  ");
			}
			sb.append(kvArgs[i]).append(":").append(kvArgs[i+1]);
		}
		sb.append(']');
		return sb.toString();
	}
	
	static public String reverse(String string) {
		return new StringBuffer(string).reverse().toString();
	}
	
	static public List<String> extractInnerMost(char startChar,char endChar,
											String expression) {
		
		List<String> results = new ArrayList<String>(3);
		int endIndex = expression.indexOf(')');
		if (endIndex==-1) {
			results.add(expression);
			results.add("");
			results.add("");
			return results;
		}
		
		int startIndex = expression.lastIndexOf('(',endIndex);
		if (startIndex==-1) {
			String reason = "Malformed subexpression missing start '"+
					startChar+"': "+
					expression;
			throw new IllegalArgumentException(reason);
		}
		
		results.add(safeSubstring(expression,0,startIndex));
		results.add(safeSubstring(expression,startIndex+1,endIndex));
		results.add(safeSubstring(expression,endIndex+1));
		return results;
	}
	
	static public String safeSubstring(String val,Integer... indices)  {
		if (val==null || val.isEmpty()) return "";
		if (indices==null || indices.length==0) {return val;}
		if (indices.length>2) {
			throw new IllegalArgumentException("Too many indices!");
		}
		int startIndex = indices[0];
		if (startIndex<0) {
			startIndex = val.length() + startIndex;
			if (startIndex<0) startIndex=0;
		}
		
		// Deal with start only...
		if (indices.length==1) {
			return val.substring(startIndex);
		}
		//else [E.g. if (indices.length==2) { ...
		int endIndex = indices[1];
		if (endIndex<0)  {
			endIndex = val.length() + endIndex;
			if (endIndex<0) endIndex=0;
		}
		else if (endIndex>val.length())  {
			endIndex = val.length();
		}
		// REVERSE THE STRING (if END is less than START)
		if (endIndex<startIndex) {
			String subsequence= val.substring(endIndex,startIndex);
			return reverse(subsequence);
		}
		
		return val.substring(startIndex,endIndex);
	}
}
