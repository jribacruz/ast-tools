package ast.tools.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.PrimitiveType;

import ast.tools.internal.model.impl.TAnnotationImpl;
import ast.tools.internal.model.impl.TImportImpl;
import ast.tools.model.TAnnotation;
import ast.tools.model.TClass;
import ast.tools.model.TField;
import ast.tools.model.TImport;
import ast.tools.model.TMethod;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class TUtils {

	private static final List<String> PRIMITIVE_TYPES = Lists.newArrayList("int", "byte", "short", "long", "float",
			"double", "char", "boolean", "void");

	public static TField findAttribute(TClass tClass, Predicate<TField> predicate) {
		for (TField attribute : tClass.getFields()) {
			if (predicate.apply(attribute)) {
				return attribute;
			}
		}
		return null;
	}

	public static Set<TField> filterAttributes(TClass tClass, Predicate<TField> predicate) {
		Set<TField> attributeSet = new HashSet<TField>();
		if (tClass != null) {
			Set<TField> attributes = tClass.getFields();
			Iterator<TField> iterator = attributes.iterator();
			while (iterator.hasNext()) {
				TField attribute = iterator.next();
				if (predicate.apply(attribute)) {
					attributeSet.add(attribute);
				}
			}
		}
		return attributeSet;
	}

	public static Set<TMethod> filterMethods(TClass tClass, Predicate<TMethod> predicate) {
		Set<TMethod> methodSet = new HashSet<TMethod>();
		if (tClass != null) {
			Set<TMethod> methods = tClass.getMethods();
			Iterator<TMethod> iterator = methods.iterator();
			while (iterator.hasNext()) {
				TMethod method = iterator.next();
				if (predicate.apply(method)) {
					methodSet.add(method);
				}
			}
		}
		return methodSet;
	}

	public static TMethod findMethod(TClass tClass, Predicate<TMethod> predicate) {
		for (TMethod method : tClass.getMethods()) {
			if (predicate.apply(method)) {
				return method;
			}
		}
		return null;
	}

	public static boolean hasAnnotation(Set<TAnnotation> annotations, String name) {
		return annotations.contains(new TAnnotationImpl(name));
	}

	public static boolean hasImport(Set<TImport> imports, String name) {
		return imports.contains(new TImportImpl(name));
	}

	public static boolean isPrimitiveType(String type) {
		return PRIMITIVE_TYPES.contains(type);
	}

	public static PrimitiveType.Code stringToPrimitiveCode(String type) {
		if (isPrimitiveType(type)) {
			if (type.equals("int")) {
				return PrimitiveType.INT;
			} else if (type.equals("byte")) {
				return PrimitiveType.BYTE;
			} else if (type.equals("short")) {
				return PrimitiveType.SHORT;
			} else if (type.equals("long")) {
				return PrimitiveType.LONG;
			} else if (type.equals("float")) {
				return PrimitiveType.FLOAT;
			} else if (type.equals("double")) {
				return PrimitiveType.DOUBLE;
			} else if (type.equals("char")) {
				return PrimitiveType.CHAR;
			} else if (type.equals("boolean")) {
				return PrimitiveType.BOOLEAN;
			} else if (type.equals("void")) {
				return PrimitiveType.VOID;
			}
		}
		return null;
	}
}
