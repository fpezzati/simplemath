package edu.pezzati.simplemath.app.tool;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

public class SubstituteVariablesInExpression<V extends Number> implements SubstituteVariables<V>, SimpleMathVisitor<V> {

	private ExpressionTerm<V> substitutionTerm;
	private String variableName;

	@Override
	public ExpressionTerm<V> substitute(ExpressionTerm<V> toCheckTerm, String variableName,
			ExpressionTerm<V> substitutionTerm) {
		this.variableName = variableName;
		this.substitutionTerm = substitutionTerm;
		// MAKE A COPY OF toCheckTerm
		ExpressionTerm<V> copyTerm = null;
		copyTerm.accept(this);
		return copyTerm;
	}
	
	private boolean checkInnerTermIsVariableWeAreLookingFor(ExpressionTerm<V> term) {
		return (term instanceof Variable && ((Variable<V>) term).getName().equals(variableName));
	}

	@Override
	public void visit(Constant<V> term) {
		// DO NOTHING.
	}

	@Override
	public void visit(Variable<V> term) {
		// DO NOTHING.
	}

	@Override
	public void visit(Abs<V> term) {
		if(checkInnerTermIsVariableWeAreLookingFor(term.getTerm())) {
			
		}
		
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
		for(ExpressionTerm<V> mulTerm : term.getTerms()) {
			mulTerm.accept(this);
		}
	}

	@Override
	public void visit(Plus<V> term) {
		for(ExpressionTerm<V> plusTerm : term.getTerms()) {
			plusTerm.accept(this);
		}
	}

}
