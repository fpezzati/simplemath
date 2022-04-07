package edu.pezzati.simplemath.operand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;

class VariableTest {

	@Test
	void variableStoresAStringAsMainIdentitifer() {
		ExpressionTerm<Integer> sut = new Variable<Integer>("X");
		Assertions.assertEquals("X", ((Variable<Integer>)sut).getName());
	}
}
