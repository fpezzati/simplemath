package edu.pezzati.simplemath.operator;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;

class DivisionTest {

	@Test
	void divisionStoresTwoExpressions() {
		ExpressionTerm<Integer> sut = new Division(new Constant(10), new Constant(2));
		ExpressionTerm<Integer> expected = new Division(new Constant(10), new Constant(2));
		Assertions.assertEquals(expected, sut);
	}
	
	@Test
	void divisionEvaluatesBothItsExpressions() {
		ExpressionTerm<Integer> sut = new Division(
				new Constant(33), 
				new Plus(Arrays.asList(new Constant(1), new Constant(0), new Constant(2)))
		);
		Assertions.assertEquals(11, sut.evaluate());
	}
}
