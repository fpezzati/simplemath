package edu.pezzati.simplemath;

import edu.pezzati.simplemath.app.SimpleMathVisitable;

/**
 * Main simplemath building block.
 * 
 * @author fpezzati
 *
 * @param <V>
 */
public interface ExpressionTerm<V extends Number> extends SimpleMathVisitable<V>{

	V evaluate();
}
