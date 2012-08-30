package ast.tools.core;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import ast.tools.context.ASTContext;
import ast.tools.context.ASTWriter;
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

	public void write(ASTWriter writer) {
		this.unit.recordModifications();
		writer.write(this.unit, this.unit.getAST());
		ITextFileBufferManager bufferManager = FileBuffers.getTextFileBufferManager();
		IPath path = this.unit.getJavaElement().getPath();
		try {
			bufferManager.connect(path, LocationKind.IFILE, null);
			ITextFileBuffer textFileBuffer = bufferManager.getTextFileBuffer(path, LocationKind.IFILE);
			IDocument document = textFileBuffer.getDocument();
			TextEdit edit = this.unit.rewrite(document, null);
			edit.apply(document);
			textFileBuffer.commit(null, false);
			bufferManager.disconnect(path, LocationKind.IFILE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (MalformedTreeException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
