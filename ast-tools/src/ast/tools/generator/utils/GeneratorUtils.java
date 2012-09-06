package ast.tools.generator.utils;

import java.util.List;

import ast.tools.context.ASTWriter;
import ast.tools.core.ASTProcessor;

import com.google.common.base.Function;

public class GeneratorUtils {
	public static <T> void forAllDo(List<T> collection, ASTProcessor processor, Function<T, ASTWriter> function) {
		for (T t : collection) {
			processor.write(function.apply(t));
		}
	}
}
