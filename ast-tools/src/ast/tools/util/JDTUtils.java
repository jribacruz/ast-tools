package ast.tools.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import ast.tools.context.ASTContext;
import ast.tools.core.ASTProcessor;

import com.google.common.base.Predicate;

public class JDTUtils {
	public static ICompilationUnit find(ICompilationUnit[] units, Predicate<ASTContext> predicate) {
		for (ICompilationUnit unit : units) {
			ASTProcessor processor = new ASTProcessor(unit);
			ASTContext context = processor.visit();
			return context != null && predicate.apply(context) ? unit : null;
		}
		return null;
	}

	public static ICompilationUnit find(List<ICompilationUnit> units, Predicate<ASTContext> predicate) {
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

	public static List<ICompilationUnit> filter(List<ICompilationUnit> units, Predicate<ASTContext> predicate) {
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

	public static List<ICompilationUnit> getAllCompilationUnits(IJavaProject javaProject) {
		List<ICompilationUnit> units = new ArrayList<ICompilationUnit>();
		try {
			for (IPackageFragment fragment : javaProject.getPackageFragments()) {
				for (ICompilationUnit compilationUnit : fragment.getCompilationUnits()) {
					units.add(compilationUnit);
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return units;
	}

	public static ICompilationUnit getCompilationUnit(IJavaProject javaProject, String packageName, String compilationUnitName) {
		if (javaProject != null) {
			try {
				IType type = javaProject.findType(packageName, compilationUnitName);
				return type.getCompilationUnit();
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static IJavaProject getJavaProject(ISelection selection) {
		if (selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof ICompilationUnit) {
				ICompilationUnit unit = (ICompilationUnit) structuredSelection.getFirstElement();
				return unit.getJavaProject();
			} else if (structuredSelection.getFirstElement() instanceof IPackageFragment) {
				IPackageFragment packageFragment = (IPackageFragment) structuredSelection.getFirstElement();
				return packageFragment.getJavaProject();
			} else if (structuredSelection.getFirstElement() instanceof IPackageFragmentRoot) {
				IPackageFragmentRoot fragmentRoot = (IPackageFragmentRoot) structuredSelection.getFirstElement();
				return fragmentRoot.getJavaProject();
			} else if (structuredSelection.getFirstElement() instanceof IJavaProject) {
				IJavaProject javaProject = (IJavaProject) structuredSelection.getFirstElement();
				return javaProject;
			}
		}
		return null;
	}

	public static boolean isCompilationUnit(ISelection selection) {
		if(selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection= (IStructuredSelection) selection;
			return structuredSelection.getFirstElement() instanceof ICompilationUnit;
		}
		return false;
	}

	public static boolean isPackageFragment(ISelection selection) {
		if(selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection= (IStructuredSelection) selection;
			return structuredSelection.getFirstElement() instanceof IPackageFragment;
		}
		return false;
	}

	public static boolean isPackageFragmentRoot(ISelection selection) {
		if(selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection= (IStructuredSelection) selection;
			return structuredSelection.getFirstElement() instanceof IPackageFragmentRoot;
		}
		return false;
	}

	public static boolean isJavaProject(ISelection selection) {
		if(selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection= (IStructuredSelection) selection;
			return structuredSelection.getFirstElement() instanceof IJavaProject;
		}
		return false;
	}

}
