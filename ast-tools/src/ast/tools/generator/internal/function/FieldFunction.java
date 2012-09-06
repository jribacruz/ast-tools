package ast.tools.generator.internal.function;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.core.dom.AST;

import ast.tools.context.ASTWriter;
import ast.tools.generator.core.GeneratorProcessor;
import ast.tools.generator.model.CreateField;
import ast.tools.helper.CompilationUnitHelper;
import ast.tools.helper.FieldDeclarationHelper;
import ast.tools.model.TModifier;
import ast.tools.util.ASTUtils;

import com.google.common.base.Function;

public class FieldFunction implements Function<CreateField, ASTWriter> {

	@Override
	public ASTWriter apply(CreateField createField) {

		final String genericType = createField.getGenericType();
		final String[] types = createField.getTypes();
		final String name = createField.getName();
		final String[] modifiers = createField.getModifiers();

		return new ASTWriter() {

			@Override
			public void write(CompilationUnitHelper unitHelper, AST ast) {
				if (!StringUtils.isEmpty(name) && modifiers.length > 0 && types.length > 0) {
					GeneratorProcessor.log.info("Gerando field: " + name);
					Set<TModifier> modifierSet = ASTUtils.getModifiers(modifiers);
					FieldDeclarationHelper helper = new FieldDeclarationHelper(ast, name, modifierSet);
					if (!StringUtils.isEmpty(genericType) && types.length > 1) {
						helper.setType(genericType, types);
					} else if (types.length == 1 && ASTUtils.isPrimitiveType(types[0])) {
						helper.setType(ASTUtils.getPrimitiveType(types[0]));
					} else {
						helper.setType(types[0]);
					}
					unitHelper.addFieldDeclaration(helper.getFieldDeclaration());

				}
			}
		};
	}

}
