package ast.tools.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;

import ast.tools.core.ASTProcessor;
import ast.tools.model.TClass;
import ast.tools.model.TField;
import ast.tools.model.TMethod;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class TUtils {

	public static TClass getTClass(ICompilationUnit iunit) {
		ASTProcessor processor = new ASTProcessor(iunit);
		processor.visit();
		return processor.getTClass();
	}

	/**
	 * returna o atributo da classe de acordo com o predicado especifico.
	 * 
	 * @param tClass
	 * @param predicate
	 * @return
	 */
	public static TField findAttribute(TClass tClass, Predicate<TField> predicate) {
		for (TField attribute : tClass.getFields()) {
			if (predicate.apply(attribute)) {
				return attribute;
			}
		}
		return null;
	}

	/**
	 * retorna a lista de atributos espeficicados de acordo com o predicado
	 * 
	 * @param tClass
	 * @param predicate
	 * @return
	 */
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

	/**
	 * Retorna os metodos da classe de acordo com o predicado especificado
	 * 
	 * @param tClass
	 * @param predicate
	 * @return
	 */
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

	/**
	 * Retorna o metodo especificado de acordo com o predicado especificado
	 * 
	 * @param tClass
	 * @param predicate
	 * @return
	 */
	public static TMethod findMethod(TClass tClass, Predicate<TMethod> predicate) {
		for (TMethod method : tClass.getMethods()) {
			if (predicate.apply(method)) {
				return method;
			}
		}
		return null;
	}

	public static boolean isCollectionAttribute(TField field) {
		return !field.getTypes().isEmpty() ? field.getGenericType().equals("List") || field.getGenericType().equals("Collection")
				|| field.getGenericType().equals("Set") : field.getTypes().contains(Lists.newArrayList("List", "Collection", "Set"));
	}
}
