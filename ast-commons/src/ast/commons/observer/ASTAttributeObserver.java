package ast.commons.observer;

import java.util.Set;

import ast.commons.model.Annotation;

public interface ASTAttributeObserver {
	void update(String name, String type, String genericType, Set<Annotation> annotations);
}
