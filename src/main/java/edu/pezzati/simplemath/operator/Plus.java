package edu.pezzati.simplemath.operator;

import java.util.List;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.error.SimpleMathError;
import edu.pezzati.simplemath.operator.tool.PlusComputator;

public class Plus<V extends Number> implements ExpressionTerm<V> {

	private List<ExpressionTerm<V>> exps;
	private PlusComputator<ExpressionTerm<V>> computator;

	public Plus(List<ExpressionTerm<V>> exps, PlusComputator<ExpressionTerm<V>> computator) {
		if(exps.size() < 2) throw new SimpleMathError();
		this.exps = exps;
		this.computator = computator;
	}

	@Override
	public V evaluate() {
		return computator.compute(exps).evaluate();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exps == null) ? 0 : exps.hashCode());
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
		if (exps == null) {
			if (other.exps != null)
				return false;
		} else if (!exps.equals(other.exps))
			return false;
		return true;
	}
	
	@Override
	public void accept(SimpleMathVisitor v) {
		v.visit(this);
	}
	
	public List<ExpressionTerm<V>> getTerms() {
		return exps;
	}
}
