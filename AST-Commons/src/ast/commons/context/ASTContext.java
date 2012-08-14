package ast.commons.context;

import java.util.Set;

import ast.commons.model.Annotation;
import ast.commons.model.Attribute;

public interface ASTContext {
	boolean isInterface();

	String getClassName();

	String getSuperClassName();

	Set<Annotation> getAnnotations();

	Set<Attribute> getAttributes();
}
