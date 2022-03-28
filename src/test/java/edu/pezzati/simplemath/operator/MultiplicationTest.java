package edu.pezzati.simplemath.operator;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operator.tool.MultiplicationComputator;

class MultiplicationTest {

	@Test
	void sutReliesOnComputatorToEvaluateExpression() {
		MultiplicationComputator<ExpressionTerm<Integer>> computator = Mockito.mock(MultiplicationComputator.class);
		Mockito.when(computator.compute(ArgumentMatchers.any(List.class))).thenReturn(new Constant<Integer>(20));
		List<ExpressionTerm<Integer>> terms = Arrays.asList(new Constant<Integer>(10), new Constant<Integer>(2));
		ExpressionTerm<Integer> sut = new Multiplication<Integer>(terms , computator);
		sut.evaluate();
		Mockito.verify(computator).compute(ArgumentMatchers.eq(Arrays.asList(new Constant<Integer>(10), new Constant<Integer>(2))));
	}
}
