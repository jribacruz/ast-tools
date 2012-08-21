package ast.tools.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;

import ast.tools.context.ASTContext;
import ast.tools.core.ASTPredicate;
import ast.tools.core.ASTProcessor;
import ast.tools.model.Annotation;
import ast.tools.model.impl.AnnotationImpl;

public class ASTUtils {
	public static ICompilationUnit find(ICompilationUnit[] units, ASTPredicate predicate) {
		for (ICompilationUnit unit : units) {
			ASTProcessor processor = new ASTProcessor(unit);
			ASTContext context = processor.process();
			return context != null && predicate.apply(context) ? unit : null;
		}
		return null;
	}

	public static ICompilationUnit[] filter(ICompilationUnit[] units, ASTPredicate predicate) {
		List<ICompilationUnit> unitList = new ArrayList<ICompilationUnit>();
		for (ICompilationUnit unit : units) {
			ASTProcessor processor = new ASTProcessor(unit);
			ASTContext context = processor.process();
			if (context != null && predicate.apply(context)) {
				unitList.add(unit);
			}
		}
		return unitList.toArray(new ICompilationUnit[] {});
	}

	public static boolean containsAnnotation(Set<Annotation> annotations, String name) {
		return annotations.contains(new AnnotationImpl(name));
	}

}
