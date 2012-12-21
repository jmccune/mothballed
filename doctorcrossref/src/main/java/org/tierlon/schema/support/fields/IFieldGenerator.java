package org.tierlon.schema.support.fields;

import org.tierlon.schema.support.FieldSchema;

public interface IFieldGenerator<ModelTYPE,ContextTYPE> {

	boolean canGenerate(String fieldName,String fieldType, String remainder);
	
	FieldSchema<ModelTYPE,ContextTYPE> generate(String fieldName,String fieldType, String remainder);
}
