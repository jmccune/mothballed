package org.tierlon.schema.support.fields;

import org.tierlon.schema.support.FieldSchema;

public interface IFieldGenerator {

	boolean canGenerate(String fieldName,String fieldType, String remainder);
	
	FieldSchema generate(String fieldName,String fieldType, String remainder);
}
