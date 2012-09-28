package org.doctorcrossref.model.system.standard;

import org.tierlon.system.helper.GeneralHelper;

public class PersonName {

	static public String normalizeName(String lastName, String firstName, 
								String middle, String suffix)
	{
		GeneralHelper.assertNonNull(lastName,firstName);
		
		StringBuilder sb = new StringBuilder();
		sb.append(lastName.toLowerCase()).append(">");
		sb.append(firstName.toLowerCase());
		
		if (middle!=null)
			sb.append('>').append(middle.toLowerCase());
		if (suffix!=null)
			sb.append('>').append(suffix.toLowerCase());
		
		return sb.toString();
	}
}
