package edu.pezzati.simplemath.app;

import java.util.Set;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.tool.FindsVariables;
import edu.pezzati.simplemath.app.tool.FindsVariablesInExpression;

public class Expression {
	
	private ExpressionTerm<? extends Number> root;
	private CheckCircularDependencies ccD;
	private FindsVariables checkVariables;
	
	public Expression() {
		checkVariables = new FindsVariablesInExpression<>();
	}
	
	public Number calc(ExpressionMap map) {
		if(ccD != null && ccD.check(map)) {
			// TODO do computations
		} else {
			// TODO there is a circular dependency
		}
		return null;
	}
	
	public Set<String> getReferencedSymbols() {
		return checkVariables.getVariableNames();
	}
	
	public void setExpression(ExpressionTerm<?> exp) {
		root = exp;
		root.accept(checkVariables);
	}
	
	public void setCircularDependenciesCheck(CheckCircularDependencies ccD) {
		this.ccD = ccD;
	}

	public void setCheckVariables(FindsVariables checkVariables) {
		this.checkVariables = checkVariables;
	}
}
