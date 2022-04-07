package edu.pezzati.simplemath.app.tool;

import java.util.Set;

import edu.pezzati.simplemath.app.SimpleMathVisitor;

public interface FindsVariables<V extends Number> extends SimpleMathVisitor<V> {

	Set<String> getVariableNames();
}
