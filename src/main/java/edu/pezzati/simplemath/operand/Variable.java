package edu.pezzati.simplemath.operand;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;

public class Variable<V extends Number> implements ExpressionTerm<V> {

	private String name;
	private ExpressionTerm<V> exp;
	
	public Variable(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setExpression(ExpressionTerm<V> exp) {
		this.exp = exp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exp == null) ? 0 : exp.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Variable<V> other = (Variable) obj;
		if (exp == null) {
			if (other.exp != null)
				return false;
		} else if (!exp.equals(other.exp))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public void accept(SimpleMathVisitor<V> v) {
		v.visit(this);
	}
}
