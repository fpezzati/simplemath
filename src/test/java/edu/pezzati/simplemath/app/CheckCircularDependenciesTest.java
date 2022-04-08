package edu.pezzati.simplemath.app;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;

import edu.pezzati.simplemath.operand.Variable;

class CheckCircularDependenciesTest {

	private CheckCircularDependencies sut;
	
	@BeforeEach
	void initEach() {
		sut = new CheckCircularDependenciesByKhanAlgorithm();
	}

	/**
	 * when sut receives a null {@link ExpressionMap} object it returns a NPE.
	 */
	@Test
	void whenSutReceivesANullMapItReturnsANPE() {
		ExpressionMap<Integer> map = null;
		Assertions.assertThrows(NullPointerException.class, ()->{
			sut.check(map);
		});
	}
	
	/**
	 * x = (1 + y)<br>
	 * y = abs(1 - 3)
	 */
	
	@Test
	void whenSutReceivesAnEmptyMapItReturnsTrue() {
		ExpressionMap<Integer> map = new ExpressionMap<>();
		Assertions.assertTrue(sut.check(map));
	}
	
	/**
	 * when sut receives a {@link ExpressionMap} with no circular dependency it returns true.
	 */
	@Test
	void whenSutReceivesAMapWithNoCircularDependencyItReturnsTrue() {
		ExpressionMap<Integer> map = getExpressionMapWithNoCircularDependency();
		Assertions.assertTrue(sut.check(map));
	}
	
	@Test
	void whenSutReceivesAMapWithNoCircularDependencyItIsAbleToTellInWhichOrderApplyVariableSubstitution() {
		ExpressionMap<Integer> map = getExpressionMapWithNoCircularDependency();
		sut.check(map);
		Assertions.assertEquals(Arrays.asList("Y", "X", "Z"), sut.sortedElements());
	}

	private ExpressionMap<Integer> getExpressionMapWithNoCircularDependency() {
		return getExpressionMap("X=>Z", "Y=>X,Z", "Z");
	}
	
	@SuppressWarnings("unchecked")
	private Expression<Integer> getExpression(String...variables) {
		Expression<Integer> exp = Mockito.mock(Expression.class);
		Mockito.when(exp.getReferencedSymbols()).thenReturn(Sets.newSet(variables));
		return exp;
	}
	
	private ExpressionMap<Integer> getExpressionMap(String...nodeAndEdges) {
		ExpressionMap<Integer> map = new ExpressionMap<>();
		for(String nodeAndEdge : nodeAndEdges) {
			String[] vars = nodeAndEdge.split("=>");
			if(vars.length > 1) {
				map.getVariablesMap().put(vars[0].trim(), getExpression(vars[1].replaceAll(" ", "").split(",")));
			} else {
				map.getVariablesMap().put(vars[0].trim(), getExpression());
			}
		}
		return map;
	}
	
	/**
	 * when sut receives a {@link ExpressionMap} with circular dependency it returns false.
	 */
	@ParameterizedTest(name = "{index} => map={0}, expected={1}")
	@MethodSource("mapsProvider")
	void checkExpressionMapCircularDependencies(ExpressionMap<Integer> map, boolean expected) {
		Assertions.assertEquals(expected, sut.check(map));
	}
	
	private static Stream<Arguments> mapsProvider() {
		CheckCircularDependenciesTest t = new CheckCircularDependenciesTest();
		return Stream.of(
				Arguments.of(t.getExpressionMap("X=>Y", "Y", "Z=>Y"), true),
				Arguments.of(t.getExpressionMap("X=>Y", "Y=>Z, O", "Z=>B", "O", "B"), true),
				Arguments.of(t.getExpressionMap("X", "Y"), true),
				Arguments.of(t.getExpressionMap("X=>X", "Y=>Z"), false),
				Arguments.of(t.getExpressionMap("X=>Y", "Y=>Z", "Z=>A, B", "A=>Y", "B"), false)
				);
	}
	
	@Test
	void checkCircularDependencyOnARealExpressionMap() {
		Assertions.assertFalse(sut.check(getMapWithCircularDependency()));
	}
	
	private ExpressionMap<Integer> getMapWithCircularDependency() {
		ExpressionMap<Integer> expMap = new ExpressionMap<>();
		Expression<Integer> expX = new Expression<>();
		expX.setExpression(new Variable<>("Y"));
		Expression<Integer> expY = new Expression<>();
		expY.setExpression(new Variable<>("Z"));
		Expression<Integer> expZ = new Expression<>();
		expZ.setExpression(new Variable<>("X"));
		expMap.getVariablesMap().put("X", expX);
		expMap.getVariablesMap().put("Y", expY);
		expMap.getVariablesMap().put("Z", expZ);
		return expMap;
	}
}
