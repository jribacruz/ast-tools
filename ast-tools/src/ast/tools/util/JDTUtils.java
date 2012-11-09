package ast.tools.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import ast.tools.context.ASTContext;
import ast.tools.core.ASTProcessor;
import ast.tools.internal.model.impl.TAnnotationImpl;
import ast.tools.model.TClass;

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
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
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
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
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
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
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
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
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
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
		}
		return units;
	}

	public static ICompilationUnit getCompilationUnit(IJavaProject javaProject, String packageName, String compilationUnitName) {
		if (javaProject != null) {
			try {
				IType type = javaProject.findType(packageName, compilationUnitName);
				return type.getCompilationUnit();
			} catch (JavaModelException e) {
				MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
			}
		}
		return null;
	}

	public static ICompilationUnit getCompilationUnit(ISelection selection) {
		return (ICompilationUnit) (isCompilationUnit(selection) ? ((IStructuredSelection) selection).getFirstElement() : null);
	}

	public static IPackageFragment getPackageFragment(ISelection selection) {
		if (selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof ICompilationUnit) {
				ICompilationUnit unit = (ICompilationUnit) structuredSelection.getFirstElement();
				return getType(unit).getPackageFragment();
			} else if (structuredSelection.getFirstElement() instanceof IPackageFragment) {
				IPackageFragment packageFragment = (IPackageFragment) structuredSelection.getFirstElement();
				return packageFragment;
			}
		}
		return null;
	}

	public static IPackageFragmentRoot getPackageFragmentRoot(ISelection selection) {
		if (selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof ICompilationUnit) {
				ICompilationUnit unit = (ICompilationUnit) structuredSelection.getFirstElement();
				return (IPackageFragmentRoot) getType(unit).getPackageFragment().getParent();
			} else if (structuredSelection.getFirstElement() instanceof IPackageFragment) {
				IPackageFragment packageFragment = (IPackageFragment) structuredSelection.getFirstElement();
				return (IPackageFragmentRoot) packageFragment.getParent();
			} else if (structuredSelection.getFirstElement() instanceof IPackageFragmentRoot) {
				IPackageFragmentRoot fragmentRoot = (IPackageFragmentRoot) structuredSelection.getFirstElement();
				return fragmentRoot;
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
		if (selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			return structuredSelection.getFirstElement() instanceof ICompilationUnit;
		}
		return false;
	}

	public static boolean isPackageFragment(ISelection selection) {
		if (selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			return structuredSelection.getFirstElement() instanceof IPackageFragment;
		}
		return false;
	}

	public static boolean isPackageFragmentRoot(ISelection selection) {
		if (selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			return structuredSelection.getFirstElement() instanceof IPackageFragmentRoot;
		}
		return false;
	}

	public static boolean isJavaProject(ISelection selection) {
		if (selection != null && !selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			return structuredSelection.getFirstElement() instanceof IJavaProject;
		}
		return false;
	}

	public static ICompilationUnit createCompilationUnit(IPackageFragment fragment, String name, String source) {
		try {
			name = name.endsWith(".java") ? name : name.concat(".java");
			return fragment.createCompilationUnit(name, source, false, null);
		} catch (JavaModelException e) {
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
		}

		return null;
	}

	public static IPackageFragment createPackageFragment(IPackageFragmentRoot root, String source) {
		try {
			return root.createPackageFragment(source, true, null);
		} catch (JavaModelException e) {
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
		}
		return null;
	}

	public static IImportDeclaration createImport(ICompilationUnit unit, String source) {
		try {
			return getType(unit).getCompilationUnit().createImport(source, null, null);
		} catch (JavaModelException e) {
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
		}

		return null;
	}

	public static IField createField(ICompilationUnit unit, String source) {
		try {
			return getType(unit).createField(source, null, false, null);
		} catch (JavaModelException e) {
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
		}

		return null;
	}

	public static IMethod createMethod(ICompilationUnit unit, String source) {
		try {
			return getType(unit).createMethod(source, null, false, null);
		} catch (JavaModelException e) {
			MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
		}

		return null;
	}

	public static boolean createFile(IProject project, String path, String name, String extention, String source) {
		IFolder folder = project.getFolder(path);
		if (folder.exists()) {
			IFile file = folder.getFile(name.concat(".").concat(extention));
			InputStream stream = new ByteArrayInputStream(source.getBytes());
			try {
				file.create(stream, false, null);
				return true;
			} catch (CoreException e) {
				MessageDialog.openError(null, "Erro", e.getLocalizedMessage());
			}
		}
		return false;
	}

	public static String getCompilationUnitName(ICompilationUnit unit) {
		return unit.getElementName().replace(".java", "");
	}

	public static IType getType(ICompilationUnit unit) {
		return unit.getType(getCompilationUnitName(unit));
	}

	public static boolean isDomain(ICompilationUnit unit) {
		TClass tClass = TUtils.getTClass(unit);
		if (tClass != null) {
			if (tClass.getAnnotations().contains(new TAnnotationImpl("Entity"))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isBC(ICompilationUnit unit) {
		TClass tClass = TUtils.getTClass(unit);
		if (tClass != null) {
			if (tClass.getAnnotations().contains(new TAnnotationImpl("BusinessController"))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDAO(ICompilationUnit unit) {
		TClass tClass = TUtils.getTClass(unit);
		if (tClass != null) {
			if (tClass.getAnnotations().contains(new TAnnotationImpl("PersistenceController"))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEditMB(ICompilationUnit unit) {
		TClass tClass = TUtils.getTClass(unit);
		if (tClass != null) {
			if (tClass.getAnnotations().contains(new TAnnotationImpl("ViewController"))
					&& tClass.getSuperClassName().equals("AbstractEditPageBean")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFolder(ISelection selection) {
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		return structuredSelection.getFirstElement() instanceof IFolder ? true : false;
	}

	public static boolean isFile(ISelection selection) {
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		return structuredSelection.getFirstElement() instanceof IFile ? true : false;
	}

	public static boolean isListMB(ICompilationUnit unit) {
		TClass tClass = TUtils.getTClass(unit);
		if (tClass != null) {
			if (tClass.getAnnotations().contains(new TAnnotationImpl("ViewController"))
					&& tClass.getSuperClassName().equals("AbstractListPageBean")) {
				return true;
			}
		}
		return false;
	}

	public static IProject getProject(ISelection selection) {
		IJavaProject javaProject = JDTUtils.getJavaProject(selection);
		if (javaProject != null) {
			return javaProject.getProject();
		}
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if (structuredSelection instanceof IFolder) {
			IFolder folder = (IFolder) structuredSelection.getFirstElement();
			return folder.getProject();
		} else if (structuredSelection instanceof IFile) {
			IFile file = (IFile) structuredSelection.getFirstElement();
			return file.getProject();
		}
		return null;
	}

}
