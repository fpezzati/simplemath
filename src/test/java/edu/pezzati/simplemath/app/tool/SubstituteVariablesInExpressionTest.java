package edu.pezzati.simplemath.app.tool;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.Expression;
import edu.pezzati.simplemath.app.ExpressionMap;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
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
		Assertions.assertThrows(NullPointerException.class, ()->{
			sut.substitute(toCheckTerm, variableName, substitutionTerm);
		});
	}
	
	/**
	 * when variable name is null sut raises a NPE
	 */
	@Test
	void VariableNameIsMandatory() {
		variableName = null;
		Assertions.assertThrows(NullPointerException.class, ()->{
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
	void whenVariableNameIsntFoundSutDoesNothing() {
		variableName = null;
		ExpressionTerm<Integer> expected = new Constant<>(5);
		ExpressionTerm<Integer> actual = sut.substitute(toCheckTerm, variableName, substitutionTerm);
		Assertions.assertEquals(expected, actual);
		Assertions.assertFalse(expected == actual);
	}
	
	
	/**
	 * for every name hit variable is replaced with given expressionterm 
	 */

	

	private ExpressionMap getSingleVariableMap() {
		ExpressionMap expMap = new ExpressionMap();
		Expression exp = new Expression();
		exp.setExpression(new Plus<Integer>(Arrays.asList(new Constant<Integer>(3), new Constant<Integer>(1)), null));
		expMap.getVariablesMap().put("X", exp );
		return expMap;
	}

	private ExpressionMap getMapWithCircularDependency() {
		ExpressionMap expMap = new ExpressionMap();
		Expression expX = new Expression();
		expX.setExpression(new Variable<>("Y"));
		Expression expY = new Expression();
		expY.setExpression(new Variable<>("Z"));
		Expression expZ = new Expression();
		expZ.setExpression(new Variable<>("X"));
		expMap.getVariablesMap().put("X", expX);
		return expMap;
	}
}
