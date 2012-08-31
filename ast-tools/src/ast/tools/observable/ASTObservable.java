package ast.tools.observable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TModifier;
import ast.tools.model.TParameter;
import ast.tools.observer.ASTAttributeObserver;
import ast.tools.observer.ASTClassObserver;
import ast.tools.observer.ASTImportObserver;
import ast.tools.observer.ASTInterfaceObserver;
import ast.tools.observer.ASTMethodObserver;

public class ASTObservable {
	private Set<ASTClassObserver> classObservers;
	private Set<ASTAttributeObserver> attributeObservers;
	private Set<ASTMethodObserver> methodObservers;
	private Set<ASTImportObserver> importObservers;
	private Set<ASTInterfaceObserver> interfaceObservers;

	public ASTObservable() {
		super();
		this.attributeObservers = new HashSet<ASTAttributeObserver>();
		this.classObservers = new HashSet<ASTClassObserver>();
		this.methodObservers = new HashSet<ASTMethodObserver>();
		this.importObservers = new HashSet<ASTImportObserver>();
		this.interfaceObservers = new HashSet<ASTInterfaceObserver>();
	}

	/**
	 * 
	 * @param className
	 * @param superClassName
	 * @param annotations
	 * @param interfaces
	 */
	public void notifyClassObservers(String className, String superClassName, Set<TAnnotation> annotations,
			Set<String> interfaces) {
		Iterator<ASTClassObserver> iterator = classObservers.iterator();
		while (iterator.hasNext()) {
			ASTClassObserver classObserver = iterator.next();
			classObserver.update(className, superClassName, annotations, interfaces);
		}
	}

	/**
	 * 
	 * @param name
	 * @param types
	 * @param genericType
	 * @param modifiers
	 * @param annotations
	 */
	public void notifyAttributeObservers(String name, List<String> types, String genericType, Set<TModifier> modifiers,
			Set<TAnnotation> annotations) {
		Iterator<ASTAttributeObserver> iterator = attributeObservers.iterator();
		while (iterator.hasNext()) {
			ASTAttributeObserver attributeObserver = iterator.next();
			attributeObserver.update(name, types, genericType, modifiers, annotations);
		}
	}

	/**
	 * 
	 * @param name
	 * @param returnTypes
	 * @param genericReturnType
	 * @param modifiers
	 * @param parameters
	 * @param annotations
	 */
	public void notifyMethodObservers(String name, List<String> returnTypes, String genericReturnType,
			Set<TModifier> modifiers, Set<TParameter> parameters, Set<TAnnotation> annotations) {
		Iterator<ASTMethodObserver> iterator = methodObservers.iterator();
		while (iterator.hasNext()) {
			ASTMethodObserver methodObserver = iterator.next();
			methodObserver.update(name, returnTypes, genericReturnType, modifiers, parameters, annotations);
		}
	}

	/**
	 * 
	 * @param name
	 */
	public void notifyImportObservers(String name) {
		Iterator<ASTImportObserver> iterator = importObservers.iterator();
		while (iterator.hasNext()) {
			ASTImportObserver importObserver = iterator.next();
			importObserver.update(name);
		}
	}

	/**
	 * 
	 * @param name
	 */
	public void notifyInterfaceObservers(String name, String superInterfaceName) {
		Iterator<ASTInterfaceObserver> iterator = interfaceObservers.iterator();
		while (iterator.hasNext()) {
			ASTInterfaceObserver interfaceObserver = iterator.next();
			interfaceObserver.update(name, superInterfaceName);
		}
	}

	public void registerObserver(ASTAttributeObserver observer) {
		this.attributeObservers.add(observer);
	}

	public void registerObserver(ASTClassObserver observer) {
		this.classObservers.add(observer);
	}

	public void registerObserver(ASTMethodObserver observer) {
		this.methodObservers.add(observer);
	}

	public void registerObserver(ASTImportObserver observer) {
		this.importObservers.add(observer);
	}

	public void removeObserver(ASTAttributeObserver observer) {
		this.attributeObservers.remove(observer);
	}

	public void removeObserver(ASTClassObserver observer) {
		this.classObservers.remove(observer);
	}

	public void removeObserver(ASTMethodObserver observer) {
		this.methodObservers.remove(observer);
	}

	public void removeObserver(ASTImportObserver observer) {
		this.importObservers.remove(observer);
	}

	public Set<ASTClassObserver> getClassObservers() {
		return classObservers;
	}

	public Set<ASTAttributeObserver> getAttributeObservers() {
		return attributeObservers;
	}

}
