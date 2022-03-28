package edu.pezzati.simplemath.operator;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.error.SimpleMathError;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operator.tool.PlusComputator;
import edu.pezzati.simplemath.operator.tool.PlusIntComputator;

class PlusTest {

	@Test
	void plusCannotHoldOneExpressionOnly() {
		PlusComputator<ExpressionTerm<Integer>> computator = Mockito.mock(PlusComputator.class);
		List<ExpressionTerm<Integer>> exp = Arrays.asList(new Constant<Integer>(1));
		Assertions.assertThrows(SimpleMathError.class, ()->{
			new Plus<Integer>(exp, computator);
		});
	}
	
	@Test
	void plusEvaluatesAllItsExpressions() {
		PlusComputator<ExpressionTerm<Integer>> computator = new PlusIntComputator();
		List<ExpressionTerm<Integer>> exps = Arrays.asList(
				new Constant<Integer>(1),
				new Constant<Integer>(2),
				new Constant<Integer>(3),
				new Plus<Integer>(Arrays.asList(new Constant<Integer>(4), new Constant<Integer>(15)), computator));
		ExpressionTerm<Integer> sut = new Plus<>(exps, computator);
		Assertions.assertEquals(25, sut.evaluate());
	}
}
