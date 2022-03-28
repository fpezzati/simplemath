package edu.pezzati.simplemath.operator.tool;

import java.util.List;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.error.SimpleMathError;
import edu.pezzati.simplemath.operand.Constant;

public class MultiplicationIntComputator implements MultiplicationComputator<ExpressionTerm<Integer>> {

	@Override
	public ExpressionTerm<Integer> compute(List<ExpressionTerm<Integer>> terms) {
		if(terms.size() < 2) throw new SimpleMathError();
		Integer mult = 1;
		for(ExpressionTerm<Integer> term : terms) {
			mult = mult * term.evaluate();
		}
		return new Constant<Integer>(mult);
	}
}
