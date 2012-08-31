package ast.tools.internal.model.impl;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TMethod;
import ast.tools.model.TModifier;
import ast.tools.model.TParameter;

public class TMethodImpl implements TMethod {

	private String name;
	private List<TParameter> parameters;
	private Set<TAnnotation> annotations;
	private Set<TModifier> modifiers;
	private List<String> returnTypes;
	private String returnGenericType;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<TParameter> getParameters() {
		return this.parameters;
	}

	@Override
	public Set<TAnnotation> getAnnotations() {
		return this.annotations;
	}

	@Override
	public Set<TModifier> getModifiers() {
		return modifiers;
	}

	@Override
	public List<String> getReturnTypes() {
		return this.returnTypes;
	}

	@Override
	public String getReturnGenericType() {
		return this.returnGenericType;
	}

}
