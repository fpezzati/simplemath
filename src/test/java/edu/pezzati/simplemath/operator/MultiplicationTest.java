package edu.pezzati.simplemath.operator;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.error.SimpleMathError;
import edu.pezzati.simplemath.operand.Constant;

class MultiplicationTest {

	@Test
	void multiplicationRaisesAnErrorIfOnlyOneExpressionIsPassed() {
		ExpressionTerm<Integer> exp = new Constant(0);
		Assertions.assertThrows(SimpleMathError.class, ()-> {
			new Multiplication(Arrays.asList(exp));
		});
	}
	
	@Test
	void multiplicatorCanStoreTwoOrMoreExpressions() {
		ExpressionTerm<Integer> sut = new Multiplication(getExpressions());
		ExpressionTerm<Integer> expected = new Multiplication(getExpressions());
		Assertions.assertEquals(expected, sut);
	}
	
	@Test
	void multiplicatorIsAbleToEvaluateAllOfItsExpressions() {
		ExpressionTerm<Integer> sut = new Multiplication(getExpressions());
		Assertions.assertEquals(-6, sut.evaluate());
	}
	
	List<ExpressionTerm<Integer>> getExpressions() {
		return Arrays.asList(new Constant(3), new Constant(2), new Minus(new Constant(4), new Constant(5)));
	}
}
