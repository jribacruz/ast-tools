package ast.tools.observer;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TModifier;
import ast.tools.model.TParameter;

public interface ASTMethodObserver {
	void update(String name, List<String> returnTypes, String genericReturnType, Set<TModifier> modifiers,
			Set<TParameter> parameters, Set<TAnnotation> annotations);
}
