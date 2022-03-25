package edu.pezzati.simplemath.operator;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.error.SimpleMathError;
import edu.pezzati.simplemath.operand.Constant;

class PlusTest {

	@Test
	void plusCannotHoldOneExpressionOnly() {
		List<ExpressionTerm<Integer>> exp = Arrays.asList(new Constant(1));
		Assertions.assertThrows(SimpleMathError.class, ()->{
			new Plus(exp);
		});
	}
	
	@Test
	void plusEvaluatesAllItsExpressions() {
		List<ExpressionTerm<Integer>> exps = Arrays.asList(
				new Constant(1),
				new Constant(2),
				new Constant(3),
				new Plus(Arrays.asList(new Constant(4), new Constant(15))));
		ExpressionTerm<Integer> sut = new Plus(exps);
		Assertions.assertEquals(25, sut.evaluate());
	}
}
