package ast.tools.internal.model.impl;

import ast.tools.model.TImport;

public class TImportImpl implements TImport {

	private String name;

	public TImportImpl(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
