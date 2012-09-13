package ast.tools.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jface.dialogs.MessageDialog;

import ast.tools.context.ASTContext;
import ast.tools.core.ASTProcessor;
import ast.tools.model.TClass;
import ast.tools.model.TModifier;

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

	public static boolean isPrimitiveType(String type) {
		return getPrimitiveType(type) != null;
	}

	public static PrimitiveType.Code getPrimitiveType(String type) {
		if (type.equals("int")) {
			return PrimitiveType.INT;
		} else if (type.equals("float")) {
			return PrimitiveType.FLOAT;
		} else if (type.equals("double")) {
			return PrimitiveType.DOUBLE;
		} else if (type.equals("char")) {
			return PrimitiveType.CHAR;
		} else if (type.equals("boolean")) {
			return PrimitiveType.BOOLEAN;
		} else if (type.equals("byte")) {
			return PrimitiveType.BYTE;
		} else if (type.equals("long")) {
			return PrimitiveType.LONG;
		} else if (type.equals("short")) {
			return PrimitiveType.SHORT;
		} else if (type.equals("void")) {
			return PrimitiveType.VOID;
		}
		return null;
	}

	public static Set<TModifier> getModifiers(String... modifiers) {
		Set<TModifier> modifiersSet = new HashSet<TModifier>();
		for (String modifier : modifiers) {
			try {
				modifiersSet.add(TModifier.valueOf(modifier.toUpperCase()));
			} catch (IllegalArgumentException e) {
				MessageDialog.openError(null, "Erro", "O tipo " + modifier + " não exite");
			}
		}
		return modifiersSet;
	}

}
