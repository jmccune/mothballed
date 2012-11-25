package org.tierlon.schema.support.parser;

import org.apache.log4j.Logger;
import org.tierlon.evaluation.expression.IllegalEvaluationTypeException;
import org.tierlon.evaluation.expression.logic.BooleanAndExpression;
import org.tierlon.evaluation.expression.logic.BooleanNotExpression;
import org.tierlon.evaluation.expression.logic.BooleanOrExpression;
import org.tierlon.transform.process.String2Token.IString2TokenProcessor;
import org.tierlon.transform.process.String2Token.RegexBasedStringProcessor;
import org.tierlon.transform.process.String2Token.String2TokenContext;
import org.tierlon.transform.process.String2Token.StringProcessParens2Tokens;

public class BooleanExpressionPartialParser<ExpressionContext> {

	public static final String TOKEN_REGEX = "\\s*__Token[0-9]+__\\s*";
	public static final String NOT_REGEX = "\\s*NOT\\s*__Token[0-9]+__\\s*";
	
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
		
		BooleanRuleProcessor expressionProcessor= 
				new BooleanRuleProcessor();
		
		expressionProcessor.setDefaultProcessor(defaultProcessor);	
		s2Tcontext.setRootProcessor(expressionProcessor);
		
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
	class BooleanRuleProcessor
		extends RegexBasedStringProcessor {

		public BooleanRuleProcessor() {

			super();

			super.addRegexProcessor(TOKEN_REGEX, new TokenProcessor<ExpressionContext>());
			super.addRegexProcessor(NOT_REGEX, new NegationProcessor<ExpressionContext>());
			super.addRegexProcessor(OR_REGEX, new OrProcessor<ExpressionContext>());
			super.addRegexProcessor(AND_REGEX, new AndProcessor<ExpressionContext>());
		}
	}
}


class TokenProcessor<ExpressionContext> implements IString2TokenProcessor {
	@Override
	public Object processString2Token(String2TokenContext context) {
		String tokenName = context.getCurrentState().trim();
		return context.getToken(tokenName);
	}
}

class NegationProcessor<ExpressionContext> implements IString2TokenProcessor {
	static Logger logger = Logger.getLogger(NegationProcessor.class);
	@Override
	public Object processString2Token(String2TokenContext context) {
		String mytoken = context.getCurrentState();
		mytoken =mytoken.replace("NOT","").trim();
		Object data = context.getToken(mytoken);
		if (data instanceof String) {
			context.pushStringContext((String)data);
			logger.debug("RECEIVED>> "+data);
			data=context.getRootProcessor().processString2Token(context);
			logger.debug("PROCESSED TO>> "+data);
			context.popState();			
		}
		
		BooleanNotExpression<ExpressionContext> newExp =
				new BooleanNotExpression<ExpressionContext>();
		newExp.addOperands(data);
		return newExp;		
	}

}



class OrProcessor<ExpressionContext> implements IString2TokenProcessor {

	@Override
	public Object processString2Token(String2TokenContext context) {
		
		String orExpressionString = context.getCurrentState();
		
		if (!orExpressionString.contains("||")) {
			throw new IllegalEvaluationTypeException(
			  "Not sure how this happened-- expecting a string with: || in it. "
			  +" Received: "+orExpressionString);
		}
		String[] terms = orExpressionString.split("\\|\\|");
		BooleanOrExpression<ExpressionContext> orExpression = 
				new BooleanOrExpression<ExpressionContext>();
		
		for (String term : terms) {
			term = term.trim();
			context.pushStringContext(term);
			Object operand = context.getRootProcessor().processString2Token(context);
			context.popState();			
			
			orExpression.addOperands(operand);
		}
		return orExpression;
	}	
}

class AndProcessor<ExpressionContext> implements IString2TokenProcessor {

	@Override
	public Object processString2Token(String2TokenContext context) {
		
		String andExpressionString = context.getCurrentState();
		
		if (!andExpressionString.contains("&&")) {
			throw new IllegalEvaluationTypeException(
			  "Not sure how this happened-- expecting a string with: && in it. "
			  +" Received: "+andExpressionString);
		}
		String[] terms = andExpressionString.split("\\&\\&");
		BooleanAndExpression<ExpressionContext> andExpression = 
				new BooleanAndExpression<ExpressionContext>();
		
		for (String term : terms) {
			term = term.trim();
			context.pushStringContext(term);
			Object operand = context.getRootProcessor().processString2Token(context);
			context.popState();			
			
			andExpression.addOperands(operand);
		}
		return andExpression;
	}	

}