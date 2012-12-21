package org.doctorcrossref.model.domain.schema;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.doctorcrossref.model.domain.EntityObject;
import org.doctorcrossref.model.domain.schema.fieldgen.StringFieldGenerator;
import org.tierlon.parsing.lexer.SimpleLineLexer;
import org.tierlon.schema.IModelSchemaParser;
import org.tierlon.schema.ModelSchema;
import org.tierlon.schema.support.fields.FieldSchemaFactory;
import org.tierlon.schema.support.parser.SimpleModelSchemaParser;

public class DomainModelSchemaParser implements IModelSchemaParser<EntityObject,Object> {

	// ==============================================================
	// PUBLIC Methods
	// ==============================================================
	@Override
	public Map<String, ModelSchema<EntityObject,Object>> parseModels(InputStream input) {
		
		SimpleModelSchemaParser<EntityObject,Object> simpleParser = 
				new SimpleModelSchemaParser<EntityObject,Object>();
		
		configureFactory(simpleParser);
		
		SimpleLineLexer lexer;
		try {
			lexer = new SimpleLineLexer(input);
			simpleParser.parseData(lexer);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	

	@Override
	public Map<String, ModelSchema<EntityObject,Object>> parseModels(String input) {
		return parseModels(new ByteArrayInputStream(input.getBytes()));
	}
	
	// ==============================================================
	// PRIVATE Methods
	// ==============================================================	
	@SuppressWarnings("unchecked")
	private void configureFactory(
			SimpleModelSchemaParser<EntityObject, Object> simpleParser) {
		
		FieldSchemaFactory<EntityObject, Object> factory = 
				simpleParser.getFieldSchemaFactory();
		
		factory.addFieldGenerators(new StringFieldGenerator());
		
	}
}
