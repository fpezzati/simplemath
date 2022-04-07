package edu.pezzati.simplemath.app.tool;

import edu.pezzati.simplemath.ExpressionTerm;

public interface SubstituteVariables<V extends Number> {

	ExpressionTerm<V> substitute(ExpressionTerm<V> toCheckTerm, String variableName, ExpressionTerm<V> substitutionTerm);

}
