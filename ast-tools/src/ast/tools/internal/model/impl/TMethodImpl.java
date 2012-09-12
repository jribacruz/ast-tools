package ast.tools.internal.model.impl;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TMethod;
import ast.tools.model.TModifier;
import ast.tools.model.TParameter;
import ast.tools.model.TTag;

public class TMethodImpl implements TMethod {

	private String name;
	private Set<TParameter> parameters;
	private Set<TAnnotation> annotations;
	private Set<TModifier> modifiers;
	private List<String> returnTypes;
	private String returnGenericType;
	private boolean constructor;
	private List<String> thrownExceptions;
	private List<TTag> tags;

	public TMethodImpl(String name, Set<TParameter> parameters, Set<TAnnotation> annotations, Set<TModifier> modifiers,
			List<String> returnTypes, String returnGenericType, boolean constructor, List<String> thrownExceptions,
			List<TTag> tags) {
		super();
		this.name = name;
		this.parameters = parameters;
		this.annotations = annotations;
		this.modifiers = modifiers;
		this.returnTypes = returnTypes;
		this.returnGenericType = returnGenericType;
		this.constructor = constructor;
		this.thrownExceptions = thrownExceptions;
		this.tags = tags;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Set<TParameter> getParameters() {
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

	@Override
	public boolean isConstructor() {
		return this.constructor;
	}

	@Override
	public List<String> getThrownExceptions() {
		return this.thrownExceptions;
	}

	@Override
	public List<TTag> getTags() {
		return this.tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TMethodImpl other = (TMethodImpl) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (parameters == null) {
			if (other.parameters != null) {
				return false;
			}
		} else if (!parameters.equals(other.parameters)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TMethodImpl [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (parameters != null) {
			builder.append("parameters=");
			builder.append(parameters);
			builder.append(", ");
		}
		if (annotations != null) {
			builder.append("annotations=");
			builder.append(annotations);
			builder.append(", ");
		}
		if (modifiers != null) {
			builder.append("modifiers=");
			builder.append(modifiers);
			builder.append(", ");
		}
		if (returnTypes != null) {
			builder.append("returnTypes=");
			builder.append(returnTypes);
			builder.append(", ");
		}
		if (returnGenericType != null) {
			builder.append("returnGenericType=");
			builder.append(returnGenericType);
			builder.append(", ");
		}
		builder.append("constructor=");
		builder.append(constructor);
		builder.append(", ");
		if (thrownExceptions != null) {
			builder.append("thrownExceptions=");
			builder.append(thrownExceptions);
		}
		builder.append("]");
		return builder.toString();
	}

}
