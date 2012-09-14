package ast.tools.internal.predicate;

import ast.tools.model.TImport;

import com.google.common.base.Predicate;

public class TImportPredicate implements Predicate<TImport> {

	private String name;

	public TImportPredicate(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean apply(TImport importItem) {
		return importItem.getName().equals(name);
	}

}
