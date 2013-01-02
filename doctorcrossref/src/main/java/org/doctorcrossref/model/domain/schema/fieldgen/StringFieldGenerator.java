package org.doctorcrossref.model.domain.schema.fieldgen;

import org.doctorcrossref.model.domain.EntityObject;
import org.tierlon.evaluation.expression.IExpression;
import org.tierlon.schema.support.FieldSchema;
import org.tierlon.schema.support.fields.AbstractFieldGenerator;
import org.tierlon.schema.support.parser.MultiRegexParser;
import org.tierlon.schema.support.parser.RegexProcessorFactory;

public class StringFieldGenerator 
	extends AbstractFieldGenerator<EntityObject,Object>{

	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public StringFieldGenerator() {
		super("string");
	}

	// ==============================================================
	// PROTECTED IMPLEMENTATION 
	// ==============================================================
	@Override
	protected FieldSchema<EntityObject, Object> generateImplementation(
			String fieldName, String fieldType, String validationSpec,
			String defaultValue) {
		
		System.out.println("StringFieldGenerator>> fieldName: "+fieldName);
		System.out.println("StringFieldGenerator>> fieldType: "+fieldType);
		System.out.println("StringFieldGenerator>> validationSpec: "+validationSpec);
		System.out.println("StringFieldGenerator>> defaultValue: "+defaultValue);
		
		// TEMPORARY CODING -- INTENT TO REFACTOR
		if (validationSpec!=null && !validationSpec.trim().isEmpty()) {
			MultiRegexParser<Object> regexParser = 
					new MultiRegexParser<Object>(null);
			
			RegexProcessorFactory<Object> regexProcFactory = 
					new RegexProcessorFactory<Object>();
			
			regexParser.addRegexProcessorFamily("boolean",
					regexProcFactory.generateBooleanParsers());
			regexParser.addRegexProcessorFamily("comparison",
					regexProcFactory.generateComparisonParsers());
			regexParser.addRegexProcessorFamily("string",
					regexProcFactory.generateStringParsers());
			
			Object parseResult = regexParser.parse(validationSpec);
			IExpression<Object> resultExpression = (IExpression<Object>)parseResult;
			//***** WORKING HERE ****
		}
		
		return null;
	}

	@Override
	protected boolean canProcessOtherSpecParts(String validationSpec,
			String defaultValue) {
		return true;
	}
	
}
