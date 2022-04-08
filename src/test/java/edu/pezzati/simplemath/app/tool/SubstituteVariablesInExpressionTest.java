package edu.pezzati.simplemath.app.tool;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.error.SimpleMathError;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

class SubstituteVariablesInExpressionTest {
	
	private SubstituteVariables<Integer> sut;
	private ExpressionTerm<Integer> toCheckTerm;
	private String variableName;
	private ExpressionTerm<Integer> substitutionTerm;
	
	@BeforeEach
	void initEach() {
		toCheckTerm = new Variable<>("X");
		variableName = "X";
		substitutionTerm = new Constant<>(5);
		sut = new SubstituteVariablesInExpression<>();
	}
	
	/**
	 * when expressionterm is null sut raises a NPE
	 */
	@Test
	void substitutionTermIsMandatory() {
		substitutionTerm = null;
		Assertions.assertThrows(SimpleMathError.class, ()->{
			sut.substitute(toCheckTerm, variableName, substitutionTerm);
		});
	}
	
	/**
	 * when variable name is null sut raises a NPE
	 */
	@Test
	void VariableNameIsMandatory() {
		variableName = null;
		Assertions.assertThrows(SimpleMathError.class, ()->{
			sut.substitute(toCheckTerm, variableName, substitutionTerm);
		});
	}
	
	/**
	 * when expressionterm to be scanned is null sut raises a NPE
	 */
	@Test
	void toCheckExpressionTermIsMandatory() {
		toCheckTerm = null;
		Assertions.assertThrows(NullPointerException.class, ()->{
			sut.substitute(toCheckTerm, variableName, substitutionTerm);
		});
	}
	
	/**
	 * when variable name is not found sut does nothing but returns a copy object in any case.
	 */
	@Test
	void whenVariableNameIsntFoundSutSubstitutesNothing() {
		variableName = "Y";
		toCheckTerm = new Plus<Integer>(Arrays.asList(new Variable<Integer>("X"), new Constant<Integer>(5)));
		ExpressionTerm<Integer> expected = new Plus<Integer>(Arrays.asList(new Variable<Integer>("X"), new Constant<Integer>(5)));
		ExpressionTerm<Integer> actual = sut.substitute(toCheckTerm, variableName, substitutionTerm);
		checkTermsAreEqualsButDifferentObjects(expected, actual);
		
	}
	
	@Test
	void whenTermToCheckIsConstantSutSubstitutesNothing() {
		toCheckTerm = new Constant<Integer>(5);
		ExpressionTerm<Integer> expected = new Constant<>(5);
		ExpressionTerm<Integer> actual = sut.substitute(toCheckTerm, variableName, substitutionTerm);
		checkTermsAreEqualsButDifferentObjects(expected, actual);
	}
	
	@Test
	void whenTermToCheckIsMatchingVariableSutSubstituteIt() {
		toCheckTerm = new Variable<Integer>("X");
		ExpressionTerm<Integer> actual = sut.substitute(toCheckTerm, variableName, substitutionTerm);
		checkTermsAreTheSameObjects(substitutionTerm, actual);
	}
	
	@Test
	void whenTermToCheckIsTermContainingVariableSutSubstitutes() {
		toCheckTerm = new Plus<Integer>(Arrays.asList(new Variable<Integer>("X"), new Constant<Integer>(5)));
		ExpressionTerm<Integer> expected = new Plus<Integer>(Arrays.asList(new Constant<Integer>(5), new Constant<Integer>(5)));
		ExpressionTerm<Integer> actual = sut.substitute(toCheckTerm, variableName, substitutionTerm);
		checkTermsAreEqualsButDifferentObjects(expected, actual);
	}
	
	@Test
	void whenTermToCheckIsSomeComplexTermWithVariableSutSubstitutes() {
		toCheckTerm = new Minus<Integer>(
				new Multiplication<Integer>(
						Arrays.asList(new Variable<Integer>("X"), new Variable<Integer>("Y"), new Constant<Integer>(3))
						), 
				new Division<Integer>(new Variable<Integer>("X"), new Plus<Integer>(Arrays.asList(new Variable<Integer>("X"), new Constant<Integer>(3))))
			);
		ExpressionTerm<Integer> expected = new Minus<Integer>(
				new Multiplication<Integer>(
						Arrays.asList(new Constant<>(5), new Variable<Integer>("Y"), new Constant<Integer>(3))
						), 
				new Division<Integer>(new Constant<>(5), new Plus<Integer>(Arrays.asList(new Constant<>(5), new Constant<Integer>(3))))
			);
		ExpressionTerm<Integer> actual = sut.substitute(toCheckTerm, variableName, substitutionTerm);
		checkTermsAreEqualsButDifferentObjects(expected, actual);
	}
	
	@Test
	void sutSubstitutesInComplexTerm() {
		toCheckTerm = getComplexExpressionWithVariables();
		ExpressionTerm<Integer> partialResult = sut.substitute(toCheckTerm, "X", new Plus<Integer>(Arrays.asList(new Constant<>(1), new Variable<>("Y"))));
		ExpressionTerm<Integer> actual = sut.substitute(partialResult, "Y", new Abs<Integer>(new Minus<>(new Constant<>(1), new Constant<>(3))));
		Assertions.assertEquals(getComplexExpressionHavingNoVariables(), actual);
	}
	
	private void checkTermsAreEqualsButDifferentObjects(ExpressionTerm<Integer> expected,
			ExpressionTerm<Integer> actual) {
		Assertions.assertEquals(expected, actual);
		Assertions.assertFalse(expected == actual);
	}
	
	private void checkTermsAreTheSameObjects(ExpressionTerm<Integer> expected,
			ExpressionTerm<Integer> actual) {
		Assertions.assertTrue(expected == actual);
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
}
