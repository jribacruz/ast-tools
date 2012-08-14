package ast.commons.observer;

import java.util.Set;

import ast.commons.model.Annotation;


public interface ASTClassObserver {
	void update(String className, String superClassName, Set<Annotation> annotations);
}
