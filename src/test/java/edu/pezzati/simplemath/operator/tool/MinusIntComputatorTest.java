package edu.pezzati.simplemath.operator.tool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;

class MinusIntComputatorTest {
	
	private MinusComputator<ExpressionTerm<Integer>> sut;

	@BeforeEach
	void initEach() {
		sut = new MinusIntComputator();
	}

	@Test
	void whenATermIsNullSutRaisesANPE() {
		Assertions.assertThrows(NullPointerException.class, ()->{
			sut.compute(new Constant<Integer>(10), null);
		});
	}
	
	@Test
	void sutSubtractsRightTermToLeftTerm() {
		Assertions.assertEquals(5, sut.compute(new Constant<Integer>(10), new Constant<Integer>(5)).evaluate());
	}
}
