package ast.tools.internal.model.impl;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;
import ast.tools.model.TClass;
import ast.tools.model.TImport;
import ast.tools.model.TInterface;
import ast.tools.model.TMethod;

public class TClassImpl implements TClass {

	private String name;
	private String packageName;
	private TClass superClass;
	private Set<TInterface> interfaces;
	private Set<TAnnotation> annotations;
	private Set<TAttribute> attributes;
	private Set<TMethod> methods;
	private Set<TImport> imports;
	private List<String> genericTypeArguments;

	public TClassImpl(String name, String packageName, TClass superClass, Set<TInterface> interfaces,
			Set<TAnnotation> annotations, Set<TAttribute> attributes, Set<TMethod> methods, Set<TImport> imports,
			List<String> genericTypeArguments) {
		super();
		this.name = name;
		this.packageName = packageName;
		this.superClass = superClass;
		this.interfaces = interfaces;
		this.annotations = annotations;
		this.attributes = attributes;
		this.methods = methods;
		this.imports = imports;
		this.genericTypeArguments = genericTypeArguments;
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
	public TClass getSuperClass() {
		return this.superClass;
	}

	@Override
	public Set<TInterface> getInterfaces() {
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
		if (superClass != null) {
			builder.append("superClass=");
			builder.append(superClass);
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
		}
		builder.append("]");
		return builder.toString();
	}

}
