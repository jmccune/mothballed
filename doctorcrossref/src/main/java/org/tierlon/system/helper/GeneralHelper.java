package org.tierlon.system.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralHelper {

	static SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
	static SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yy");
	
	public static void assertNonNull(Object... args) {
		int nth=0;
		for (Object arg : args) {			
			if (arg==null)
				throw new NullPointerException("The "+nth+" argument was null!");
			nth=nth+1;
		}
	}
	
	public static void assertObjectsOfType(Class<? extends Object> type,
			Object...objects) {
		for (Object obj : objects) {
			if (obj==null)
				throw new NullPointerException();
			if (!type.isInstance(obj)) 
				throw new IllegalArgumentException("Expected type: "+type+
						" but argument: "+obj+" is of type: "+obj.getClass());
		}
	}
	
	public static Date parseDate(String dateString) {
		try {
			if (dateString.contains(".")) {
				return dateFormat1.parse(dateString);
			}
			else if (dateString.contains("/")) {
				return dateFormat2.parse(dateString);
			}
		}
		catch (ParseException x) {
			System.err.println(">>Error parsing: "+dateString);
			x.printStackTrace();
		}
	
		throw new IllegalArgumentException("Argument: "+dateString+
				" isn't in one of the expected date formats!");
	}
	
}
