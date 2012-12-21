package org.tierlon.schema.support.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tierlon.parsing.lexer.SimpleLineLexer;
import org.tierlon.schema.IModelSchemaParser;
import org.tierlon.schema.ModelSchema;
import org.tierlon.schema.support.FieldSchema;
import org.tierlon.schema.support.fields.FieldSchemaFactory;

public class SimpleModelSchemaParser<ModelTYPE,ContextTYPE>
	implements IModelSchemaParser<ModelTYPE,ContextTYPE> {

	@Override
	public Map<String, ModelSchema<ModelTYPE,ContextTYPE>> parseModels(InputStream input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ModelSchema<ModelTYPE,ContextTYPE>> parseModels(String input) {
		return parseModels(new ByteArrayInputStream(input.getBytes()));
	}

}


//******************************************************************
//ModelParser
//******************************************************************
class ModelParser<ModelTYPE,ContextTYPE> {
	private static final String LABEL_REGEX = "[A-Za-z][A-Za-z0-9_-]*";
	private static final String NSLABEL_REGEX = "("+LABEL_REGEX+"\\:)?"+LABEL_REGEX;
	private static final Pattern fieldTypeEnd = Pattern.compile("[@#]"); 
	private final FieldSchemaFactory<ModelTYPE,ContextTYPE> fieldFactory = 
			new FieldSchemaFactory<ModelTYPE,ContextTYPE>();
	
	public static final String ENTITY_REGEX = 
			"\\s{0,2}("+NSLABEL_REGEX+")"+
			"(\\s+\\|\\s+"+NSLABEL_REGEX+")*"+
			"(\\s*\\>\\s*"+NSLABEL_REGEX+")?\\s*:\\s*";
	
	private ModelSchema<ModelTYPE,ContextTYPE> buildModel;
	private List<FieldSchema<ModelTYPE,ContextTYPE>> fieldSchemaList;
	// ==============================================================
	// PUBLIC Methods
	// ==============================================================
	public ModelSchema<ModelTYPE,ContextTYPE> parseData(SimpleLineLexer lexer) throws IOException {
		String data = lexer.getLineData();
		if (!data.matches(ENTITY_REGEX)) {
			throw new IllegalStateException("Error parsing data>> "+
					"Expected ModelHeader, received: "+data);
		}
		
		//Create the basic model here...
		extractModelInformation(data);
		

		System.out.println("PROCESSED: "+data);
		//For each field that is part of the current model,
		// add the field specification...
		while (lexer.hasNextLine()) {
			data = lexer.getLineData();
			System.out.println("NEXT LINE: "+data);
			
			if (data.matches(ENTITY_REGEX)) {
				//TODO:  END ENTITY 1, START ENTITY 2...
				//       OR NEED TO CHANGE LEXER TO ADVANCE ONLY WHEN DIRECTED
				break;
			}
			addFieldSpec(data);
		}
		finalizeEntityInformation();
		return buildModel;
		
	}	
	
	// ==============================================================
	// PRIVATE Methods
	// ==============================================================
	
	// Process the field spec:
	//    <name>:<type>[@(<validation_spec>)][#<default_value>]
	private void addFieldSpec(String data) {
	
		int indx = data.indexOf(':');
		if (indx==-1) {
			throw new IllegalStateException("Must have field type for: "+data);
		}
		
		String fieldName = data.substring(0,indx).trim();
		String remainder = data.substring(indx+1).trim();
		String fieldType = "";
		Matcher m = fieldTypeEnd.matcher(remainder);
		if (m.find()) {
			fieldType = remainder.substring(0,m.start());
			remainder = remainder.substring(m.start());
		} else {
			fieldType = remainder;
			remainder = "";
		}
		
		System.out.println("DATA>>"+data);
		System.out.println("fieldType: "+fieldType);
		System.out.println("remainder: "+remainder);
		
		if (fieldType.isEmpty()) {
			throw new IllegalStateException("Must have type specification!");
		}
	
		FieldSchema<ModelTYPE,ContextTYPE> schema = 
				fieldFactory.createField(fieldName,fieldType,remainder);
		
		System.out.println("Parsed field: "+fieldName+" fieldType: "+fieldType);
		//this.fieldSchemaList.add(new FieldSchema(fieldName,fieldType));
		this.fieldSchemaList.add(schema);
	}
	
	
	private void finalizeEntityInformation() {
		
		// Right now, finalization is just validation...
		
		if (buildModel==null) {
			throw new IllegalStateException("No model?  That's odd...");
		}
		
		buildModel.setModelFields(this.fieldSchemaList);
		if (buildModel.getModelFields().isEmpty()) {
			throw new IllegalStateException("Cannot have an empty model.");
		}
	}
	
	
	private void extractModelInformation(String data) {
		
//		<ENTITY_HEADER>: <ENTITY_LINEAGE_SPEC> [<MARKER> : <CUSTOM_VALIDATOR>] <MARKER> \n
//		<ENTITY_LINEAGE_SPEC>: [<PARENT_ENTITIES> <PARENT_MARKER> ] <ENTITY_NAME>
//		
//		<PARENT_ENTITIES>: <ENTITY_NAME>   | 
//		                   [<PARENT_ENTITIES> <OR_MARKER>] <ENTITY_NAME> 
	
//		Examples:
//			AlphaNS:AlphaEntity | BetaNS:BetaEntity > AlphaNS:ChildEntity:
		
		String[] parents2child = data.split(">");
		String parentSpec ="";
		String nameSpec = "";
			
		if (parents2child.length==1) {
			nameSpec = parents2child[0];
		} else if (parents2child.length==2) {
			parentSpec = parents2child[0];
			nameSpec = parents2child[1];
		} else {
			throw new IllegalStateException("Should never get here!>> "+
					parents2child.length+ " is not expected 1 or 2.");
		}
		
		// Break name into namespace (as appropriate).
		nameSpec = nameSpec.trim();
		assert(nameSpec.endsWith(":"));
		nameSpec = nameSpec.substring(0,nameSpec.length()-1).trim();
		
		String nameParts[] = nameSpec.split(":");
		
		String nameSpace ="";
		String entityName = "";
		if (nameParts.length==1) {
			entityName = nameParts[0];
		} else if (nameParts.length==2) {
			nameSpace = nameParts[0].trim();
			entityName = nameParts[1].trim();
		}
				
		validateLabel(entityName);
		if (!nameSpace.isEmpty()) validateLabel(nameSpace);
		
		buildModel = new ModelSchema<ModelTYPE,ContextTYPE>(nameSpace,entityName);

		// Parent Spec
		if (!parentSpec.isEmpty()) {
			String[] parents = parentSpec.split("\\|");
			List<String> parentNames = new ArrayList<String>(parents.length);
			for (String parent: parents) {
				String parentName =parent.trim();
				validateLabel(parentName);
				parentNames.add(parentName);
			}
			buildModel.setParentNames(parentNames);
		}

		
		fieldSchemaList = new ArrayList<FieldSchema<ModelTYPE,ContextTYPE>>();
		
	}
	
	
	private void validateLabel(String label) {
		if (!label.matches(LABEL_REGEX)) {
			throw new IllegalStateException("Label: "+
					label+" is not a valid label");
		}
	}
}




