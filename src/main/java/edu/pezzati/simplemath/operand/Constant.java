package edu.pezzati.simplemath.operand;

import edu.pezzati.simplemath.ExpressionTerm;

public class Constant implements ExpressionTerm<Integer> {

	private Integer value;

	public Constant(Integer value) {
		this.value = value;
	}

	@Override
	public Integer evaluate() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Constant other = (Constant) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
