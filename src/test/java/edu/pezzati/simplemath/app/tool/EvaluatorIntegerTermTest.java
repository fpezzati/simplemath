package edu.pezzati.simplemath.app.tool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.simplemath.error.NotEvaluableExpressionTerm;
import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Minus;

class EvaluatorIntegerTermTest {

	private Evaluator<Integer> sut;
	
	@BeforeEach
	void initEach() {
		sut = new EvaluatorIntegerTerm();
	}
	
	@Test
	void sutCannotEvaluateExpressionsContainingVariables() {
		Minus<Integer> term = new Minus<>(new Variable<>("YXY"), new Constant<>(10));
		Assertions.assertThrows(NotEvaluableExpressionTerm.class, ()->{
			sut.evaluate(term);
		});
	}
}
