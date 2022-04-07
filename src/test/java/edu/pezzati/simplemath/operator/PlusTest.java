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
		List<ExpressionTerm<Integer>> exp = Arrays.asList(new Constant<Integer>(1));
		Assertions.assertThrows(SimpleMathError.class, ()->{
			new Plus<Integer>(exp);
		});
	}
	
	@Test
	void plusEvaluatesAllItsExpressions() {
		List<ExpressionTerm<Integer>> exps = Arrays.asList(
				new Constant<Integer>(1),
				new Constant<Integer>(2),
				new Constant<Integer>(3),
				new Plus<Integer>(Arrays.asList(new Constant<Integer>(4), new Constant<Integer>(15))));
		Plus<Integer> sut = new Plus<>(exps);
		Assertions.assertEquals(new Constant<Integer>(1), sut.getTerms().get(0));
		Assertions.assertEquals(new Constant<Integer>(2), sut.getTerms().get(1));
		Assertions.assertEquals(new Constant<Integer>(3), sut.getTerms().get(2));
		Assertions.assertEquals(new Plus<Integer>(Arrays.asList(new Constant<Integer>(4), new Constant<Integer>(15))), sut.getTerms().get(3));
	}
}
