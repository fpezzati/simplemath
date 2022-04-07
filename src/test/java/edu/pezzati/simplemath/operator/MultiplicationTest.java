package edu.pezzati.simplemath.operator;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.error.SimpleMathError;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;

class MultiplicationTest {
	
	@Test
	void sutCannotStoreLessThanTwoOperands() {
		Assertions.assertThrows(SimpleMathError.class, ()->{
			new Multiplication<Double>(Arrays.asList(new Constant<>(3D)));
		});
	}
	
	@Test
	void sutCanStoreTwoOrMoreOperands() {
		Multiplication<Double> mul = new Multiplication<Double>(
				Arrays.asList(new Constant<>(3D), new Variable<>("SOME"), new Constant<>(110.3))
				);
		Assertions.assertEquals(new Constant<>(3D), mul.getTerms().get(0));
		Assertions.assertEquals(new Variable<>("SOME"), mul.getTerms().get(1));
		Assertions.assertEquals(new Constant<>(110.3), mul.getTerms().get(2));
	}
}
