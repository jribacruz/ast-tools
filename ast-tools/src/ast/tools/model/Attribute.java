package ast.tools.model;

import java.util.Set;

public interface Attribute {
	String getName();

	String getType();

	String getGenericType();

	Set<Annotation> getAnnotations();
}
