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
	
	private Integer result;

	@Override
	public Integer evaluate(ExpressionTerm<Integer> term) {
		term.accept(this);
		return result;
	}

	@Override
	public void visit(Constant<Integer> term) {
		// DO NOTHING.
	}

	@Override
	public void visit(Variable<Integer> term) {
		throw new NotEvaluableExpressionTerm();
	}

	@Override
	public void visit(Abs<Integer> term) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Division<Integer> term) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Minus<Integer> term) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Multiplication<Integer> term) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Plus<Integer> term) {
		
		
	}
}
