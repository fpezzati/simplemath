package edu.pezzati.simplemath.app;

import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

public interface SimpleMathVisitor<V extends Number> {

	void visit(Constant<V> term);
	
	void visit(Variable<V> term);
	
	void visit(Abs<V> term);
	
	void visit(Division<V> term);
	
	void visit(Minus<V> term);
	
	void visit(Multiplication<V> term);
	
	void visit(Plus<V> term);
}
