package edu.pezzati.simplemath.app.tool;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.error.NotEvaluableExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

public class EvaluatorIntegerTerm implements Evaluator<Integer>, SimpleMathVisitor<Integer> {
	
	private Integer lastVisitedInteger;

	@Override
	public Integer evaluate(ExpressionTerm<Integer> term) {
		term.accept(this);
		return lastVisitedInteger;
	}

	@Override
	public void visit(Constant<Integer> term) {
		lastVisitedInteger = term.getValue();
	}

	@Override
	public void visit(Variable<Integer> term) {
		throw new NotEvaluableExpressionTerm();
	}

	@Override
	public void visit(Abs<Integer> term) {
		Integer absResult = 0;
		term.getTerm().accept(this);
		if(lastVisitedInteger > 0) {
			absResult = lastVisitedInteger;
		} else {
			absResult = -1 * lastVisitedInteger;
		}
		lastVisitedInteger = absResult;
	}

	@Override
	public void visit(Division<Integer> term) {
		Integer leftResult = 0;
		Integer rightResult = 0;
		term.getLeft().accept(this);
		leftResult = lastVisitedInteger;
		term.getRight().accept(this);
		rightResult = lastVisitedInteger;
		lastVisitedInteger = leftResult / rightResult;
	}

	@Override
	public void visit(Minus<Integer> term) {
		Integer leftResult = 0;
		Integer rightResult = 0;
		term.getLeft().accept(this);
		leftResult = lastVisitedInteger;
		term.getRight().accept(this);
		rightResult = lastVisitedInteger;
		lastVisitedInteger = leftResult - rightResult;
	}

	@Override
	public void visit(Multiplication<Integer> term) {
		Integer result = 1;
		for(ExpressionTerm<Integer> plusTerm : term.getTerms()) {
			plusTerm.accept(this);
			result = result * lastVisitedInteger;
		}
		lastVisitedInteger = result;
		
	}

	@Override
	public void visit(Plus<Integer> term) {
		Integer result = 0;
		for(ExpressionTerm<Integer> plusTerm : term.getTerms()) {
			plusTerm.accept(this);
			result = result + lastVisitedInteger;
		}
		lastVisitedInteger = result;
	}
}
