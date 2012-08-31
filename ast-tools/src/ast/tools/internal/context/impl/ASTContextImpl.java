package ast.tools.internal.context.impl;

import java.util.Set;

import ast.tools.context.ASTContext;
import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;
import ast.tools.model.TImport;
import ast.tools.model.TMethod;

public class ASTContextImpl implements ASTContext {

	private boolean isInterface;
	private String className;
	private String superClassName;
	private Set<TAnnotation> annotations;
	private Set<TAttribute> attributes;
	private Set<TMethod> methods;
	private Set<TImport> imports;
	private Set<String> interfaces;
	private String packageName;

	public ASTContextImpl() {
		super();
	}

	public ASTContextImpl(boolean isInterface, String className, String superClassName, Set<TAnnotation> annotations,
			Set<TAttribute> attributes, Set<TMethod> methods, Set<TImport> imports, Set<String> interfaces,
			String packageName) {
		super();
		this.isInterface = isInterface;
		this.className = className;
		this.superClassName = superClassName;
		this.annotations = annotations;
		this.attributes = attributes;
		this.methods = methods;
		this.imports = imports;
		this.interfaces = interfaces;
		this.packageName = packageName;
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
	public String getSuperClassName() {
		return this.superClassName;
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
	public Set<String> getInterfaces() {
		return this.interfaces;
	}

	@Override
	public String getPackageName() {
		return this.packageName;
	}

}
