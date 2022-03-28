package edu.pezzati.simplemath.operator;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operator.tool.MinusComputator;

class MinusTest {

	@Test
	void sutReliesOnComputatorToEvaluateExpression() {
		MinusComputator<ExpressionTerm<Integer>> computator = Mockito.mock(MinusComputator.class);
		Mockito.when(computator.compute(ArgumentMatchers.any(ExpressionTerm.class), ArgumentMatchers.any(ExpressionTerm.class))).thenReturn(new Constant<Integer>(8));
		ExpressionTerm<Integer> sut = new Minus<Integer>(new Constant<Integer>(10), new Constant<Integer>(2), computator);
		sut.evaluate();
		Mockito.verify(computator).compute(ArgumentMatchers.eq(new Constant<Integer>(10)), ArgumentMatchers.eq(new Constant<Integer>(2)));
	}
}
