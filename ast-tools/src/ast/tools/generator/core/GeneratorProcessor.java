package ast.tools.generator.core;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.MessageDialog;

import plugin.commons.logger.Log;
import plugin.commons.util.ProjectUtils;
import ast.tools.core.ASTProcessor;
import ast.tools.generator.internal.function.FieldFunction;
import ast.tools.generator.internal.function.ImportFunction;
import ast.tools.generator.model.CompilationUnit;
import ast.tools.generator.model.Generator;
import ast.tools.generator.utils.GeneratorUtils;

public class GeneratorProcessor {
	private String genfile;
	private Generator generator;
	private IJavaProject javaProject;
	public static Log log = new Log("AST Tools");

	public GeneratorProcessor(IJavaProject javaProject, String genfile) {
		super();
		this.genfile = genfile;
		this.javaProject = javaProject;
		this.init();
	}

	private void init() {
		try {
			log.info("Iniciando geração do arquivo " + genfile);
			JAXBContext context = JAXBContext.newInstance(Generator.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			this.generator = (Generator) unmarshaller.unmarshal(new FileReader(genfile));
		} catch (JAXBException e) {
			MessageDialog.openError(null, "Erro na Geração", "Erro ao processar o arquivo gerador");
		} catch (FileNotFoundException e) {
			MessageDialog.openError(null, "Erro na Geração", "O arquivo " + genfile + " não foi encontrado");
		}

	}

	public void generate() {
		if (this.generator != null) {
			for (CompilationUnit unitModel : this.generator.getCompilationUnits()) {
				String compilationUnitName = unitModel.getName();
				String compilationUnitPackage = unitModel.getPackageName();
				ICompilationUnit compilationUnit = ProjectUtils.getCompilationUnit(javaProject, compilationUnitPackage,
						compilationUnitName);
				if (compilationUnit != null) {
					log.info("Atualizando unidade de compilação: " + compilationUnit.getElementName());
					ASTProcessor processor = new ASTProcessor(compilationUnit);
					GeneratorUtils.forAllDo(unitModel.getImports(), processor, new ImportFunction());
					GeneratorUtils.forAllDo(unitModel.getFields(), processor, new FieldFunction());
					processor.commit();
					log.info("Unidade de compilação atualizada com sucesso: " + compilationUnit.getElementName());

				} else {
					log.error("Unidade de compilação " + compilationUnitPackage + "." + compilationUnitName
							+ " não encontrada");
				}
			}
		}
	}
}
