package ast.tools.helper;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ImportDeclaration;

public class ImportDeclarationHelper {

	private ImportDeclaration declaration;

	public ImportDeclarationHelper(AST ast, String... names) {
		super();
		this.declaration = ast.newImportDeclaration();
		declaration.setName(ast.newName(names));
	}

	public ImportDeclaration getDeclaration() {
		return declaration;
	}

}
