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

}
