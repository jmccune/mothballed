package org.tierlon.schema.support.fields;

import org.tierlon.schema.support.FieldSchema;


abstract public class  AbstractFieldGenerator<ModelTYPE>
	implements IFieldGenerator<ModelTYPE> {

	private String fieldType;

	
	// ==============================================================
	// CONSTRUCTION 
	// ==============================================================	
	/**
	 * 
	 * @param fieldType - the string "class" or "type" name of the 
	 * 	field.  E.g. "date", "string", "number", etc.
	 */
	public AbstractFieldGenerator(String fieldType) {
		this.fieldType = fieldType;
	}
	// ==============================================================
	// PUBLIC 
	// ==============================================================	
	@Override
	public boolean canGenerate(String fieldName, String fieldType,
			String remainder) {
		
		boolean matchesType= matchesFieldType(fieldType);
		if (!matchesType)
			return false;
		
		if (remainder!=null && !remainder.isEmpty()) { 
			ParsedRemainderData data=new ParsedRemainderData(remainder);
			return canProcessOtherSpecParts(data.validationSpec,
					data.defaultValue);		
		}
		return true;
		
	}

	@Override
	public FieldSchema<ModelTYPE> generate(String fieldName, String fieldType,
			String remainder) {
		
		if (!matchesFieldType(fieldType)) {
			throw new IllegalStateException("Can't support field type");
		}
		
		ParsedRemainderData data = new ParsedRemainderData(remainder);
		
		return generateImplementation(fieldName,fieldType,
									  data.validationSpec,
									  data.defaultValue);
	}
	
	// ==============================================================
	// PROTECTED 
	// ==============================================================
	protected boolean matchesFieldType(String fieldType) {
		return fieldType.equals(this.fieldType);
	}
	
	abstract protected FieldSchema<ModelTYPE> generateImplementation(String fieldName, 
			String fieldType,
			String validationSpec, String defaultValue);
	
	protected boolean canProcessOtherSpecParts(String validationSpec,
			String defaultValue) {
		return false;
	}
	
	
	
   /** Organize the remainder data */
   class ParsedRemainderData {
		String validationSpec;
		String defaultValue;
		
		public ParsedRemainderData(String remainder) {
			validationSpec ="";
			defaultValue="";
			
			if (remainder!=null && !remainder.isEmpty()) {
				int atIndex = remainder.indexOf('@');
				int poundIndex =remainder.indexOf("#");
				
				/** Get validation specification */
				if (atIndex>=0) {
					int endIndex= (poundIndex>=0) ? poundIndex : remainder.length();
					validationSpec=remainder.substring(atIndex,endIndex);
					if (!validationSpec.startsWith("@(") || 
						!validationSpec.endsWith(")")) {
						throw new IllegalStateException("Illegal validation string");
					}
					validationSpec = validationSpec.
							substring(2,validationSpec.length()-1);
				}
				/** Get default value */
				if (poundIndex>0) {
					defaultValue = remainder.substring(poundIndex+1);
				}
			}
		}
	};
}
