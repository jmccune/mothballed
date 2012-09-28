package org.tierlon.system.helper;

public class GeneralHelper {

	public static void assertNonNull(Object... args) {
		int nth=0;
		for (Object arg : args) {			
			if (arg==null)
				throw new NullPointerException("The "+nth+" argument was null!");
			nth=nth+1;
		}
	}
}
