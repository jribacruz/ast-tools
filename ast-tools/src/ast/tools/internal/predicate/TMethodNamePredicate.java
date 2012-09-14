package ast.tools.internal.predicate;

import ast.tools.model.TMethod;

import com.google.common.base.Predicate;

public class TMethodNamePredicate implements Predicate<TMethod> {

	private String name;

	public TMethodNamePredicate(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean apply(TMethod method) {
		return method.getName().equals(name);
	}

}
