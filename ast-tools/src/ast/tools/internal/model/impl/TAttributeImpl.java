package ast.tools.internal.model.impl;

import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;

public class TAttributeImpl implements TAttribute {

	private String name;
	private String type;
	private String genericType;
	private Set<TAnnotation> annotations;
	boolean _protected;
	boolean _public;
	boolean _private;
	boolean _final;
	boolean _static;
	boolean _volatile;
	boolean _transient;

	public TAttributeImpl(String name, String type, String genericType, Set<TAnnotation> annotations, boolean _protected,
			boolean _public, boolean _private, boolean _final, boolean _static, boolean _volatile, boolean _transient) {
		super();
		this.name = name;
		this.type = type;
		this.genericType = genericType;
		this.annotations = annotations;
		this._protected = _protected;
		this._public = _public;
		this._private = _private;
		this._final = _final;
		this._static = _static;
		this._volatile = _volatile;
		this._transient = _transient;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getGenericType() {
		return this.genericType;
	}

	@Override
	public Set<TAnnotation> getAnnotations() {
		return this.annotations;
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
	public boolean isProtected() {
		return this._protected;
	}

	@Override
	public boolean isPublic() {
		return this._public;
	}

	@Override
	public boolean isPrivate() {
		return this._private;
	}

	@Override
	public boolean isFinal() {
		return this._final;
	}

	@Override
	public boolean isStatic() {
		return this._static;
	}

	@Override
	public boolean isVolatile() {
		return this._volatile;
	}

	@Override
	public boolean isTransient() {
		return this._transient;
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
