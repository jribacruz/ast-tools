package ast.tools.internal.model.impl;

import ast.tools.model.TParameter;

public class TParameterImpl implements TParameter {

	private String name;
	private String type;
	private String genericType;

	public TParameterImpl(String name, String type, String genericType) {
		super();
		this.name = name;
		this.type = type;
		this.genericType = genericType;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genericType == null) ? 0 : genericType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		TParameterImpl other = (TParameterImpl) obj;
		if (genericType == null) {
			if (other.genericType != null) {
				return false;
			}
		} else if (!genericType.equals(other.genericType)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TParameterImpl [");
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
		}
		builder.append("]");
		return builder.toString();
	}

}
