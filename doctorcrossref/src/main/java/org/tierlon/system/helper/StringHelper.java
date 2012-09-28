package org.tierlon.system.helper;

public class StringHelper {

	static public String objectToString(String objectName,String... kvArgs) {
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
}
