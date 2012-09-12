package ast.tools.internal.model.impl;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TField;
import ast.tools.model.TModifier;
import ast.tools.model.TTag;

public class TFieldImpl implements TField {

	private String name;
	private List<String> types;
	private String genericType;
	private Set<TAnnotation> annotations;
	private Set<TModifier> modifiers;
	private List<TTag> tags;

	public TFieldImpl(String name, List<String> types, String genericType, Set<TAnnotation> annotations,
			Set<TModifier> modifiers, List<TTag> tags) {
		super();
		this.name = name;
		this.types = types;
		this.genericType = genericType;
		this.annotations = annotations;
		this.modifiers = modifiers;
		this.tags = tags;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getGenericType() {
		return this.genericType;
	}

	@Override
	public List<String> getTypes() {
		return this.types;
	}

	@Override
	public Set<TAnnotation> getAnnotations() {
		return this.annotations;
	}

	@Override
	public Set<TModifier> getModifiers() {
		return this.modifiers;
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
		TFieldImpl other = (TFieldImpl) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TAttributeImpl [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (types != null) {
			builder.append("types=");
			builder.append(types);
			builder.append(", ");
		}
		if (genericType != null) {
			builder.append("genericType=");
			builder.append(genericType);
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
		}
		builder.append("]");
		return builder.toString();
	}

}
