package ast.tools.internal.model.impl;

import java.util.ArrayList;

import ast.tools.model.TAnnotation;

import com.google.common.collect.Multimap;

public class TAnnotationImpl implements TAnnotation {

	private String name;

	private Multimap<String, Object> value;

	public TAnnotationImpl() {
		super();
	}

	public TAnnotationImpl(String name, Multimap<String, Object> value) {
		super();
		this.name = name;
		this.value = value;
	}

	public TAnnotationImpl(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getString(String key) {
		return (String) (this.value.containsKey(key) && this.value.size() > 0 ? new ArrayList<Object>(this.value.get(key))
				.get(0) : new String());
	}

	@Override
	public Integer getInteger(String key) {
		return (Integer) (this.value.containsKey(key) && this.value.size() > 0 ? new ArrayList<Object>(this.value.get(key))
				.get(0) : new Integer(0));
	}

	@Override
	public Long getLong(String key) {
		return (Long) (this.value.containsKey(key) && this.value.size() > 0 ? new ArrayList<Object>(this.value.get(key))
				.get(0) : new Long(0L));
	}

	@Override
	public Class<?> getKlass(String key) {
		return null;
	}

	@Override
	public String[] getStringArray(String key) {
		return (this.value.containsKey(key) && this.value.size() > 0 ? new ArrayList<Object>(this.value.get(key))
				.toArray(new String[] {}) : new String[] {});
	}

	@Override
	public Integer[] getIntegerArray(String key) {
		return (this.value.containsKey(key) && this.value.size() > 0 ? new ArrayList<Object>(this.value.get(key))
				.toArray(new Integer[] {}) : new Integer[] {});
	}

	@Override
	public Long[] getLongArray(String key) {
		return (this.value.containsKey(key) && this.value.size() > 0 ? new ArrayList<Object>(this.value.get(key))
				.toArray(new Long[] {}) : new Long[] {});
	}

	@Override
	public TAnnotation getAnnotation(String key) {
		return (TAnnotation) (this.value.containsKey(key) && this.value.size() > 0 ? new ArrayList<Object>(
				this.value.get(key)).get(0) : new TAnnotationImpl());
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
		TAnnotationImpl other = (TAnnotationImpl) obj;
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
		builder.append("Annotation [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (value != null) {
			builder.append("value=");
			builder.append(value);
		}
		builder.append("]");
		return builder.toString();
	}

}
