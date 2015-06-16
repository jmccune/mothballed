package org.tierlon.schema.support.fields;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tierlon.schema.support.FieldSchema;

public class FieldSchemaFactory<ModelTYPE> {

	static private Logger log = 
			LoggerFactory.getLogger(FieldSchemaFactory.class);
	private List<IFieldGenerator<ModelTYPE>> fieldGenerators;
	
	// ==============================================================
	// CONSTRUCTION 
	// ==============================================================
	public FieldSchemaFactory() {
		fieldGenerators= new ArrayList<IFieldGenerator<ModelTYPE>>();
		//fieldGenerators.add(new StringFieldGenerator<ModelTYPE,ContextTYPE>());
	}
	// ==============================================================
	// Configuration
	// ==============================================================
	
	public void addFieldGenerators(IFieldGenerator<ModelTYPE>... fgs) {
		for (IFieldGenerator<ModelTYPE> fg : fgs)
			fieldGenerators.add(fg);
	}
	
	public boolean removeFieldGenerator(IFieldGenerator<ModelTYPE> fg) {
		return fieldGenerators.remove(fg);
	}
	
	// ==============================================================
	// PUBLIC Methods
	// ==============================================================
	
	public FieldSchema<ModelTYPE> createField(String fieldName, String fieldType,
			String remainder) {
	
		validateFieldName(fieldName);
		
		for (IFieldGenerator<ModelTYPE> generator: fieldGenerators) {			
			if (!generator.canGenerate(fieldName, fieldType, remainder))
				continue;
			
			try {
				log.info("Generating for: "+fieldName+":"+fieldType);
				return generator.generate(fieldName, fieldType, remainder);
			}
			catch (Exception x) {
				log.info("Problem encountered with fieldname: "+fieldName,x);
			}
		}
			
		return new FieldSchema<ModelTYPE>(fieldName,fieldType);
		
		//return fieldGenerator.generateFieldSchema(fieldName,fieldType);
		//return new FieldSchema(fieldName,fieldType);
	}
	
	
	// ==============================================================
	// PRIVATE Methods
	// ==============================================================
	private void validateFieldName(String name) {}
	
	
}
