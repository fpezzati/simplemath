package edu.pezzati.simplemath.app;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class CheckCircularDependenciesByKhanAlgorithm implements CheckCircularDependencies {

	@Override
	public boolean check(ExpressionMap map) {
		List<Edge> mapEdges = new ArrayList<>();
		for (String variable : map.getVariablesMap().keySet()) {
			computeEdgesFrom(variable, map.getExpression(variable), mapEdges);
		}
		return checkForCycles(map.getVariablesMap().keySet(), new ArrayList<Edge>(mapEdges));
	}

	private void computeEdgesFrom(String variable, Expression expression, List<Edge> mapEdges) {
		for (String symbol : expression.getReferencedSymbols()) {
			mapEdges.add(new Edge(variable, symbol));
		}
	}

	/**
	 * A trivial implementation of Kahn's algorithm:
	 * 
	 * L ← Empty list that will contain the sorted elements <br>
	 * S ← Set of all nodes with no incoming edge <br>
	 * <br>
	 * while S is not empty do <br>
	 *   remove a node n from S <br>
	 *   add n to L <br>
	 *   for each node m with an edge e from n to m do <br>
	 *     remove edge e from the graph <br>
	 *     if m has no other incoming edges then <br>
	 *       insert m into S <br>
	 * <br>
	 * if graph has edges then <br>
	 *   return error (graph has at least one cycle) <br>
	 * else <br>
	 *   return L (a topologically sorted order) <br>
	 * 
	 * @param variables all vertex in the graph we are going to check
	 * @param mapEdges  all edges in the graph we are going to check
	 * @return true if no circular dependency is found, false otherwise
	 */
	private boolean checkForCycles(Set<String> variables, List<Edge> mapEdges) {
		// creating a root node, a node without incoming edges
		
		List<String> nodesWithoutIncomingEdges = new ArrayList<>(findNodesWithNoIncomingEdges(variables, mapEdges));
		List<String> sortedElements = new ArrayList<>();
		ListIterator<String> sortedNodesIterator = nodesWithoutIncomingEdges.listIterator();
		while (sortedNodesIterator.hasNext()) {
			String node = sortedNodesIterator.next();
			sortedElements.add(node);
			sortedNodesIterator.remove();
			ListIterator<Edge> edgesIterator = mapEdges.listIterator();
			while (edgesIterator.hasNext()) {
				Edge edge = edgesIterator.next();
				if (edge.left.equals(node)) {
					if(nodeHasNoOtherIncomingEdges(edge.right, edge, mapEdges)) {
						sortedNodesIterator.add(edge.right);
						sortedNodesIterator = nodesWithoutIncomingEdges.listIterator();
					}
					edgesIterator.remove();
				}
			}
		}
		return mapEdges.isEmpty();
	}
	
	private boolean nodeHasNoOtherIncomingEdges(String node, Edge edgeToIgnore, List<Edge> mapEdges) {
		boolean hasNoIncomingEdges = true;
		for(Edge edge : mapEdges) {
			if(!edge.equals(edgeToIgnore)) {
				hasNoIncomingEdges = hasNoIncomingEdges && !edge.right.equals(node);
			}
		}
		return hasNoIncomingEdges;
	}

	private boolean nodeHasNoIncomingEdges(String node, List<Edge> mapEdges) {
		boolean hasIncomingEdges = false;
		for(Edge edge : mapEdges) {
			hasIncomingEdges = hasIncomingEdges || edge.right.equals(node);
		}
		return hasIncomingEdges;
	}

	private List<String> findNodesWithNoIncomingEdges(Set<String> variables, List<Edge> mapEdges) {
		List<String> nodesWithoutIncomingEdges = new ArrayList<>();
		for(String variable : variables) {
			if(!nodeHasNoIncomingEdges(variable, mapEdges)) {
				nodesWithoutIncomingEdges.add(variable);
			}
		}
		return nodesWithoutIncomingEdges;
	}
}

class Edge {

	String left;
	String right;

	Edge(String left, String right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return String.format("%s=>%s", left, right);
	}
}
