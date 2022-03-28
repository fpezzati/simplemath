package edu.pezzati.simplemath.app;

public interface SimpleMathVisitable<V extends Number> {

	void accept(SimpleMathVisitor<V> v);
}
