package org.tierlon.transform.process;

import java.util.List;

import org.tierlon.system.helper.StringHelper;

public class StringProcessParens2Tokens implements IString2TokenProcessor {

	private Character startExpressionMarker='(';
	private Character endExpressionMarker=')';
	private IString2TokenProcessor subExpressionProcessor;
	
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public StringProcessParens2Tokens(IString2TokenProcessor expressionParser) {
		if (expressionParser == null) {
			throw new NullPointerException();
		}
		this.subExpressionProcessor = expressionParser;
	}
	
	
	// ==============================================================
	// IMPLEMENTATION
	// ==============================================================
	
	@Override
	public Object processString2Token(String2TokenContext context) {
		
		
		while (true) {
			String rootExpression = context.getCurrentState();
			// Extract a sub-expression...
			List<String> results = StringHelper.extractInnerMost(
					startExpressionMarker, endExpressionMarker, rootExpression);

			// Check TERMINAL case
			String extractedExpression = results.get(1);
			if (extractedExpression.isEmpty()) {
				// Fact check:
				if (!results.get(2).isEmpty()) {
					throw new IllegalArgumentException("Illegal expression: "
							+ rootExpression);
				}

				return subExpressionProcessor.processString2Token(context);
			}

			context.pushStringContext(extractedExpression);
			String tokenName = context.generateNextTokenNameString();
			Object tokenValue = subExpressionProcessor
					.processString2Token(context);
			context.setToken(tokenName, tokenValue);
			context.popState();
			
			String nextState = results.get(0);
			nextState += " "+tokenName;
			if (!results.get(2).isEmpty())
				nextState+=" "+results.get(2);
			context.pushStringContext(nextState);
		}
	}
}
