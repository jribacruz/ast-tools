package ast.tools.internal.model.impl;

import java.util.List;

import ast.tools.model.TParameter;

public class TParameterImpl implements TParameter {

	private String name;
	private List<String> types;
	private String genericType;

	public TParameterImpl(String name, List<String> types, String genericType) {
		super();
		this.name = name;
		this.types = types;
		this.genericType = genericType;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<String> getTypes() {
		return this.types;
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
		result = prime * result + ((types == null) ? 0 : types.hashCode());
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
		if (types == null) {
			if (other.types != null) {
				return false;
			}
		} else if (!types.equals(other.types)) {
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
		if (types != null) {
			builder.append("type=");
			builder.append(types);
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
