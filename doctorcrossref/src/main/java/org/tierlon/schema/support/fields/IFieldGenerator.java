package org.tierlon.schema.support.fields;

import org.tierlon.schema.support.FieldSchema;

public interface IFieldGenerator<ModelTYPE> {

	boolean canGenerate(String fieldName,String fieldType, String remainder);
	
	FieldSchema<ModelTYPE> generate(String fieldName,String fieldType, String remainder);
}
