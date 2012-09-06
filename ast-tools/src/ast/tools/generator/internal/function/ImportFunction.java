package ast.tools.generator.internal.function;

import org.eclipse.jdt.core.dom.AST;

import ast.tools.context.ASTWriter;
import ast.tools.generator.core.GeneratorProcessor;
import ast.tools.generator.model.CreateImport;
import ast.tools.helper.CompilationUnitHelper;
import ast.tools.helper.ImportDeclarationHelper;

import com.google.common.base.Function;

public class ImportFunction implements Function<CreateImport, ASTWriter> {

	@Override
	public ASTWriter apply(CreateImport createImport) {

		final String[] importName = createImport.getNames();

		return new ASTWriter() {

			@Override
			public void write(CompilationUnitHelper unitHelper, AST ast) {
				GeneratorProcessor.log.info("Gerando import: " + importName);
				ImportDeclarationHelper helper = new ImportDeclarationHelper(ast, importName);
				unitHelper.addImport(helper.getDeclaration());
			}
		};
	}

}
