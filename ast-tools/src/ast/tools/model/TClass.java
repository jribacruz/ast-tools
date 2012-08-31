package ast.tools.model;

import java.util.Set;

public interface TClass {

	String getName();

	String getPackageName();

	TClass getSuperClass();

	Set<String> getInterfaces();

	Set<TAnnotation> getAnnotations();

	Set<TAttribute> getAttributes();

	Set<TMethod> getMethods();

	Set<TImport> getImports();
}
