package ast.tools.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import ast.tools.context.ASTContext;
import ast.tools.internal.context.impl.ASTContextImpl;
import ast.tools.internal.model.impl.TClassImpl;
import ast.tools.internal.visitor.ASTVisitor;
import ast.tools.model.TClass;
import ast.tools.observable.ASTObservable;

public class ASTProcessor extends ASTObservable {
	private ICompilationUnit iunit;
	private CompilationUnit unit;
	private TClass tClass;
	private boolean isRecordModifications;

	public ASTProcessor(ICompilationUnit iunit) {
		super();
		this.iunit = iunit;
		this.init();
	}

	public ASTProcessor(File file) {
		super();
		try {
			this.init(FileUtils.readFileToString(file).toCharArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ASTProcessor(String source) {
		super();
		this.init(source.toCharArray());
	}

	private void init() {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(iunit);
		parser.setResolveBindings(true);
		this.unit = (CompilationUnit) parser.createAST(null);
	}

	private void init(char[] source) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(source);
		parser.setResolveBindings(true);
		this.unit = (CompilationUnit) parser.createAST(null);
	}

	public ASTContext visit() {
		ASTContext context = new ASTContextImpl();
		if (unit != null) {
			ASTVisitor visitor = new ASTVisitor(this);
			unit.accept(visitor);
			context = visitor.getContext();
			tClass = visitor.getTClass();
		}
		return context;
	}

	public TClass getTClass() {
		return this.tClass != null ? this.tClass : new TClassImpl();
	}
}
