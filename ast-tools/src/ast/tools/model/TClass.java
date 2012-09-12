package ast.tools.model;

import java.util.List;
import java.util.Set;

public interface TClass {

	String getName();

	String getPackageName();

	List<String> getGenericTypeArguments();

	String getSuperClassName();

	List<String> getSuperClassGenericTypeArguments();

	Set<String> getInterfaces();

	Set<TAnnotation> getAnnotations();

	Set<TField> getFields();

	Set<TMethod> getMethods();

	Set<TImport> getImports();

	List<TTag> getTags();
}
