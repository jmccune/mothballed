package org.tierlon.schema;

import java.util.List;
import java.util.Map;

import org.tierlon.system.evaluation.IAcceptanceResult;
import org.tierlon.system.evaluation.IValidate;
import org.tierlon.system.transform.ITransformData;
import org.tierlon.schema.support.FieldSchema;

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
public class ModelSchema implements IValidate<Map<String,String>>{

	private List<FieldSchema> modelFields;
	private IValidate<ModelSchema> modelValidator;
	// ==============================================================
	// Methods
	// ==============================================================
	@Override
	public IAcceptanceResult evaluate(Map<String, String> object,
			Object... contextualInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}

