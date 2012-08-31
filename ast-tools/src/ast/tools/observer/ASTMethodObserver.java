package ast.tools.observer;

import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TModifier;
import ast.tools.model.TParameter;

public interface ASTMethodObserver {
	void update(String name, String returnType, Set<TModifier> modifiers, Set<TParameter> parameters,
			Set<TAnnotation> annotations);
}
