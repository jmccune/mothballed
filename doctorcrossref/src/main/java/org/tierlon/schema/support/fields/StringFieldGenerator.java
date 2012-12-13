package org.tierlon.schema.support.fields;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tierlon.evaluation.IEvaluate;
import org.tierlon.schema.support.FieldSchema;
import org.tierlon.schema.support.FieldData;

public class StringFieldGenerator extends AbstractFieldGenerator {

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
	protected FieldSchema generateImplementation(String fieldName,
			String fieldType, String validationSpec, String defaultValue) {
	
		log.info("FIELDNAME: "+fieldName);
		log.info(" VALIDATION>> "+validationSpec);
		log.info(" DEFAULT VALUE>>> "+defaultValue);
		FieldSchema schema= new FieldSchema(fieldName,fieldType);
		
		IEvaluate<FieldData> fieldValidator = null;
		schema.setFieldValidator(fieldValidator);
		return schema;
	}

	@Override
	protected boolean canProcessOtherSpecParts(String validationSpec,
			String defaultValue) {
		return true;
	}

}
