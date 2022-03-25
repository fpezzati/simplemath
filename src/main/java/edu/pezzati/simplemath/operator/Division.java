package edu.pezzati.simplemath.operator;

import edu.pezzati.simplemath.ExpressionTerm;

public class Division implements ExpressionTerm<Integer> {

	private ExpressionTerm<Integer> leftOp;
	private ExpressionTerm<Integer> rightOp;

	public Division(ExpressionTerm<Integer> leftOp, ExpressionTerm<Integer> rightOp) {
		this.leftOp = leftOp;
		this.rightOp = rightOp;
	}

	@Override
	public Integer evaluate() {
		return leftOp.evaluate() / rightOp.evaluate();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leftOp == null) ? 0 : leftOp.hashCode());
		result = prime * result + ((rightOp == null) ? 0 : rightOp.hashCode());
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
		Division other = (Division) obj;
		if (leftOp == null) {
			if (other.leftOp != null)
				return false;
		} else if (!leftOp.equals(other.leftOp))
			return false;
		if (rightOp == null) {
			if (other.rightOp != null)
				return false;
		} else if (!rightOp.equals(other.rightOp))
			return false;
		return true;
	}
}
