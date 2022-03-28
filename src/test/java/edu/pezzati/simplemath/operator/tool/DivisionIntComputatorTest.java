package edu.pezzati.simplemath.operator.tool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;

class DivisionIntComputatorTest {
	
	private DivisionComputator<ExpressionTerm<Integer>> sut;

	@BeforeEach
	void initEach() {
		sut = new DivisionIntComputator();
	}

	@Test
	void whenOneTermIsNullSutRaisesANPE() {
		Assertions.assertThrows(NullPointerException.class, ()->{
			sut.compute(new Constant<Integer>(10), null);
		});
	}
	
	@Test
	void whenAnExceptionOccursSutSimplyBubblesThat() {
		Assertions.assertThrows(ArithmeticException.class, ()->{
			sut.compute(new Constant<Integer>(10), new Constant<Integer>(0));
		});
	}
	
	@Test
	void whenBothTermsAreSolidSutComputesDivision() {
		Assertions.assertEquals(2, sut.compute(new Constant<Integer>(10), new Constant<Integer>(5)).evaluate());
	}
	
	@Test
	void becauseSutHandlesIntegersResultIsRounded() {
		Assertions.assertEquals(3, sut.compute(new Constant<Integer>(10), new Constant<Integer>(3)).evaluate());
	}
}
