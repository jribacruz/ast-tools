package ast.tools.observer;

import java.util.Set;

import ast.tools.model.TAnnotation;

public interface ASTClassObserver {
	void update(String className, String superClassName, Set<TAnnotation> annotations, Set<String> interfaces);
}
