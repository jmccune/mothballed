package org.tierlon.schema.support;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tierlon.schema.IModelSchemaParser;
import org.tierlon.schema.ModelSchema;
import org.tierlon.schema.support.fields.FieldSchemaFactory;

public class SimpleModelSchemaParser implements IModelSchemaParser {

	@Override
	public Map<String, ModelSchema> parseModels(InputStream input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ModelSchema> parseModels(String input) {
		return parseModels(new ByteArrayInputStream(input.getBytes()));
	}

}


//******************************************************************
//SimpleLineLexer
//******************************************************************
class ModelParser {
	private static final String LABEL_REGEX = "[A-Za-z][A-Za-z0-9_-]*";
	private static final String NSLABEL_REGEX = "("+LABEL_REGEX+"\\:)?"+LABEL_REGEX;
	private static final Pattern fieldTypeEnd = Pattern.compile("[@#]"); 
	private static final FieldSchemaFactory fieldFactory = 
			new FieldSchemaFactory();
	
	public static final String ENTITY_REGEX = 
			"\\s{0,2}("+NSLABEL_REGEX+")"+
			"(\\s+\\|\\s+"+NSLABEL_REGEX+")*"+
			"(\\s*\\>\\s*"+NSLABEL_REGEX+")?\\s*:\\s*";
	
	private ModelSchema buildModel;
	private List<FieldSchema> fieldSchemaList;
	// ==============================================================
	// PUBLIC Methods
	// ==============================================================
	public ModelSchema parseData(SimpleLineLexer lexer) throws IOException {
		String data = lexer.getLineData();
		if (!data.matches(ENTITY_REGEX)) {
			throw new IllegalStateException("Error parsing data>> "+
					"Expected ModelHeader, received: "+data);
		}
		extractModelInformation(data);
		
		System.out.println("PROCESSED: "+data);
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
	
		FieldSchema schema = fieldFactory.createField(fieldName,fieldType,remainder);
		
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
		
		buildModel = new ModelSchema(nameSpace,entityName);

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

		
		fieldSchemaList = new ArrayList<FieldSchema>();
		
	}
	
	
	private void validateLabel(String label) {
		if (!label.matches(LABEL_REGEX)) {
			throw new IllegalStateException("Label: "+
					label+" is not a valid label");
		}
	}
}






// ******************************************************************
//  SimpleLineLexer
// ******************************************************************
class SimpleLineLexer {
	
	private static String COMMENT_REGEX = "\\s*#.*";
	private BufferedReader inputReader;
	private boolean hasNextDataLine;
	private String dataLine="";
	
	// ==============================================================
	// Constructor
	// ==============================================================
	public SimpleLineLexer(InputStream input) throws IOException {
		inputReader
    		= new BufferedReader(
    				new InputStreamReader(input));
		//Assume true:
		hasNextDataLine =true;
		scanForNextDataLine(); 
	}
	
	public SimpleLineLexer(String string) throws IOException {
		this(new ByteArrayInputStream(string.getBytes()));
	}

	// ==============================================================
	// Public Methods
	// ==============================================================
	public boolean hasNextLine() {
		return hasNextDataLine;
	}
	
	public String getLineData() throws IOException {
		String result = dataLine;
		scanForNextDataLine();
		return result;
	}
	
	// ==============================================================
	// Private Methods
	// ==============================================================
	private void scanForNextDataLine() throws IOException {
		if (!hasNextDataLine)
			return;
		
		boolean done=false;
		String lineRead="";
		while (!done) {
			lineRead = inputReader.readLine();
			if (lineRead==null) {
				hasNextDataLine=false;
				dataLine="";
				return;
			}
			
			//Skip comment lines and blanklines.
			if (lineRead.matches(COMMENT_REGEX))
				//Keep going...
				continue;
			if (lineRead.trim().isEmpty())
				continue;
			done=true;
		}
		dataLine = lineRead;
	}
}