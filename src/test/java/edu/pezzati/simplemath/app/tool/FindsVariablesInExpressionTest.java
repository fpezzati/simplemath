package edu.pezzati.simplemath.app.tool;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.Expression;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

class FindsVariablesInExpressionTest {

	SimpleMathVisitor sut;

	@BeforeEach
	void initEach() {
		sut = new FindsVariablesInExpression();
	}

	@Test
	void sutComesWithAnEmptyJournal() {
		Assertions.assertTrue(((FindsVariablesInExpression) sut).getJournal().isEmpty());
	}

	@Test
	void whenSutVisitsConstantItAddsNothingToItsJournal() {
		Constant term = Mockito.mock(Constant.class);
		sut.visit(term);
		Mockito.verify(term, Mockito.never()).evaluate();
		Assertions.assertTrue(((FindsVariablesInExpression)sut).getJournal().isEmpty());
	}

	@Test
	void whenSutVisitsVariableItAddsVariableNameToItsJournal() {
		Variable term = Mockito.mock(Variable.class);
		Mockito.when(term.getName()).thenReturn("X");
		sut.visit(term);
		Mockito.verify(term, Mockito.never()).accept(Mockito.any(SimpleMathVisitor.class));
		Assertions.assertEquals(Arrays.asList("X"), ((FindsVariablesInExpression)sut).getJournal());
	}

	@Test
	void whenSutVisitsAbsItVisistsAbsTermToo() {
		ExpressionTerm<Integer> term = getTerm();
		Abs abs = new Abs(term);
		sut.visit(abs);
		checkTermsAreEvaluated(term);
	}

	@Test
	void whenSutVisitsDivisionItVisitsDivisionLeftTermAndDivisionRightTermToo() {
		ExpressionTerm<Integer> term1 = getTerm();
		ExpressionTerm<Integer> term2 = getTerm();
		Division term = new Division(term1, term2);
		sut.visit(term);
		checkTermsAreEvaluated(term1, term2);
	}

	@Test
	void whenSutVisitsMinusItVisitsMinusLeftTermAndDivisionRightTermToo() {
		ExpressionTerm<Integer> term1 = getTerm();
		ExpressionTerm<Integer> term2 = getTerm();
		Minus term = new Minus(term1, term2);
		sut.visit(term);
		checkTermsAreEvaluated(term1, term2);
	}

	@Test
	void whenSutVisitsMultiplicationItAlsoVisitsAllOfMultiplicationTerms() {
		ExpressionTerm<Integer> term1 = getTerm();
		ExpressionTerm<Integer> term2 = getTerm();
		ExpressionTerm<Integer> term3 = getTerm();
		Multiplication term = new Multiplication(Arrays.asList(term1, term2, term3));
		sut.visit(term);
		checkTermsAreEvaluated(term1, term2, term3);
		
	}

	@Test
	void whenSutVisitsSumItAlsoVisitsAllOfSumTerms() {
		ExpressionTerm<Integer> term1 = getTerm();
		ExpressionTerm<Integer> term2 = getTerm();
		ExpressionTerm<Integer> term3 = getTerm();
		Plus term = new Plus(Arrays.asList(term1, term2, term3));
		sut.visit(term);
		checkTermsAreEvaluated(term1, term2, term3);
	}
	
	@SuppressWarnings("unchecked")
	private ExpressionTerm<Integer> getTerm() {
		return Mockito.mock(ExpressionTerm.class);
	}
	
	private void checkTermsAreEvaluated(ExpressionTerm<Integer>...expressionTerms) {
		InOrder ord = Mockito.inOrder(expressionTerms);
		for(ExpressionTerm<Integer> term : expressionTerms) {
			ord.verify(term, Mockito.times(1)).accept(Mockito.any(SimpleMathVisitor.class));
		}
	}
}
