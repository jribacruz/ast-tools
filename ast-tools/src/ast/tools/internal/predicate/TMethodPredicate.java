package ast.tools.internal.predicate;

import ast.tools.model.TMethod;

import com.google.common.base.Predicate;

public class TMethodPredicate implements Predicate<TMethod> {

	private String name;

	public TMethodPredicate(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean apply(TMethod method) {
		return method.getName().equals(name);
	}

}
