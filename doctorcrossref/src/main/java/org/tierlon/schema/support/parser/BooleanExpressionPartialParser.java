package org.tierlon.schema.support.parser;

import org.tierlon.evaluation.expression.IExpression;
import org.tierlon.evaluation.expression.IllegalEvaluationTypeException;
import org.tierlon.evaluation.expression.logic.BooleanNotExpression;
import org.tierlon.transform.process.IString2TokenProcessor;
import org.tierlon.transform.process.RegexBasedStringProcessor;
import org.tierlon.transform.process.String2TokenContext;
import org.tierlon.transform.process.StringProcessParens2Tokens;

public class BooleanExpressionPartialParser<ExpressionContext> {

	public static final String NOT_REGEX = "NOT\\s*__Token[0-9]+__";
	
	// Simply || but escaped for strings+regex
	private static final String OR_TKN="\\|\\|";
	private static final String AND_TKN="&&";
	
	public static final String OR_REGEX = 
		".*?"+OR_TKN+"\\s*\\S.*?"+
		"("+OR_TKN+"\\s*\\S.*?)*";
	public static final String AND_REGEX=
			".*?"+AND_TKN+"\\s*\\S.*?"+
			"("+AND_TKN+"\\s*\\S.*?)*";
	
	// ------------------------------------------
	private IString2TokenProcessor defaultProcessor;
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================	
	public BooleanExpressionPartialParser(IString2TokenProcessor baseProcessor)  {
		this.defaultProcessor = baseProcessor;
	}
	// ==============================================================
	// PUBLIC Methods
	// ==============================================================	
	public Object parse(String string) {
		String2TokenContext s2Tcontext = new String2TokenContext(string);
		
		BooleanRuleProcessor<ExpressionContext> expressionProcessor= 
				new BooleanRuleProcessor<ExpressionContext>();
		
		expressionProcessor.setDefaultProcessor(defaultProcessor);		
		
		StringProcessParens2Tokens parensProcessor = 
				new StringProcessParens2Tokens(expressionProcessor);
		
		return parensProcessor.processString2Token(s2Tcontext);
	}


	// ==============================================================
	// OTHER
	// ==============================================================
	
	/**
	 * Organize the other processors we use with the regular 
	 * expressions we use to process them.
	 * @author justinanddiana
	 *
	 */
	class BooleanRuleProcessor<ExpressionContext>
		extends RegexBasedStringProcessor {

		public BooleanRuleProcessor() {

			super();

			super.addRegexProcessor(NOT_REGEX, new NegationProcessor<ExpressionContext>());
			super.addRegexProcessor(OR_REGEX, new OrProcessor());
			super.addRegexProcessor(AND_REGEX, new AndProcessor());
		}
	}
}

class NegationProcessor<ExpressionContext> implements IString2TokenProcessor {

	@SuppressWarnings("rawtypes")
	@Override
	public Object processString2Token(String2TokenContext context) {
		String mytoken = context.getCurrentState();
		mytoken =mytoken.replace("NOT","").trim();
		Object data = context.getToken(mytoken);
		if (data instanceof String) {
			context.pushStringContext((String)data);
			data=context.getRootProcessor().processString2Token(context);
			context.popState();			
		}
		
		BooleanNotExpression<ExpressionContext> newExp =
				new BooleanNotExpression<ExpressionContext>();
		newExp.addOperands(data);
		return newExp;		
	}

}

class OrProcessor implements IString2TokenProcessor {

	@Override
	public Object processString2Token(String2TokenContext context) {
		
		return null;
	}

}

class AndProcessor implements IString2TokenProcessor {

	@Override
	public Object processString2Token(String2TokenContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}