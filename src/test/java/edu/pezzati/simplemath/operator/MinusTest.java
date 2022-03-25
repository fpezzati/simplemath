package edu.pezzati.simplemath.operator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;

class MinusTest {

	@Test
	void minusStoresExactlyTwoExpressions() {
		ExpressionTerm<Integer> leftOp = new Constant(5);
		ExpressionTerm<Integer> rightOp = new Constant(6);
		ExpressionTerm<Integer> sut = new Minus(leftOp, rightOp);
		Assertions.assertEquals(sut, new Minus(new Constant(5), new Constant(6)));
	}
}
