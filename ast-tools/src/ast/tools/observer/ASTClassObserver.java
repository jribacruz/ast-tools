package ast.tools.observer;

import java.util.Set;

import ast.tools.model.Annotation;


public interface ASTClassObserver {
	void update(String className, String superClassName, Set<Annotation> annotations);
}
