package ast.tools.internal.model.impl;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;
import ast.tools.model.TModifier;

public class TAttributeImpl implements TAttribute {

	private String name;
	private String type;
	private String genericType;
	private Set<TAnnotation> annotations;
	private Set<TModifier> modifiers;
	private List<String> types;

	public TAttributeImpl(String name, String type, String genericType, Set<TAnnotation> annotations,
			Set<TModifier> modifiers, List<String> types) {
		super();
		this.name = name;
		this.type = type;
		this.genericType = genericType;
		this.annotations = annotations;
		this.modifiers = modifiers;
		this.types = types;
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
		TAttributeImpl other = (TAttributeImpl) obj;
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
		builder.append("Attribute [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
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
		}
		builder.append("]");
		return builder.toString();
	}

}
