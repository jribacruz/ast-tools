package ast.tools.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import ast.tools.context.ASTContext;
import ast.tools.core.ASTProcessor;
import ast.tools.model.TClass;

import com.google.common.base.Predicate;

public class ASTUtils {
	public static ICompilationUnit find(ICompilationUnit[] units, Predicate<ASTContext> predicate) {
		for (ICompilationUnit unit : units) {
			ASTProcessor processor = new ASTProcessor(unit);
			ASTContext context = processor.visit();
			return context != null && predicate.apply(context) ? unit : null;
		}
		return null;
	}

	public static TClass findTClass(ICompilationUnit[] units, Predicate<ASTContext> predicate) {
		ICompilationUnit unit = find(units, predicate);
		return unit != null ? getTClass(unit) : null;
	}

	public static ICompilationUnit[] filter(ICompilationUnit[] units, Predicate<ASTContext> predicate) {
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

	public static TClass getTClass(ICompilationUnit iunit) {
		ASTProcessor processor = new ASTProcessor(iunit);
		processor.visit();
		return processor.getTClass();
	}

}
