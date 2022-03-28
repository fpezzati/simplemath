package edu.pezzati.simplemath.operator.tool;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;

public class AbsIntComputator implements AbsComputator<ExpressionTerm<Integer>> {

	@Override
	public ExpressionTerm<Integer> compute(ExpressionTerm<Integer> term) {
		if(term.evaluate() < 0) {
			return new Constant<Integer>(-1 * term.evaluate());
		} else {
			return term;
		}
	}
}
