package ast.tools.observer;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TInterface;

public interface ASTClassObserver {
	void update(String className, List<String> genericTypeArguments, String superClassName, List<String> superClassGenericTypeArguments, Set<TAnnotation> annotations,
			Set<TInterface> interfaces);
}
