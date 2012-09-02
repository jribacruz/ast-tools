package ast.tools.model;

import java.util.List;
import java.util.Set;

public interface TClass {

	String getName();

	String getPackageName();

	List<String> getGenericTypeArguments();

	TClass getSuperClass();

	Set<TInterface> getInterfaces();

	Set<TAnnotation> getAnnotations();

	Set<TAttribute> getAttributes();

	Set<TMethod> getMethods();

	Set<TImport> getImports();
}
