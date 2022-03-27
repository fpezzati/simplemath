package edu.pezzati.simplemath.app;

import java.util.HashMap;
import java.util.Map;

public class ExpressionMap {

	private Map<String, Expression> variablesMap;
	
	public ExpressionMap() {
		variablesMap = new HashMap<>();
	}

	public Expression getExpression(String symbolName) {
		return variablesMap.get(symbolName);
	}

	public Map<String, Expression> getVariablesMap() {
		return variablesMap;
	}
}
