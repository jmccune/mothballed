package org.tierlon.schema.support.fields;

import org.tierlon.evaluation.IAcceptanceResult;
import org.tierlon.schema.support.FieldData;
import org.tierlon.schema.support.FieldSchema;
import org.tierlon.system.context.IContext;

public interface IValidateSchemaFields<ModelTYPE> {

	/**
	 * @motivation schema -- ?? I believe this is in relation to how
	 * 	a field may only be valid in relation to other parts of the schema.
	 *  (Start vs End, # chosen being less than the # of listitems,etc.)
	 *  
	 * @param schema (non-null) the schema to which the field belongs
	 * @param data   (non-null) which field in particular are we validating
	 * @param model  (non-null) the model which is having its field validated.
	 * @param context (null-allowed) any other contextual information that
	 * 	can optionally be provided to give context to all the other information.
	 * @return
	 * 
	 */
	IAcceptanceResult evaluate(FieldSchema<ModelTYPE> schema, 
							FieldData field, ModelTYPE model, 
							IContext context);
	
}
