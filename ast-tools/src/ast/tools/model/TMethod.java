package ast.tools.model;

import java.util.List;
import java.util.Set;

public interface TMethod {
	String getName();

	boolean isConstructor();

	Set<TParameter> getParameters();

	List<String> getReturnTypes();

	String getReturnGenericType();

	Set<TAnnotation> getAnnotations();

	Set<TModifier> getModifiers();
}
