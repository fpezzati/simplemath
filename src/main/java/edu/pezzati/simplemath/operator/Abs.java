package edu.pezzati.simplemath.operator;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;

public class Abs<V extends Number> implements ExpressionTerm<V> {

	private ExpressionTerm<V> exp;

	public Abs(ExpressionTerm<V> exp) {
		this.exp = exp;
	}

	public ExpressionTerm<V> getTerm() {
		return exp;
	}
	
	@Override
	public void accept(SimpleMathVisitor<V> v) {
		v.visit(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exp == null) ? 0 : exp.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
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
