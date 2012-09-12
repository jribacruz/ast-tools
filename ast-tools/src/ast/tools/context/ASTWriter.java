package ast.tools.context;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;

public interface ASTWriter {
	public void write(CompilationUnit unit, AST ast);
}
