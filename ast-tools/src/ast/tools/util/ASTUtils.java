package ast.tools.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;

import ast.tools.context.ASTContext;
import ast.tools.context.ASTWriter;
import ast.tools.core.ASTPredicate;
import ast.tools.core.ASTProcessor;
import ast.tools.internal.model.impl.TAnnotationImpl;
import ast.tools.model.TAnnotation;

public class ASTUtils {
	public static ICompilationUnit find(ICompilationUnit[] units, ASTPredicate predicate) {
		for (ICompilationUnit unit : units) {
			ASTProcessor processor = new ASTProcessor(unit);
			ASTContext context = processor.visit();
			return context != null && predicate.apply(context) ? unit : null;
		}
		return null;
	}

	public static ICompilationUnit[] filter(ICompilationUnit[] units, ASTPredicate predicate) {
		List<ICompilationUnit> unitList = new ArrayList<ICompilationUnit>();
		for (ICompilationUnit unit : units) {
			ASTProcessor processor = new ASTProcessor(unit);
			ASTContext context = processor.visit();
			if (context != null && predicate.apply(context)) {
				unitList.add(unit);
			}
		}
		return unitList.toArray(new ICompilationUnit[] {});
	}

	public static void forAllWrite(ICompilationUnit[] units, ASTPredicate predicate, ASTWriter writer) {
		ICompilationUnit[] filteredUnits = filter(units, predicate);
		for (ICompilationUnit unit : filteredUnits) {
			ASTProcessor processor = new ASTProcessor(unit);
			processor.write(writer);
			processor.commit();
		}
	}

	public static boolean containsAnnotation(Set<TAnnotation> annotations, String name) {
		return annotations.contains(new TAnnotationImpl(name));
	}

}
