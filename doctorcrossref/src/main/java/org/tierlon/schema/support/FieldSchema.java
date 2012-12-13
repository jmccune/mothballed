package org.tierlon.schema.support;

import org.tierlon.evaluation.IEvaluate;
import org.tierlon.schema.support.FieldData;
import org.tierlon.transform.ITransformData;

public class FieldSchema {
	public enum PropertyType {string,date,number,num_float,num_int,
		object,ref,list};
	private String propertyName;
	
	private PropertyType propertyType;
	
	private ITransformData<FieldData,Object> fieldTransform;
	private IEvaluate<FieldData> fieldValidator;
	private boolean required;

	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public FieldSchema(String propertyName, String propertyType) {
		this.propertyName = propertyName;
		this.propertyType = PropertyType.valueOf(propertyType);
	}
	// ==============================================================
	// FieldSchema
	// ==============================================================
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyType() {
		return propertyType.toString();
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = PropertyType.valueOf(propertyType);
	}
	public ITransformData<FieldData, Object> getFieldTransform() {
		return fieldTransform;
	}
	public void setFieldTransform(ITransformData<FieldData, Object> fieldTransform) {
		this.fieldTransform = fieldTransform;
	}
	
	//TODO:  Change return type IValidateSchemaFields ????????!!!*******
	public IEvaluate<FieldData> getFieldValidator() {
		return fieldValidator;
	}
	public void setFieldValidator(IEvaluate<FieldData> fieldValidator) {
		this.fieldValidator = fieldValidator;
	}

}
