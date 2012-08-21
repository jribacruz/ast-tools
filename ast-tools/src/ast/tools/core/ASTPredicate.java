package ast.tools.core;

import ast.tools.context.ASTContext;

public interface ASTPredicate {
	public boolean apply(ASTContext context);
}
