package edu.pezzati.simplemath.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.pezzati.simplemath.ExpressionTerm;
import edu.pezzati.simplemath.app.tool.Evaluator;
import edu.pezzati.simplemath.app.tool.FindsVariables;
import edu.pezzati.simplemath.app.tool.FindsVariablesInExpression;
import edu.pezzati.simplemath.app.tool.SubstituteVariables;
import edu.pezzati.simplemath.app.tool.SubstituteVariablesInExpression;
import edu.pezzati.simplemath.error.CircularDependencyError;
import edu.pezzati.simplemath.error.UnmatchedVariableInMap;

public class Expression<V extends Number> {
	
	private ExpressionTerm<V> root;
	private CheckCircularDependencies ccD;
	private FindsVariables<V> checkVariables;
	private Evaluator<V> evaluator;
	
	public Expression() {
		checkVariables = new FindsVariablesInExpression<>();
	}
	
	public Number calc(ExpressionMap<V> map) {
		prepareExpressionToBeEvaluated(map);
		performSubstitution(map);
		return evaluator.evaluate(root);
	}
	
	private void prepareExpressionToBeEvaluated(ExpressionMap<V> map) {
		root.accept(checkVariables);
		boolean noCircularDependency = true;
		checkMatchesBetweenTermVariablesAndMapVariables(checkVariables, map);
		noCircularDependency = ccD.check(map);
		if(!noCircularDependency) {
			throw new CircularDependencyError();
		}
	}

	private void performSubstitution(ExpressionMap<V> map) {
		for(String symbol : ccD.sortedElements()) {
			SubstituteVariables<V> sub = new SubstituteVariablesInExpression<>();
			root = sub.substitute(root, symbol, map.getExpression(symbol).root);
		}
	}

	/**
	 * Check if variables in root term are a subset of map's ones, then remove unnecessary entries from map.
	 * @param checkVariables tool containing variables in root term.
	 * @param map the key - expression set.
	 */
	private void checkMatchesBetweenTermVariablesAndMapVariables(FindsVariables<V> checkVariables, ExpressionMap<V> map) {
		Map<String, Expression<V>> expressions = new HashMap<>();
		if(!map.getVariablesMap().keySet().containsAll(checkVariables.getVariableNames())) {
			throw new UnmatchedVariableInMap();
		}
		for(String variable : checkVariables.getVariableNames()) {
			if(map.getExpression(variable) != null) {
				expressions.put(variable, map.getExpression(variable));
			}
		}
		map.getVariablesMap().clear();
		map.getVariablesMap().putAll(expressions);
	}

	public Set<String> getReferencedSymbols() {
		root.accept(checkVariables);
		return checkVariables.getVariableNames();
	}
	
	public void setExpression(ExpressionTerm<V> exp) {
		root = exp;
	}
	
	public void setCircularDependenciesCheck(CheckCircularDependencies ccD) {
		this.ccD = ccD;
	}

	public void setCheckVariables(FindsVariables<V> checkVariables) {
		this.checkVariables = checkVariables;
	}

	public void setEvaluator(Evaluator<V> evaluator) {
		this.evaluator = evaluator;
	}
}
