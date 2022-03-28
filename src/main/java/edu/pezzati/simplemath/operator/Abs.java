package edu.pezzati.simplemath.operator;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.operator.tool.AbsComputator;

public class Abs<V extends Number> implements ExpressionTerm<V> {

	private ExpressionTerm<V> exp;
	private AbsComputator<ExpressionTerm<V>> computator;

	public Abs(ExpressionTerm<V> exp, AbsComputator<ExpressionTerm<V>> computator) {
		this.exp = exp;
		this.computator = computator;
	}

	@Override
	public V evaluate() {
		return computator.compute(exp).evaluate();
	}
	
	public ExpressionTerm<V> getTerm() {
		return exp;
	}
	
	@Override
	public void accept(SimpleMathVisitor v) {
		v.visit(this);
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
