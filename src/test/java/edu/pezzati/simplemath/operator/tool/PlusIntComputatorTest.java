package edu.pezzati.simplemath.operator.tool;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;

class PlusIntComputatorTest {
	
	private PlusComputator<ExpressionTerm<Integer>> sut;

	@BeforeEach
	void initEach() {
		sut = new PlusIntComputator();
	}

	@Test
	void whenOneTermIsNullSutRaisesANPE() {
		Assertions.assertThrows(NullPointerException.class, ()->{
			sut.compute(Arrays.asList(new Constant<Integer>(10), null));
		});
	}
	
	@Test
	void whenAllTermsAreSolidSutComputesMultiplication() {
		Assertions.assertEquals(17, sut.compute(Arrays.asList(new Constant<Integer>(10), new Constant<Integer>(7))).evaluate());
	}
}
