package edu.pezzati.simplemath.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpressionMapTest {
	
	private ExpressionMap<Integer> sut;

	@BeforeEach
	void initEach() {
		sut = new ExpressionMap<>();
	}

	@Test
	void sutComesWithAnEmptyCollectionOfVariableNamesAndTerms() {
		Assertions.assertTrue(sut.getVariablesMap().isEmpty());
	}
	
	/**
	 * when there is no match between sut's dictionary and requested variable, sut returns null.
	 */
	@Test
	void sutReturnsNullIfThereIsNoMatchBetweenItsDictionaryAndRequestedVariableName() {
		Assertions.assertNull(sut.getExpression("V"));
	}
	
	/**
	 * when there is match between sut's dictionary and requested variable, sut returns proper {@link Expression}.
	 */
	@Test
	void whenThereIsMatchBetweenSutDictionaryAndRequestedVariableNameSutReturnsProperExpression() {
		Expression<Integer> exp = new Expression<>();
		sut.getVariablesMap().put("X", exp);
		Assertions.assertEquals(exp, sut.getExpression("X"));
	}
}
