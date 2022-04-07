package edu.pezzati.simplemath.app.tool;

import java.util.ArrayList;
import java.util.List;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.error.SimpleMathError;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

public class SubstituteVariablesInExpression<V extends Number> implements SubstituteVariables<V>, SimpleMathVisitor<V> {

	private ExpressionTerm<V> lastVisitedTerm;
	private ExpressionTerm<V> substitutionTerm;
	private String variableName;

	@Override
	public ExpressionTerm<V> substitute(ExpressionTerm<V> toCheckTerm, String variableName,
			ExpressionTerm<V> substitutionTerm) {
		if(variableName == null || variableName.isEmpty()) throw new SimpleMathError();
		if(substitutionTerm == null) throw new SimpleMathError();
		this.variableName = variableName;
		this.substitutionTerm = substitutionTerm;
		toCheckTerm.accept(this);
		return lastVisitedTerm;
	}
	
	@Override
	public void visit(Constant<V> term) {
		lastVisitedTerm = new Constant<>(term.getValue());
	}

	@Override
	public void visit(Variable<V> term) {
		if(term.getName().equals(variableName)) {
			lastVisitedTerm = substitutionTerm;
		} else {
			lastVisitedTerm = new Variable<V>(term.getName());
		}
	}

	@Override
	public void visit(Abs<V> term) {
		term.getTerm().accept(this);
		ExpressionTerm<V> abs = lastVisitedTerm;
		lastVisitedTerm = new Abs<V>(abs);
	}

	@Override
	public void visit(Division<V> term) {
		term.getLeft().accept(this);
		ExpressionTerm<V> left = lastVisitedTerm;
		term.getRight().accept(this);
		ExpressionTerm<V> right = lastVisitedTerm;
		lastVisitedTerm = new Division<V>(left, right);
	}

	@Override
	public void visit(Minus<V> term) {
		term.getLeft().accept(this);
		ExpressionTerm<V> left = lastVisitedTerm;
		term.getRight().accept(this);
		ExpressionTerm<V> right = lastVisitedTerm;
		lastVisitedTerm = new Minus<V>(left, right);
	}

	@Override
	public void visit(Multiplication<V> term) {
		List<ExpressionTerm<V>> terms = new ArrayList<>();
		for(ExpressionTerm<V> plusTerm : term.getTerms()) {
			plusTerm.accept(this);
			terms.add(lastVisitedTerm);
		}
		lastVisitedTerm = new Multiplication<V>(terms);
	}

	@Override
	public void visit(Plus<V> term) {
		List<ExpressionTerm<V>> terms = new ArrayList<>();
		for(ExpressionTerm<V> plusTerm : term.getTerms()) {
			plusTerm.accept(this);
			terms.add(lastVisitedTerm);
		}
		lastVisitedTerm = new Plus<V>(terms);
	}
}
