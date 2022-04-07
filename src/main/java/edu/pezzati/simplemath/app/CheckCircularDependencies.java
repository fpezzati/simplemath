package edu.pezzati.simplemath.app;

import java.util.List;

public interface CheckCircularDependencies {

	/**
	 * @param map the map to check.
	 * 
	 * @return true if no circular dependency is found, false otherwise
	 */
	boolean check(ExpressionMap map);

	/**
	 * if no circular dependency is found, method returns the order in which
	 * variables can be substituted in expression.
	 * 
	 * @return a list of ordered variable names
	 */
	List<String> sortedElements();

}
