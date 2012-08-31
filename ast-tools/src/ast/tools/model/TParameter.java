package ast.tools.model;

import java.util.List;

public interface TParameter {
	String getName();

	List<String> getTypes();

	String getGenericType();
}
