package edu.pezzati.simplemath.operator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.operand.Constant;

class AbsTest {
	
	@Test
	void sutCanStoreAnExpressionTerm() {
		Abs<Integer> abs = new Abs<>(new Constant<>(10));
		Assertions.assertEquals(new Constant<Integer>(10), abs.getTerm());
	}
}
