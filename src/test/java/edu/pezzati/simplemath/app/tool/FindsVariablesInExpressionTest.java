package edu.pezzati.simplemath.app.tool;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.SimpleMathVisitor;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

class FindsVariablesInExpressionTest {

	private FindsVariables<Integer> sut;

	@BeforeEach
	void initEach() {
		sut = new FindsVariablesInExpression<Integer>();
	}

	@Test
	void sutComesWithAnEmptyJournal() {
		Assertions.assertTrue(((FindsVariablesInExpression<Integer>) sut).getVariableNames().isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	void whenSutVisitsConstantItAddsNothingToItsJournal() {
		Constant<Integer> term = Mockito.mock(Constant.class);
		sut.visit(term);
		Assertions.assertTrue(((FindsVariablesInExpression<Integer>)sut).getVariableNames().isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	void whenSutVisitsVariableItAddsVariableNameToItsJournal() {
		Variable<Integer> term = Mockito.mock(Variable.class);
		Mockito.when(term.getName()).thenReturn("X");
		sut.visit(term);
		Mockito.verify(term, Mockito.never()).accept(Mockito.any(SimpleMathVisitor.class));
		Assertions.assertEquals(new HashSet<String>(Arrays.asList("X")), ((FindsVariablesInExpression<Integer>)sut).getVariableNames());
	}

	@Test
	void whenSutVisitsAbsItVisistsAbsTermToo() {
		ExpressionTerm<Integer> term = getTerm();
		Abs<Integer> abs = new Abs<>(term);
		sut.visit(abs);
		checkTermsAreEvaluated(term);
	}

	@Test
	void whenSutVisitsDivisionItVisitsDivisionLeftTermAndDivisionRightTermToo() {
		ExpressionTerm<Integer> term1 = getTerm();
		ExpressionTerm<Integer> term2 = getTerm();
		Division<Integer> term = new Division<>(term1, term2);
		sut.visit(term);
		checkTermsAreEvaluated(term1, term2);
	}

	@Test
	void whenSutVisitsMinusItVisitsMinusLeftTermAndDivisionRightTermToo() {
		ExpressionTerm<Integer> term1 = getTerm();
		ExpressionTerm<Integer> term2 = getTerm();
		Minus<Integer> term = new Minus<>(term1, term2);
		sut.visit(term);
		checkTermsAreEvaluated(term1, term2);
	}

	@Test
	void whenSutVisitsMultiplicationItAlsoVisitsAllOfMultiplicationTerms() {
		ExpressionTerm<Integer> term1 = getTerm();
		ExpressionTerm<Integer> term2 = getTerm();
		ExpressionTerm<Integer> term3 = getTerm();
		Multiplication<Integer> term = new Multiplication<>(Arrays.asList(term1, term2, term3));
		sut.visit(term);
		checkTermsAreEvaluated(term1, term2, term3);
		
	}

	@Test
	void whenSutVisitsSumItAlsoVisitsAllOfSumTerms() {
		ExpressionTerm<Integer> term1 = getTerm();
		ExpressionTerm<Integer> term2 = getTerm();
		ExpressionTerm<Integer> term3 = getTerm();
		Plus<Integer> term = new Plus<>(Arrays.asList(term1, term2, term3));
		sut.visit(term);
		checkTermsAreEvaluated(term1, term2, term3);
	}
	
	@SuppressWarnings("unchecked")
	private ExpressionTerm<Integer> getTerm() {
		return Mockito.mock(ExpressionTerm.class);
	}
	
	@SuppressWarnings("unchecked")
	private void checkTermsAreEvaluated(ExpressionTerm<Integer>...expressionTerms) {
		InOrder ord = Mockito.inOrder(expressionTerms);
		for(ExpressionTerm<Integer> term : expressionTerms) {
			ord.verify(term, Mockito.times(1)).accept(Mockito.any(SimpleMathVisitor.class));
		}
	}
}
