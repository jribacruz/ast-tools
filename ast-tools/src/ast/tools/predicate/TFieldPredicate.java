package ast.tools.predicate;

import ast.tools.model.TField;

import com.google.common.base.Predicate;

public class TFieldPredicate implements Predicate<TField> {

	private String name;

	public TFieldPredicate(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean apply(TField field) {
		return field.getName().equals(name);
	}

}
