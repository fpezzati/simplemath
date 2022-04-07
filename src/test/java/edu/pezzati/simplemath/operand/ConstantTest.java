package edu.pezzati.simplemath.operand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;

class ConstantTest {

	@Test
	void sutStoresAValue() {
		ExpressionTerm<Integer> sut = new Constant<Integer>(10);
		Assertions.assertEquals(10, ((Constant<Integer>)sut).getValue());
	}
}
