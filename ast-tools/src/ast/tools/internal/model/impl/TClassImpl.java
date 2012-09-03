package ast.tools.internal.model.impl;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;
import ast.tools.model.TClass;
import ast.tools.model.TImport;
import ast.tools.model.TMethod;

public class TClassImpl implements TClass {

	private String name;
	private String packageName;
	private Set<TImport> imports;
	private Set<TAnnotation> annotations;
	private Set<String> interfaces;
	private Set<TAttribute> attributes;
	private Set<TMethod> methods;
	private List<String> genericTypeArguments;
	private String superClassName;
	private List<String> superClassGenericTypeArguments;

	public TClassImpl() {
		super();
	}

	public TClassImpl(String name, String packageName, Set<TImport> imports, Set<TAnnotation> annotations,
			Set<String> interfaces, Set<TAttribute> attributes, Set<TMethod> methods, List<String> genericTypeArguments,
			String superClassName, List<String> superClassGenericTypeArguments) {
		super();
		this.name = name;
		this.packageName = packageName;
		this.imports = imports;
		this.annotations = annotations;
		this.interfaces = interfaces;
		this.attributes = attributes;
		this.methods = methods;
		this.genericTypeArguments = genericTypeArguments;
		this.superClassName = superClassName;
		this.superClassGenericTypeArguments = superClassGenericTypeArguments;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPackageName() {
		return this.packageName;
	}

	@Override
	public Set<String> getInterfaces() {
		return this.interfaces;
	}

	@Override
	public Set<TAnnotation> getAnnotations() {
		return this.annotations;
	}

	@Override
	public Set<TAttribute> getAttributes() {
		return this.attributes;
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
		builder.append("TClassImpl [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (packageName != null) {
			builder.append("packageName=");
			builder.append(packageName);
			builder.append(", ");
		}
		if (interfaces != null) {
			builder.append("interfaces=");
			builder.append(interfaces);
			builder.append(", ");
		}
		if (annotations != null) {
			builder.append("annotations=");
			builder.append(annotations);
			builder.append(", ");
		}
		if (attributes != null) {
			builder.append("attributes=");
			builder.append(attributes);
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
		if (genericTypeArguments != null) {
			builder.append("genericTypeArguments=");
			builder.append(genericTypeArguments);
			builder.append(", ");
		}
		if (superClassName != null) {
			builder.append("superClassName=");
			builder.append(superClassName);
			builder.append(", ");
		}
		if (superClassGenericTypeArguments != null) {
			builder.append("superClassGeneritTypeArguments=");
			builder.append(superClassGenericTypeArguments);
		}
		builder.append("]");
		return builder.toString();
	}

}
