package ast.tools.model;

import java.util.Set;

public interface TAttribute {
	String getName();

	String getType();

	String getGenericType();

	Set<TAnnotation> getAnnotations();

	boolean isProtected();

	boolean isPublic();

	boolean isPrivate();

	boolean isFinal();

	boolean isStatic();

	boolean isVolatile();

	boolean isTransient();
}
