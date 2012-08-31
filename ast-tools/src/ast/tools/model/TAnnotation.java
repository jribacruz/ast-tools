package ast.tools.model;

public interface TAnnotation {
	String getName();

	String getString(String key);

	Integer getInteger(String key);

	Long getLong(String key);

	Class<?> getKlass(String key);

	String[] getStringArray(String key);

	Integer[] getIntegerArray(String key);

	Long[] getLongArray(String key);

	TAnnotation getAnnotation(String key);
}
