package edu.pezzati.simplemath.operator;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.operator.tool.DivisionComputator;

public class Division<V extends Number> implements ExpressionTerm<V> {

	private ExpressionTerm<V> leftOp;
	private ExpressionTerm<V> rightOp;
	private DivisionComputator<ExpressionTerm<V>> computator;

	public Division(ExpressionTerm<V> leftOp, ExpressionTerm<V> rightOp, DivisionComputator<ExpressionTerm<V>> computator) {
		this.leftOp = leftOp;
		this.rightOp = rightOp;
		this.computator = computator;
	}

	@Override
	public V evaluate() {
		return computator.compute(leftOp, rightOp).evaluate();
	}

	@Override
	public void accept(SimpleMathVisitor v) {
		v.visit(this);
	}

	public ExpressionTerm<V> getLeft() {
		return leftOp;
	}
	
	public ExpressionTerm<V> getRight() {
		return rightOp;
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
