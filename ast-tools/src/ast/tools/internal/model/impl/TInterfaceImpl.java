package ast.tools.internal.model.impl;

import java.util.Set;

import ast.tools.model.TAttribute;
import ast.tools.model.TImport;
import ast.tools.model.TInterface;
import ast.tools.model.TMethod;

public class TInterfaceImpl implements TInterface {

	private String name;
	private TInterface superInterface;
	private Set<TAttribute> attributes;
	private Set<TMethod> methods;
	private String packageName;
	private Set<TImport> imports;

	private TInterfaceImpl(String name, TInterface superInterface, Set<TAttribute> attributes, Set<TMethod> methods,
			String packageName, Set<TImport> imports) {
		super();
		this.name = name;
		this.superInterface = superInterface;
		this.attributes = attributes;
		this.methods = methods;
		this.packageName = packageName;
		this.imports = imports;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public TInterface getSuperInterface() {
		return this.superInterface;
	}

	@Override
	public Set<TAttribute> getAttributes() {
		return this.attributes;
	}

	@Override
	public Set<TMethod> getMethods() {
		return this.methods;
	}

	@Override
	public String getPackageName() {
		return this.packageName;
	}

	@Override
	public Set<TImport> getImports() {
		return this.imports;
	}

}
