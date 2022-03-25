package edu.pezzati.simplemath.operator;

import java.util.List;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.error.SimpleMathError;

public class Multiplication implements ExpressionTerm<Integer> {

	private List<ExpressionTerm<Integer>> exps;

	public Multiplication(List<ExpressionTerm<Integer>> exps) {
		if(exps.size() < 2) throw new SimpleMathError();
		this.exps = exps;
	}

	@Override
	public Integer evaluate() {
		Integer mul = 1;
		for(ExpressionTerm<Integer> exp : exps) {
			mul = mul * exp.evaluate();
		}
		return mul;
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
		Multiplication other = (Multiplication) obj;
		if (exps == null) {
			if (other.exps != null)
				return false;
		} else if (!exps.equals(other.exps))
			return false;
		return true;
	}
}
