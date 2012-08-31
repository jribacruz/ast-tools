package ast.tools.internal.model.impl;

import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;
import ast.tools.model.TClass;
import ast.tools.model.TImport;
import ast.tools.model.TMethod;

public class TClassImpl implements TClass {

	private String name;
	private String packageName;
	private TClass superClass;
	private Set<String> interfaces;
	private Set<TAnnotation> annotations;
	private Set<TAttribute> attributes;
	private Set<TMethod> methods;
	private Set<TImport> imports;

	private TClassImpl(String name, String packageName, TClass superClass, Set<String> interfaces,
			Set<TAnnotation> annotations, Set<TAttribute> attributes, Set<TMethod> methods, Set<TImport> imports) {
		super();
		this.name = name;
		this.packageName = packageName;
		this.superClass = superClass;
		this.interfaces = interfaces;
		this.annotations = annotations;
		this.attributes = attributes;
		this.methods = methods;
		this.imports = imports;
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

}
