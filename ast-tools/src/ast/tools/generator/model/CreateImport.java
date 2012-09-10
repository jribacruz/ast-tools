package ast.tools.generator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.core.dom.AST;

import ast.tools.context.ASTWriter;
import ast.tools.generator.context.GeneratorContext;
import ast.tools.generator.core.IGeneratorElement;
import ast.tools.helper.CompilationUnitHelper;
import ast.tools.helper.ImportDeclarationHelper;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateImport implements IGeneratorElement {

	@XmlAttribute
	private String name;

	public String[] getNames() {
		return StringUtils.split(GeneratorContext.replaceTokens(name), ".");
	}

	@Override
	public ASTWriter apply() {
		return new ASTWriter() {

			@Override
			public void write(CompilationUnitHelper unitHelper, AST ast) {
				ImportDeclarationHelper helper = new ImportDeclarationHelper(ast, getNames());
				unitHelper.addImport(helper.getDeclaration());
			}
		};
	}

}
