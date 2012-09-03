package ast.tools.helper;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class CompilationUnitHelper {
	private CompilationUnit unit;

	public CompilationUnitHelper(CompilationUnit unit) {
		super();
		this.unit = unit;
	}

	@SuppressWarnings("unchecked")
	public void addMethod(MethodDeclaration methodDeclaration) {
		getTypeDeclaration().bodyDeclarations().add(methodDeclaration);
	}

	@SuppressWarnings("unchecked")
	public void addImport(ImportDeclaration importDeclaration) {
		this.unit.imports().add(importDeclaration);
	}

	@SuppressWarnings("unchecked")
	public void addFieldDeclaration(FieldDeclaration fieldDeclaration) {
		getTypeDeclaration().bodyDeclarations().add(fieldDeclaration);
	}

	private TypeDeclaration getTypeDeclaration() {
		return (TypeDeclaration) this.unit.types().get(0);
	}

}
