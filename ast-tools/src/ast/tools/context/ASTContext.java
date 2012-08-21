package ast.tools.context;

import java.util.Set;

import ast.tools.model.Annotation;
import ast.tools.model.Attribute;

public interface ASTContext {
	boolean isInterface();

	String getClassName();

	String getSuperClassName();

	Set<Annotation> getAnnotations();

	Set<Attribute> getAttributes();
}
