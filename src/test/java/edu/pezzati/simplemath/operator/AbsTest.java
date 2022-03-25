package edu.pezzati.simplemath.operator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;

class AbsTest {

	@Test
	void sutStoresAnExpression() {
		ExpressionTerm<Integer> expected = new Constant(0);
		ExpressionTerm<Integer> sut = new Abs(expected);
		Assertions.assertEquals(expected.evaluate(), sut.evaluate());
	}
	
	@Test
	void sutEvaluatesHerExpressionAsExpressionAbsoluteValue() {
		ExpressionTerm<Integer> expected = new Constant(-10);
		ExpressionTerm<Integer> sut = new Abs(expected);
		Assertions.assertEquals(10, sut.evaluate());
	}
}
