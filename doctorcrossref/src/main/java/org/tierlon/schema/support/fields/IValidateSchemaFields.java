package org.tierlon.schema.support.fields;

import org.tierlon.evaluation.IAcceptanceResult;

public interface IValidateSchemaFields<ModelTYPE,ContextTYPE> {

	IAcceptanceResult evaluate(FieldSchema schema, FieldData data, ModelTYPE model, ContextTYPE context);
	
}
