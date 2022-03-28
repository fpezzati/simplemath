package edu.pezzati.simplemath.operator.tool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;

class AbsIntComputatorTest {
	
	private AbsComputator<ExpressionTerm<Integer>> sut;

	@BeforeEach
	void initEach() {
		sut = new AbsIntComputator();
	}

	@Test
	void whenInputIsNullSutThrowsNPE() {
		Assertions.assertThrows(NullPointerException.class, ()->{
			sut.compute(null);
		});
	}
	
	@Test
	void whenInputIsMinusThanZeroSutReturnsAbsoluteValue() {
		Assertions.assertEquals(10, sut.compute(new Constant<Integer>(-10)).evaluate());
	}
	
	@Test
	void whenInputIsGreaterThanZeroSutReturnsSameValue() {
		Assertions.assertEquals(10, sut.compute(new Constant<Integer>(10)).evaluate());
	}
}
