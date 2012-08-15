package ast.commons.core;

import ast.commons.context.ASTContext;

public interface ASTPredicate {
	public boolean apply(ASTContext context);
}
