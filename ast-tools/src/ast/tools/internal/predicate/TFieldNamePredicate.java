package ast.tools.internal.predicate;

import ast.tools.model.TField;

import com.google.common.base.Predicate;

public class TFieldNamePredicate implements Predicate<TField> {

	private String name;

	public TFieldNamePredicate(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean apply(TField field) {
		return field.getName().equals(name);
	}

}
