package edu.pezzati.simplemath.operator;

import edu.pezzati.simplemath.ExpressionTerm;

public class Abs implements ExpressionTerm<Integer> {

	private ExpressionTerm<Integer> exp;

	public Abs(ExpressionTerm<Integer> exp) {
		this.exp = exp;
	}

	@Override
	public Integer evaluate() {
		Integer val = exp.evaluate();
		if(val < 0) {
			return -1 * val;
		} else {
			return exp.evaluate();
		}
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
		Abs other = (Abs) obj;
		if (exp == null) {
			if (other.exp != null)
				return false;
		} else if (!exp.equals(other.exp))
			return false;
		return true;
	}
}
