package edu.pezzati.simplemath.app.utils;

import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;

import edu.pezzati.simplemath.app.Expression;
import edu.pezzati.simplemath.app.ExpressionMap;

public class ExpressionMapUtils {

	public ExpressionMap getExpressionMap(String...nodeAndEdges) {
		ExpressionMap map = new ExpressionMap();
		for(String nodeAndEdge : nodeAndEdges) {
			String[] vars = nodeAndEdge.split("=>");
			if(vars.length > 1) {
				map.getVariablesMap().put(vars[0].trim(), getExpression(vars[1].replaceAll(" ", "").split(",")));
			} else {
				map.getVariablesMap().put(vars[0].trim(), getExpression());
			}
		}
		return map;
	}
	
	public Expression getExpression(String...variables) {
		Expression exp = Mockito.mock(Expression.class);
		Mockito.when(exp.getReferencedSymbols()).thenReturn(Sets.newSet(variables));
		return exp;
	}
}
