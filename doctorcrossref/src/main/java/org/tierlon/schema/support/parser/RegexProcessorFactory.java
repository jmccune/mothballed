package org.tierlon.schema.support.parser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.tierlon.evaluation.expression.logic.ComparisonExpression;
import org.tierlon.transform.process.String2Token.IString2TokenProcessor;
import org.tierlon.transform.process.String2Token.String2TokenContext;

public class RegexProcessorFactory<ExpContextTYPE> {

	Map<String,IString2TokenProcessor> generateTokenProcessor() {
		Map<String,IString2TokenProcessor> contextMap = 
				new HashMap<String,IString2TokenProcessor>();
		
		contextMap.put(String2TokenContext.getTokenRegex(),
				new TokenProcessor2());
		
		return contextMap;
	}
	
	
	//private final String CHAR_REGEX="[\\sA-Za-z_-0-9!@#$%^*()~\".,?/\\\\{}\\[\\]']*?";
	private final String LT_REGEX=".*?<.*?";
	private final String LTE_REGEX=".*?<=.*?";
	private final String EQ_REGEX=".*?==.*?";
	private final String NOTEQ_REGEX=".*?!=.*?";
	private final String GT_REGEX=".*?>.*?";	
	private final String GTE_REGEX=".*?>=.*?";
	// ORDER MATTERS TO THESE THINGS...  Need to fix the hash map.
	public Map<String,IString2TokenProcessor> generateComparisonParsers() {
		Map<String,IString2TokenProcessor> contextMap = 
				new LinkedHashMap<String,IString2TokenProcessor>();
		
		contextMap.put(String2TokenContext.getTokenRegex(),
				new TokenProcessor2());
		
		NumericalComparisonProcessor<ExpContextTYPE> comparisonProcessor=
				new NumericalComparisonProcessor<ExpContextTYPE>();
		
		contextMap.put(LTE_REGEX,comparisonProcessor);
		contextMap.put(LT_REGEX,comparisonProcessor);
		contextMap.put(GT_REGEX,comparisonProcessor);
		contextMap.put(EQ_REGEX,comparisonProcessor);
		contextMap.put(NOTEQ_REGEX,comparisonProcessor);
		contextMap.put(GTE_REGEX,comparisonProcessor);
		
		return contextMap;
	}
	
	public Map<String,IString2TokenProcessor> generateBooleanParsers() {
		Map<String,IString2TokenProcessor> contextMap = 
				new LinkedHashMap<String,IString2TokenProcessor>();
		
		contextMap.put(String2TokenContext.getTokenRegex(),
				new TokenProcessor2());
		
		contextMap.put(BooleanExpressionPartialParser.NOT_REGEX, new NegationProcessor<ExpContextTYPE>());
		contextMap.put(BooleanExpressionPartialParser.OR_REGEX, new OrProcessor<ExpContextTYPE>());
		contextMap.put(BooleanExpressionPartialParser.AND_REGEX, new AndProcessor<ExpContextTYPE>());
		
		return contextMap;
			
	}
	
	
}






class TokenProcessor2 implements IString2TokenProcessor {
	@Override
	public Object processString2Token(String2TokenContext context) {
		String tokenName = context.getCurrentState().trim();
		return context.getToken(tokenName);
	}
}

class NumericalComparisonProcessor<ContextTYPE> implements IString2TokenProcessor {

	String[] comparisons={"<=",">=","==","!=",">","<"};
	
	@Override
	public Object processString2Token(String2TokenContext context) {
		String token = context.getCurrentState().trim();
			
		int index=-1;
		String comparisonFound=null;
		for (String comparison : comparisons) {
			index= token.indexOf(comparison); 
			if (index>-1) {
				comparisonFound = comparison;
				break;
			}
		}
		
		String[] left2right = token.split(comparisonFound);
		IString2TokenProcessor processor = context.getRootProcessor();
		ComparisonExpression<ContextTYPE,Number> comparisonOperator = 
				ComparisonExpression.generateNumericalComparison(comparisonFound);
			
		for (String leafToken : left2right) {
			context.pushStringContext(leafToken);
			Object operand=processor.processString2Token(context);
			comparisonOperator.addOperands(operand);
			context.popState();			
		}
		
		return comparisonOperator;
	}
	
}
