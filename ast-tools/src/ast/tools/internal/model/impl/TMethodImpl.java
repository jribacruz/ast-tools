package ast.tools.internal.model.impl;

import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TMethod;
import ast.tools.model.TParameter;

public class TMethodImpl implements TMethod {

	private String name;
	private List<TParameter> parameters;
	private String returnType;
	private Set<TAnnotation> annotations;
	private boolean _protected;
	private boolean _public;
	private boolean _private;
	private boolean _final;
	private boolean _static;
	private boolean _abstract;
	private boolean _synchronized;

	public TMethodImpl(String name, List<TParameter> parameters, String returnType, Set<TAnnotation> annotations,
			boolean _protected, boolean _public, boolean _private, boolean _final, boolean _static, boolean _abstract,
			boolean _synchronized) {
		super();
		this.name = name;
		this.parameters = parameters;
		this.returnType = returnType;
		this.annotations = annotations;
		this._protected = _protected;
		this._public = _public;
		this._private = _private;
		this._final = _final;
		this._static = _static;
		this._abstract = _abstract;
		this._synchronized = _synchronized;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<TParameter> getParameters() {
		return this.parameters;
	}

	@Override
	public String getReturnType() {
		return this.returnType;
	}

	@Override
	public Set<TAnnotation> getAnnotations() {
		return this.annotations;
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
	public boolean isAbstract() {
		return this._abstract;
	}

	@Override
	public boolean isSynchronized() {
		return this._synchronized;
	}

}
