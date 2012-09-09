package ast.tools.context;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TField;
import ast.tools.model.TImport;
import ast.tools.model.TMethod;

public interface ASTContext {
	boolean isInterface();

	String getClassName();

	String getPackageName();

	String getSuperClassName();

	List<String> getGenericTypeArguments();

	List<String> getSuperClassGenericTypeArguments();

	Set<String> getInterfaces();

	Set<TAnnotation> getAnnotations();

	Set<TField> getFields();

	Set<TMethod> getMethods();

	Set<TImport> getImports();
}
