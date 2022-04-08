package edu.pezzati.simplemath.app.tool;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.error.NotEvaluableExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

class EvaluatorIntegerTermTest {

	private Evaluator<Integer> sut;
	
	@BeforeEach
	void initEach() {
		sut = new EvaluatorIntegerTerm();
	}
	
	@Test
	void sutCannotEvaluateExpressionsContainingVariables() {
		Minus<Integer> term = new Minus<>(new Variable<>("YXY"), new Constant<>(10));
		Assertions.assertThrows(NotEvaluableExpressionTerm.class, ()->{
			sut.evaluate(term);
		});
	}
	
	@Test
	void sutEvaluatesPlusTermByAddingNestedTermsValues() {
		Plus<Integer> term = new Plus<>(Arrays.asList(
				new Constant<>(5),
				new Minus<>(
						new Constant<>(-3),
						new Constant<>(1))
				));
		Assertions.assertEquals(1, sut.evaluate(term));
	}
	
	@Test
	void sutEvaluatesDivisionByDividingNestedTerms() {
		Division<Integer> term = new Division<Integer>(
				new Constant<>(10), 
				new Multiplication<>(Arrays.asList(new Constant<>(2), new Constant<>(1)))
				);
		Assertions.assertEquals(5, sut.evaluate(term));
	}
	
	@Test
	void sutEvaluatesMultiplicationByMultiplyingNestedTermsValues() {
		Multiplication<Integer> term = new Multiplication<>(Arrays.asList(
				new Plus<>(Arrays.asList(
						new Constant<>(5), 
						new Constant<>(3), 
						new Abs<>(new Minus<>(new Constant<>(-2), new Constant<>(4)))
						)),
				new Minus<>(new Constant<>(10), new Constant<>(7)),
				new Division<>(new Constant<>(6), new Constant<>(3))
				));
		Assertions.assertEquals(84, sut.evaluate(term));
	}
}
