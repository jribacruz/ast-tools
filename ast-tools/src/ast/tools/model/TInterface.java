package ast.tools.model;

import java.util.Set;

public interface TInterface {

	String getName();

	String getPackageName();

	Set<TImport> getImports();

	TInterface getSuperInterface();

	Set<TField> getFields();

	Set<TMethod> getMethods();
}
