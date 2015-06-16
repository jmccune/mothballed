package org.doctorcrossref.model.domain.schema;


/**
 * The Goal is to define the various object types and the **REQUIRED**
 * attributes/properties and normal **OPTIONAL** attributes and
 * information therein.
 * 
 * While any object may have other additional properties, an object
 * is not considered a valid representation of a given type, unless it
 * meets a minimal set of requirements <i> for a particular context </i>.
 * 
 * Contexts are usually high-level abstractions of objects -- 
 * 	what is necessary to delete an object, to search for an object, to 
 *  initially create an object, etc. 
 * 
 * 
 * @author justinanddiana
 *
 */
public class TypePropertyMap {

}


/*	(For each type, you have the property, its type, and optionally any 
 * validation constraints (either in a fixed set, or as a programmatic
 * ojbect that will validate the information.)
 *
	Entity:
		Label:String:(length>0 & length<128)
		Description:String
		CreatedDate:Date
		Type:String:org.tierlon.etc.TypeValidator
		[Creator]:Entity:(Type=Entity>Person or Type=Entity>Organization)
		[EndDate]:Date
	
	Entity>Person:
		firstName:String
		lastName:String
		[middleName]:String
		[suffix]:String
	
	
*/