package edu.pezzati.simplemath.app.tool;

import java.util.HashSet;
import java.util.Set;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

public class FindsVariablesInExpression<V extends Number> implements FindsVariables<V> {

	private Set<String> journal;
	
	public FindsVariablesInExpression() {
		journal = new HashSet<>();
	}

	@Override
	public void visit(Constant<V> term) {
		// DO NOTHING
	}

	@Override
	public void visit(Variable<V> term) {
		journal.add(term.getName());
	}

	@Override
	public void visit(Abs<V> term) {
		term.getTerm().accept(this);
	}

	@Override
	public void visit(Division<V> term) {
		term.getLeft().accept(this);
		term.getRight().accept(this);
	}

	@Override
	public void visit(Minus<V> term) {
		term.getLeft().accept(this);
		term.getRight().accept(this);
	}

	@Override
	public void visit(Multiplication<V> term) {
		for(ExpressionTerm<V> singleTerm : term.getTerms()) {
			singleTerm.accept(this);
		}
	}

	@Override
	public void visit(Plus<V> term) {
		for(ExpressionTerm<V> singleTerm : term.getTerms()) {
			singleTerm.accept(this);
		}
	}

	@Override
	public Set<String> getVariableNames() {
		return journal;
	}
}
