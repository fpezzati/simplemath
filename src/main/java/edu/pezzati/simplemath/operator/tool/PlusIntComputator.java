package edu.pezzati.simplemath.operator.tool;

import java.util.List;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.error.SimpleMathError;
import edu.pezzati.simplemath.operand.Constant;

public class PlusIntComputator implements PlusComputator<ExpressionTerm<Integer>> {

	@Override
	public ExpressionTerm<Integer> compute(List<ExpressionTerm<Integer>> terms) {
		if(terms.size() < 2) throw new SimpleMathError();
		Integer sum = 0;
		for(ExpressionTerm<Integer> term : terms) {
			sum = sum + term.evaluate();
		}
		return new Constant<Integer>(sum);
	}
}
