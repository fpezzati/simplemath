package edu.pezzati.simplemath.app.tool;

import java.util.ArrayList;
import java.util.List;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

public class FindsVariablesInExpression implements SimpleMathVisitor {

	private List<String> journal;
	
	public FindsVariablesInExpression() {
		journal = new ArrayList<String>();
	}

	@Override
	public void visit(Constant term) {
		// DO NOTHING
	}

	@Override
	public void visit(Variable term) {
		journal.add(term.getName());
	}

	@Override
	public void visit(Abs term) {
		term.getTerm().accept(this);
	}

	@Override
	public void visit(Division term) {
		term.getLeft().accept(this);
		term.getRight().accept(this);
	}

	@Override
	public void visit(Minus term) {
		term.getLeft().accept(this);
		term.getRight().accept(this);
	}

	@Override
	public void visit(Multiplication term) {
		for(ExpressionTerm<Integer> singleTerm : term.getTerms()) {
			singleTerm.accept(this);
		}
	}

	@Override
	public void visit(Plus term) {
		for(ExpressionTerm<Integer> singleTerm : term.getTerms()) {
			singleTerm.accept(this);
		}
	}

	public List<String> getJournal() {
		return journal;
	}

}
