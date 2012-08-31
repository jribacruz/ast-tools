package ast.tools.model;

import java.util.List;
import java.util.Set;

public interface TMethod {
	String getName();

	List<TParameter> getParameters();

	String getReturnType();

	Set<TAnnotation> getAnnotations();

	boolean isProtected();

	boolean isPublic();

	boolean isPrivate();

	boolean isFinal();

	boolean isStatic();

	boolean isAbstract();

	boolean isSynchronized();
}
