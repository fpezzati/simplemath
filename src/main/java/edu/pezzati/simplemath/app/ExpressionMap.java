package edu.pezzati.simplemath.app;

import java.util.HashMap;
import java.util.Map;

public class ExpressionMap<V extends Number> {

	private Map<String, Expression<V>> variablesMap;
	
	public ExpressionMap() {
		variablesMap = new HashMap<>();
	}

	public Expression<V> getExpression(String symbolName) {
		return variablesMap.get(symbolName);
	}

	public Map<String, Expression<V>> getVariablesMap() {
		return variablesMap;
	}
}
