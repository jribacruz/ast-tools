package ast.tools.internal.context.impl;

import java.util.Set;

import ast.tools.context.ASTContext;
import ast.tools.model.Annotation;
import ast.tools.model.Attribute;

public class ASTContextImpl implements ASTContext {

	private boolean isInterface;
	private String className;
	private String superClassName;
	private Set<Annotation> annotations;
	private Set<Attribute> attributes;

	public ASTContextImpl() {
		super();
	}

	public ASTContextImpl(boolean isInterface, String className, String superClassName, Set<Annotation> annotations,
			Set<Attribute> attributes) {
		super();
		this.isInterface = isInterface;
		this.className = className;
		this.superClassName = superClassName;
		this.annotations = annotations;
		this.attributes = attributes;
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
	public Set<Annotation> getAnnotations() {
		return this.annotations;
	}

	@Override
	public Set<Attribute> getAttributes() {
		return this.attributes;
	}

}
