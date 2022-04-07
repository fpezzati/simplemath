package edu.pezzati.simplemath.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;

class ExpressionTest {

	private Expression sut;
	
	@BeforeEach
	void initEach() {
		sut = new Expression();
	}
	
	@Test
	void whenSutExpressionHasNoVariablesSutReturnsAnEmptySet() {
		ExpressionTerm<?> exp = expressionWithoutVariables();
		sut.setExpression(exp);
		Assertions.assertTrue(sut.getReferencedSymbols().isEmpty());
	}
	
	@Test
	void whenSutExpressionHasTwoVariablesSutReturnsThem() {
		ExpressionTerm<?> exp = expressionWithXAndYAsVariables();
		sut.setExpression(exp);
		Set<String> expected = new HashSet<String>(Arrays.asList("X", "Y"));
		Assertions.assertEquals(expected, sut.getReferencedSymbols());
	}

	private ExpressionTerm<?> expressionWithoutVariables() {
		return new Multiplication<Number>(
				Arrays.asList(new Constant<Number>(5), new Constant<>(3), new Constant<>(128))
				);
	}
	
	private ExpressionTerm<?> expressionWithXAndYAsVariables() {
		return new Multiplication<Number>(
				Arrays.asList(
						new Constant<Number>(5), 
						new Minus<>(
								new Variable<>("Y"),
								new Abs<>(new Constant<>(-1))), 
						new Variable<>("X"))
				);
	}
}
