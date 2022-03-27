package edu.pezzati.simplemath.app;

import edu.pezzati.simplemath.operand.Constant;
import edu.pezzati.simplemath.operand.Variable;
import edu.pezzati.simplemath.operator.Abs;
import edu.pezzati.simplemath.operator.Division;
import edu.pezzati.simplemath.operator.Minus;
import edu.pezzati.simplemath.operator.Multiplication;
import edu.pezzati.simplemath.operator.Plus;

public interface SimpleMathVisitor {

	void visit(Constant term);
	
	void visit(Variable term);
	
	void visit(Abs term);
	
	void visit(Division term);
	
	void visit(Minus term);
	
	void visit(Multiplication term);
	
	void visit(Plus term);
}
