package org.tierlon.schema.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tierlon.evaluation.expression.IExpression;
import org.tierlon.transform.ITransformData;

public class BooleanExpressionPartialParser {

	
	public void parse(String string) {
		
	}
}


/**
 * 
 * 
 * @author justinanddiana
 *
 */
class ExpressionTreeBuilder<ContextTYPE> {
	
	Map<String,String> subexpressionMap = new HashMap<String,String>();
	
	public IExpression<ContextTYPE> buildExpression(String expression,
			ITransformData<String,IExpression<ContextTYPE>> leafParser) {
		
		subexpressionMap.put("__ROOT__",expression);
		while (buildTree());
		return null;
		
	}
	
	boolean buildTree()
	{
		String rootExpression = subexpressionMap.get("__ROOT__");
		//Extract a sub-expression...
		
		
		
		
		return true;
	}
}




class TreeNode {
	private List<Object> children;
	enum TYPE { OR, AND, NOT};
	
	TreeNode(TYPE operationType) {
		
	}
	
	public void add(String subExpression) {
		children.add(subExpression);
	}
	
	public void add(TreeNode node) {
		children.add(node);
	}
	
}