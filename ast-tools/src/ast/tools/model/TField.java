package ast.tools.model;

import java.util.List;
import java.util.Set;

public interface TField {
	String getName();

	List<String> getTypes();

	String getGenericType();

	Set<TAnnotation> getAnnotations();

	Set<TModifier> getModifiers();

	List<TTag> getTags();
}
