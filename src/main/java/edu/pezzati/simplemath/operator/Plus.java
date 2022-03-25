package edu.pezzati.simplemath.operator;

import java.util.List;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.error.SimpleMathError;

public class Plus implements ExpressionTerm<Integer> {

	private List<ExpressionTerm<Integer>> exp;

	public Plus(List<ExpressionTerm<Integer>> exp) {
		if(exp.size() < 2) throw new SimpleMathError();
		this.exp = exp;
	}

	@Override
	public Integer evaluate() {
		Integer sum = 0;
		for(ExpressionTerm<Integer> singleExp : exp) {
			sum = sum + singleExp.evaluate();
		}
		return sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exp == null) ? 0 : exp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plus other = (Plus) obj;
		if (exp == null) {
			if (other.exp != null)
				return false;
		} else if (!exp.equals(other.exp))
			return false;
		return true;
	}
}
