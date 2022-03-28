package edu.pezzati.simplemath.operator;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operator.tool.AbsComputator;

class AbsTest {

	@Test
	void sutReliesOnComputatorToEvaluateExpression() {
		ExpressionTerm<Integer> expected = new Constant<Integer>(-10);
		AbsComputator<ExpressionTerm<Integer>> computator = Mockito.mock(AbsComputator.class);
		Mockito.when(computator.compute(ArgumentMatchers.any(ExpressionTerm.class))).thenReturn(new Constant<Integer>(-10));
		ExpressionTerm<Integer> sut = new Abs<Integer>(expected, computator);
		sut.evaluate();
		Mockito.verify(computator).compute(ArgumentMatchers.eq(new Constant<Integer>(-10)));
	}
}
