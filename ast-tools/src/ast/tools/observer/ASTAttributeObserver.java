package ast.tools.observer;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TModifier;

public interface ASTAttributeObserver {
	void update(String name, List<String> types, String genericType, Set<TModifier> modifiers, Set<TAnnotation> annotations);
}
