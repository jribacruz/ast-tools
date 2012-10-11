package ast.tools.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;

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

	public static ICompilationUnit find(IJavaProject javaProject, Predicate<ASTContext> predicate) {
		try {
			for (IPackageFragment fragment : javaProject.getPackageFragments()) {
				for (ICompilationUnit unit : fragment.getCompilationUnits()) {
					ASTProcessor processor = new ASTProcessor(unit);
					ASTContext context = processor.visit();
					return context != null && predicate.apply(context) ? unit : null;
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ICompilationUnit find(IPackageFragment fragment, Predicate<ASTContext> predicate) {
		try {
			for (ICompilationUnit unit : fragment.getCompilationUnits()) {
				ASTProcessor processor = new ASTProcessor(unit);
				ASTContext context = processor.visit();
				return context != null && predicate.apply(context) ? unit : null;
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ICompilationUnit> filter(ICompilationUnit[] units, Predicate<ASTContext> predicate) {
		List<ICompilationUnit> unitList = new ArrayList<ICompilationUnit>();
		for (ICompilationUnit unit : units) {
			ASTProcessor processor = new ASTProcessor(unit);
			ASTContext context = processor.visit();
			if (context != null && predicate.apply(context)) {
				unitList.add(unit);
			}
		}
		return unitList;
	}

	public static List<ICompilationUnit> filter(IJavaProject javaProject, Predicate<ASTContext> predicate) {
		List<ICompilationUnit> unitList = new ArrayList<ICompilationUnit>();
		try {
			for (IPackageFragment fragment : javaProject.getPackageFragments()) {
				for (ICompilationUnit unit : fragment.getCompilationUnits()) {
					ASTProcessor processor = new ASTProcessor(unit);
					ASTContext context = processor.visit();
					if (context != null && predicate.apply(context)) {
						unitList.add(unit);
					}
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return unitList;
	}

	public static List<ICompilationUnit> filter(IPackageFragment fragment, Predicate<ASTContext> predicate) {
		List<ICompilationUnit> unitList = new ArrayList<ICompilationUnit>();
		try {
			for (ICompilationUnit unit : fragment.getCompilationUnits()) {
				ASTProcessor processor = new ASTProcessor(unit);
				ASTContext context = processor.visit();
				if (context != null && predicate.apply(context)) {
					unitList.add(unit);
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return unitList;
	}

	public static TClass getTClass(ICompilationUnit iunit) {
		ASTProcessor processor = new ASTProcessor(iunit);
		processor.visit();
		return processor.getTClass();
	}

}
