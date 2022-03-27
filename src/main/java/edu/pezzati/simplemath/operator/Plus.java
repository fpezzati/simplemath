package edu.pezzati.simplemath.operator;

import java.util.List;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.error.SimpleMathError;

public class Plus implements ExpressionTerm<Integer> {

	private List<ExpressionTerm<Integer>> exps;

	public Plus(List<ExpressionTerm<Integer>> exp) {
		if(exp.size() < 2) throw new SimpleMathError();
		this.exps = exp;
	}

	@Override
	public Integer evaluate() {
		Integer sum = 0;
		for(ExpressionTerm<Integer> singleExp : exps) {
			sum = sum + singleExp.evaluate();
		}
		return sum;
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
	
	public List<ExpressionTerm<Integer>> getTerms() {
		return exps;
	}
}
