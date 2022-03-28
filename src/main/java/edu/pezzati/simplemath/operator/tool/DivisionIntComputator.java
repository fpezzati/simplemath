package edu.pezzati.simplemath.operator.tool;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;

public class DivisionIntComputator implements DivisionComputator<ExpressionTerm<Integer>> {

	@Override
	public ExpressionTerm<Integer> compute(ExpressionTerm<Integer> leftTerm, ExpressionTerm<Integer> rightTerm) {
		return new Constant<Integer>(leftTerm.evaluate() / rightTerm.evaluate());
	}
}
