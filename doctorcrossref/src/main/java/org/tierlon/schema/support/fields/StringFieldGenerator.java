package org.tierlon.schema.support.fields;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tierlon.schema.support.FieldSchema;

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
		return new FieldSchema(fieldName,fieldType);
	}

	@Override
	protected boolean canProcessOtherSpecParts(String validationSpec,
			String defaultValue) {
		return true;
	}

}
