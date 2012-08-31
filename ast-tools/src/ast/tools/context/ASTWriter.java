package ast.tools.context;

import org.eclipse.jdt.core.dom.AST;

import ast.tools.helper.CompilationUnitHelper;

public interface ASTWriter {
	public void write(CompilationUnitHelper unitHelper, AST ast);
}
