package ast.tools.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ast.tools.internal.model.impl.TAnnotationImpl;
import ast.tools.internal.model.impl.TImportImpl;
import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;
import ast.tools.model.TClass;
import ast.tools.model.TImport;
import ast.tools.model.TMethod;

import com.google.common.base.Predicate;

public class TUtils {

	public static TAttribute findAttribute(TClass tClass, Predicate<TAttribute> predicate) {
		for (TAttribute attribute : tClass.getAttributes()) {
			if (predicate.apply(attribute)) {
				return attribute;
			}
		}
		return null;
	}

	public static Set<TAttribute> filterAttributes(TClass tClass, Predicate<TAttribute> predicate) {
		Set<TAttribute> attributeSet = new HashSet<TAttribute>();
		if (tClass != null) {
			Set<TAttribute> attributes = tClass.getAttributes();
			Iterator<TAttribute> iterator = attributes.iterator();
			while (iterator.hasNext()) {
				TAttribute attribute = iterator.next();
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
}
