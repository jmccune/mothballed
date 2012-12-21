package org.doctorcrossref.model.domain.schema;

import java.io.InputStream;
import java.util.Map;

import org.tierlon.schema.IModelSchemaParser;
import org.tierlon.schema.ModelSchema;

public class DomainModelSchemaParser<ModelTYPE,ContextTYPE> implements IModelSchemaParser<ModelTYPE,ContextTYPE> {

	@Override
	public Map<String, ModelSchema<ModelTYPE,ContextTYPE>> parseModels(InputStream input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ModelSchema<ModelTYPE,ContextTYPE>> parseModels(String input) {
		// TODO Auto-generated method stub
		return null;
	}

}
