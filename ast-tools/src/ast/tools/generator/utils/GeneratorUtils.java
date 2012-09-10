package ast.tools.generator.utils;

import java.util.List;

import ast.tools.core.ASTProcessor;
import ast.tools.generator.core.IGeneratorElement;

public class GeneratorUtils {

	public static void forAllDo(List<IGeneratorElement> collection, ASTProcessor processor) {
		for (IGeneratorElement generatorElement : collection) {
			processor.write(generatorElement.apply());
		}
	}
}
