package ast.tools.observer;

import java.util.Set;

import ast.tools.model.Annotation;

public interface ASTAttributeObserver {
	void update(String name, String type, String genericType, Set<Annotation> annotations);
}
