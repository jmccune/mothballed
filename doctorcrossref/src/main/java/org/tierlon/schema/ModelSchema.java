package org.tierlon.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.tierlon.system.evaluation.IAcceptanceResult;
import org.tierlon.system.evaluation.IEvaluate;
import org.tierlon.schema.support.FieldSchema;

/*	(For each type, you have the property, its type, and optionally any 
 * validation constraints (either in a fixed set, or as a programmatic
 * ojbect that will validate the information.)
 *
	Entity:
		Label:string@(length>0 & length<128)
		Description:string#"No Description Given"
		CreatedDate:date
		Type:String@(org.tierlon.etc.TypeValidator)#"NONE"
		[Creator]:Entity@(Type=Entity>Person || Type=Entity>Organization)
		[EndDate]:Date@($value>=@CreatedDate)
	
	Entity>Person:
		firstName:String
		lastName:String
		[middleName]:String
		[suffix]:String
	
	
	
*/
public class ModelSchema implements IEvaluate<Map<String,String>>{

	/** Which models are the immediate parents of this one? */
	private List<String>	  parentNames;
	
	/** What is the name of this model? */
	private String            modelName;
	private String			  namespace;
	
	/** What fields does this model have? */
	private List<FieldSchema> modelFields;
	
	/** What validator (if any) validates this model as a whole?
	 *  Note: Fields may have their own validators. */
	private IEvaluate<ModelSchema> modelValidator;
	
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public ModelSchema(String namespace, String modelName) {
		this.namespace = namespace;
		this.modelName = modelName;
		modelFields = Collections.emptyList();
	}
	
	
	
	// ==============================================================
	// INTERFACE IMPLEMENTATION 
	// ==============================================================
	@Override
	public IAcceptanceResult evaluate(Map<String, String> object,
			Object... contextualInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	// ==============================================================
	// Getters & Setters
	// ==============================================================
	public List<String> getParentNames() {
		return new ArrayList<String>(parentNames);
	}


	public void setParentNames(List<String> parentNames) {
		this.parentNames = parentNames;
	}



	public String getModelName() {
		return modelName;
	}



	public void setModelName(String modelName) {
		this.modelName = modelName;
	}



	public String getNamespace() {
		return namespace;
	}



	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}



	public List<FieldSchema> getModelFields() {
		return new ArrayList<FieldSchema>(modelFields);
	}



	public void setModelFields(List<FieldSchema> modelFields) {
		this.modelFields = modelFields;
	}



	public IEvaluate<ModelSchema> getModelValidator() {
		return modelValidator;
	}



	public void setModelValidator(IEvaluate<ModelSchema> modelValidator) {
		this.modelValidator = modelValidator;
	}
	
	
}

