package edu.pezzati.simplemath.operator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;

class DivisionTest {
	
	@Test
	void sutCanStoreTwoExpressionTermAsOperands() {
		Division<Integer> div = new Division<Integer>(new Constant<>(5), new Variable<>("X"));
		Assertions.assertEquals(new Constant<Integer>(5), div.getLeft());
		Assertions.assertEquals(new Variable<Integer>("X"), div.getRight());
	}
}
