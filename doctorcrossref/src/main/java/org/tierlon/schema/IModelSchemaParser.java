package org.tierlon.schema;

import java.io.InputStream;
import java.util.Map;

public interface IModelSchemaParser<ModelTYPE,ContextTYPE> {

	/**
	 * Parses a model schema definition input stream.
	 * @param input - the input specification schema for the models.
	 * @return (non-null) a map of model schemas as referenced by their names.
	 */
	public Map<String,ModelSchema<ModelTYPE,ContextTYPE>> parseModels(InputStream input);
	
	/** Convenience function for development.  Should just invoke the 
	 * above method converting the string to the input stream. 
	 * @param input - the input specification schema for the models.
	 * @return (non-null) a map of model schemas as referenced by their names.
	 */
	public Map<String,ModelSchema<ModelTYPE,ContextTYPE>> parseModels(String input);
}
