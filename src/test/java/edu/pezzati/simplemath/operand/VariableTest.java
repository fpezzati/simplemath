package edu.pezzati.simplemath.operand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.error.NotValuableVariable;

class VariableTest {

	@Test
	void variableStoresAStringAsMainIdentitifer() {
		ExpressionTerm<Integer> sut = new Variable<Integer>("X");
		Assertions.assertEquals("X", ((Variable)sut).getName());
	}
	
	@Test
	void variableCanLoadAnExpressionInASecondTime() {
		ExpressionTerm<Integer> sut = new Variable<Integer>("X");
		((Variable) sut).setExpression(new Constant(10));
		ExpressionTerm<Integer> expected = new Variable("X");
		((Variable) expected).setExpression(new Constant(10));
		Assertions.assertEquals(expected, sut);
	}
	
	@Test
	void variableRaisesAnExceptionWhileEvaluatingNoExpression() {
		ExpressionTerm<Integer> sut = new Variable<Integer>("X");
		Assertions.assertThrows(NotValuableVariable.class, ()->{
			sut.evaluate();
		});
	}
	
	@Test
	void variableEvaluatesItsExpression() {
		Variable<Integer> sut = new Variable<>("X");
		sut.setExpression(new Constant(10));
		Assertions.assertEquals(10, sut.evaluate());
	}
}
