package edu.pezzati.simplemath.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.tool.EvaluatorIntegerTerm;
import edu.pezzati.simplemath.error.CircularDependencyError;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

class ExpressionTest {

	private Expression<Integer> sut;
	
	@BeforeEach
	void initEach() {
		sut = new Expression<>();
		sut.setCircularDependenciesCheck(new CheckCircularDependenciesByKhanAlgorithm());
		sut.setEvaluator(new EvaluatorIntegerTerm());
	}
	
	@Test
	void whenSutExpressionHasNoVariablesSutReturnsAnEmptySet() {
		ExpressionTerm<Integer> exp = expressionWithoutVariables();
		sut.setExpression(exp);
		Assertions.assertTrue(sut.getReferencedSymbols().isEmpty());
	}
	
	@Test
	void whenSutExpressionHasTwoVariablesSutReturnsThem() {
		ExpressionTerm<Integer> exp = expressionWithXAndYAsVariables();
		sut.setExpression(exp);
		Set<String> expected = new HashSet<String>(Arrays.asList("X", "Y"));
		Assertions.assertEquals(expected, sut.getReferencedSymbols());
	}
	
	@Test
	void whenSutEncounterACircularDependencyItRaisesAnError() {
		ExpressionTerm<Integer> exp = getSimpleExpressionWithVariables();
		sut.setExpression(exp);
		ExpressionMap<Integer> map = getMapWithCircularDependencies();
		Assertions.assertThrows(CircularDependencyError.class, ()->{
			sut.calc(map);
		});
	}
	
	@Test
	void utEvaluatesExpressionTermWhichVariablesDoesNotCausesCircularDependencies() {
		ExpressionTerm<Integer> exp = getComplexExpressionHavingNoVariables();
		sut.setExpression(exp);
		ExpressionMap<Integer> map = getMapWithCircularDependencies();
		Assertions.assertEquals(-45, sut.calc(map).intValue());
	}
	
	@Test
	void sutIsAbleToEvaluateComplexExpressionHavingNoVariables() {
		ExpressionTerm<Integer> exp = getComplexExpressionHavingNoVariables();
		sut.setExpression(exp);
		ExpressionMap<Integer> map = new ExpressionMap<>();
		Assertions.assertEquals(-45, sut.calc(map).intValue());
	}

	@Test
	void sutIsAbleToEvaluateComplexExpression() {
		ExpressionTerm<Integer> exp = getComplexExpressionWithVariables();
		sut.setExpression(exp);
		ExpressionMap<Integer> map = getMapWithNoCircularDependencies();
		Assertions.assertEquals(-45, sut.calc(map).intValue());
	}
	
	private ExpressionMap<Integer> getMapWithCircularDependencies() {
		ExpressionMap<Integer> map = new ExpressionMap<>();
		Expression<Integer> expression1 = new Expression<>();
		expression1.setExpression(new Plus<Integer>(Arrays.asList(new Constant<>(1), new Variable<>("Y"), new Variable<>("X"))));
		Expression<Integer> expression2 = new Expression<>();
		expression2.setExpression(new Abs<Integer>(new Minus<>(new Constant<>(1), new Constant<>(3))));
		map.getVariablesMap().put("X", expression1 );
		map.getVariablesMap().put("Y", expression2);
		return map;
	}

	private ExpressionMap<Integer> getMapWithNoCircularDependencies() {
		ExpressionMap<Integer> map = new ExpressionMap<>();
		Expression<Integer> expression1 = new Expression<>();
		expression1.setExpression(new Plus<Integer>(Arrays.asList(new Constant<>(1), new Variable<>("Y"))));
		Expression<Integer> expression2 = new Expression<>();
		expression2.setExpression(new Abs<Integer>(new Minus<>(new Constant<>(1), new Constant<>(3))));
		map.getVariablesMap().put("X", expression1 );
		map.getVariablesMap().put("Y", expression2);
		return map;
	}
	
	private ExpressionTerm<Integer> getSimpleExpressionWithVariables() {
		return new Plus<>(Arrays.asList(
				new Variable<>("X"),
				new Variable<>("Y"),
				new Constant<>(1)
				));
	}

	private Minus<Integer> getComplexExpressionWithVariables() {
		return new Minus<>(
				new Constant<>(3),
				new Multiplication<>(Arrays.asList(
						new Plus<>(Arrays.asList(
								new Constant<>(5),
								new Variable<>("X")
								)),
						new Plus<>(Arrays.asList(
								new Variable<>("X"),
								new Variable<>("Y"),
								new Constant<>(1)
								))
						))
				);
	}
	
	private Minus<Integer> getComplexExpressionHavingNoVariables() {
		return new Minus<>(
				new Constant<>(3),
				new Multiplication<>(Arrays.asList(
						new Plus<>(Arrays.asList(
								new Constant<>(5),
								new Plus<Integer>(Arrays.asList(
										new Constant<>(1), 
										new Abs<Integer>(new Minus<>(new Constant<>(1), new Constant<>(3)))))
								)),
						new Plus<>(Arrays.asList(
								new Plus<Integer>(Arrays.asList(
										new Constant<>(1), 
										new Abs<Integer>(new Minus<>(new Constant<>(1), new Constant<>(3))))),
								new Abs<Integer>(new Minus<>(new Constant<>(1), new Constant<>(3))),
								new Constant<>(1)
								))
						))
				);
	}

	private ExpressionTerm<Integer> expressionWithoutVariables() {
		return new Multiplication<Integer>(
				Arrays.asList(new Constant<>(5), new Constant<>(3), new Constant<>(128))
				);
	}
	
	private ExpressionTerm<Integer> expressionWithXAndYAsVariables() {
		return new Multiplication<Integer>(
				Arrays.asList(
						new Constant<>(5), 
						new Minus<>(
								new Variable<>("Y"),
								new Abs<>(new Constant<>(-1))), 
						new Variable<>("X"))
				);
	}
}
