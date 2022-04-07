package edu.pezzati.simplemath.app.tool;

import edu.pezzati.simplemath.ExpressionTerm;

public interface Evaluator<V extends Number> {

	V evaluate(ExpressionTerm<V> term);

}
