package org.tierlon.schema.support;

import org.tierlon.schema.support.FieldData;
import org.tierlon.system.evaluation.IValidate;
import org.tierlon.system.transform.ITransformData;

public class FieldSchema {
	private String propertyName;
	private String propertyType;
	private ITransformData<FieldData,Object> fieldTransform;
	private IValidate<FieldData> fieldValidator;
	private boolean required;
	
	// ==============================================================
	// FieldData
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
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public ITransformData<FieldData, Object> getFieldTransform() {
		return fieldTransform;
	}
	public void setFieldTransform(ITransformData<FieldData, Object> fieldTransform) {
		this.fieldTransform = fieldTransform;
	}
	public IValidate<FieldData> getFieldValidator() {
		return fieldValidator;
	}
	public void setFieldValidator(IValidate<FieldData> fieldValidator) {
		this.fieldValidator = fieldValidator;
	}

}
