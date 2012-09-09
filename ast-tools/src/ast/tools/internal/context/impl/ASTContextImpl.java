package ast.tools.internal.context.impl;

import java.util.List;
import java.util.Set;

import ast.tools.context.ASTContext;
import ast.tools.model.TAnnotation;
import ast.tools.model.TField;
import ast.tools.model.TImport;
import ast.tools.model.TMethod;

public class ASTContextImpl implements ASTContext {

	private boolean isInterface;
	private String className;
	private Set<TAnnotation> annotations;
	private Set<TField> fields;
	private Set<TMethod> methods;
	private Set<TImport> imports;
	private Set<String> interfaces;
	private String packageName;
	private String superClassName;
	private List<String> genericTypeArguments;
	private List<String> superClassGenericTypeArguments;

	public ASTContextImpl() {
		super();
	}

	public ASTContextImpl(boolean isInterface, String className, Set<TAnnotation> annotations, Set<TField> attributes,
			Set<TMethod> methods, Set<TImport> imports, Set<String> interfaces, String packageName,
			String superClassName, List<String> genericTypeArguments, List<String> superClassGenericTypeArguments) {
		super();
		this.isInterface = isInterface;
		this.className = className;
		this.annotations = annotations;
		this.fields = attributes;
		this.methods = methods;
		this.imports = imports;
		this.interfaces = interfaces;
		this.packageName = packageName;
		this.superClassName = superClassName;
		this.genericTypeArguments = genericTypeArguments;
		this.superClassGenericTypeArguments = superClassGenericTypeArguments;
	}

	@Override
	public boolean isInterface() {
		return this.isInterface;
	}

	@Override
	public String getClassName() {
		return this.className;
	}

	@Override
	public Set<TAnnotation> getAnnotations() {
		return this.annotations;
	}

	@Override
	public Set<TField> getFields() {
		return this.fields;
	}

	@Override
	public Set<TMethod> getMethods() {
		return this.methods;
	}

	@Override
	public Set<TImport> getImports() {
		return this.imports;
	}

	@Override
	public Set<String> getInterfaces() {
		return this.interfaces;
	}

	@Override
	public String getPackageName() {
		return this.packageName;
	}

	@Override
	public List<String> getGenericTypeArguments() {
		return this.genericTypeArguments;
	}

	@Override
	public String getSuperClassName() {
		return this.superClassName;
	}

	@Override
	public List<String> getSuperClassGenericTypeArguments() {
		return this.superClassGenericTypeArguments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ASTContextImpl [isInterface=");
		builder.append(isInterface);
		builder.append(", ");
		if (className != null) {
			builder.append("className=");
			builder.append(className);
			builder.append(", ");
		}
		if (annotations != null) {
			builder.append("annotations=");
			builder.append(annotations);
			builder.append(", ");
		}
		if (fields != null) {
			builder.append("attributes=");
			builder.append(fields);
			builder.append(", ");
		}
		if (methods != null) {
			builder.append("methods=");
			builder.append(methods);
			builder.append(", ");
		}
		if (imports != null) {
			builder.append("imports=");
			builder.append(imports);
			builder.append(", ");
		}
		if (interfaces != null) {
			builder.append("interfaces=");
			builder.append(interfaces);
			builder.append(", ");
		}
		if (packageName != null) {
			builder.append("packageName=");
			builder.append(packageName);
			builder.append(", ");
		}
		if (superClassName != null) {
			builder.append("superClassName=");
			builder.append(superClassName);
			builder.append(", ");
		}
		if (genericTypeArguments != null) {
			builder.append("genericTypeArguments=");
			builder.append(genericTypeArguments);
			builder.append(", ");
		}
		if (superClassGenericTypeArguments != null) {
			builder.append("superClassGenericTypeArguments=");
			builder.append(superClassGenericTypeArguments);
		}
		builder.append("]");
		return builder.toString();
	}

}
