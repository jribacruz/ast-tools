package ast.tools.core;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import ast.tools.context.ASTContext;
import ast.tools.context.impl.ASTContextImpl;
import ast.tools.observer.ASTObservable;
import ast.tools.visitor.ASTVisitor;

public class ASTProcessor extends ASTObservable {
	private ICompilationUnit iunit;
	private CompilationUnit unit;

	public ASTProcessor(ICompilationUnit iunit) {
		super();
		this.iunit = iunit;
		this.init();
	}

	private void init() {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(iunit);
		parser.setResolveBindings(true);
		this.unit = (CompilationUnit) parser.createAST(null);
	}

	public ASTContext process() {
		ASTContext context = new ASTContextImpl();
		if (unit != null) {
			ASTVisitor visitor = new ASTVisitor(this);
			unit.accept(visitor);
			context = visitor.getContext();
		}
		return context;
	}
}
