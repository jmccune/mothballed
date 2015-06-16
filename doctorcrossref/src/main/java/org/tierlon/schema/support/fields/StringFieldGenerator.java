package org.tierlon.schema.support.fields;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tierlon.schema.support.FieldSchema;

public class StringFieldGenerator<ModelTYPE> 
	extends AbstractFieldGenerator<ModelTYPE> {

	private static Logger log =
			LoggerFactory.getLogger(StringFieldGenerator.class);

	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public StringFieldGenerator() {
		super("string");
	}
	
	// ==============================================================
	// *PROTECTED* INTERFACE IMPLEMENTATION
	// ==============================================================
	@Override
	protected FieldSchema<ModelTYPE> generateImplementation(
			String fieldName,
			String fieldType, String validationSpec, String defaultValue) {
	
		log.info("FIELDNAME: "+fieldName);
		log.info(" VALIDATION>> "+validationSpec);
		log.info(" DEFAULT VALUE>>> "+defaultValue);
		FieldSchema<ModelTYPE> schema= 
				new FieldSchema<ModelTYPE>(fieldName,fieldType);
		
		IValidateSchemaFields<ModelTYPE> fieldValidator = null;
		schema.setFieldValidator(fieldValidator);
		return schema;
	}

	@Override
	protected boolean canProcessOtherSpecParts(String validationSpec,
			String defaultValue) {
		return true;
	}

}
