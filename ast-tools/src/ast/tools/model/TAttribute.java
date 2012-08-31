package ast.tools.model;

import java.util.Set;

public interface TAttribute {
	String getName();

	String getType();

	String getGenericType();

	Set<TAnnotation> getAnnotations();

	Set<TModifier> getModifiers();
}
