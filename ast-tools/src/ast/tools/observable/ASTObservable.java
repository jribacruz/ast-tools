package ast.tools.observable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ast.tools.model.TAnnotation;
import ast.tools.model.TModifier;
import ast.tools.observer.ASTAttributeObserver;
import ast.tools.observer.ASTClassObserver;
import ast.tools.observer.ASTImportObserver;
import ast.tools.observer.ASTMethodObserver;

public class ASTObservable {
	private Set<ASTClassObserver> classObservers;
	private Set<ASTAttributeObserver> attributeObservers;
	private Set<ASTMethodObserver> methodObservers;
	private Set<ASTImportObserver> importObservers;

	public ASTObservable() {
		super();
		this.classObservers = new HashSet<ASTClassObserver>();
		this.attributeObservers = new HashSet<ASTAttributeObserver>();
		this.methodObservers = new HashSet<ASTMethodObserver>();
		this.importObservers = new HashSet<ASTImportObserver>();
	}

	public void notifyClassObservers(String className, String superClassName, Set<TAnnotation> annotations) {
		Iterator<ASTClassObserver> iterator = classObservers.iterator();
		while (iterator.hasNext()) {
			ASTClassObserver classObserver = iterator.next();
			classObserver.update(className, superClassName, annotations);
		}
	}

	public void notifyAttributeObservers(String name, String type, String genericType, Set<TModifier> modifiers,
			Set<TAnnotation> annotations) {
		Iterator<ASTAttributeObserver> iterator = attributeObservers.iterator();
		while (iterator.hasNext()) {
			ASTAttributeObserver attributeObserver = iterator.next();
			attributeObserver.update(name, type, genericType, modifiers, annotations);
		}
	}

	public void notifyMethodObservers(String name, String type, String genericType, Set<TModifier> modifiers,
			Set<TAnnotation> annotations) {
		Iterator<ASTAttributeObserver> iterator = attributeObservers.iterator();
		while (iterator.hasNext()) {
			ASTAttributeObserver attributeObserver = iterator.next();
			attributeObserver.update(name, type, genericType, modifiers, annotations);
		}
	}

	public void notifyImportObservers(String name) {
		Iterator<ASTImportObserver> iterator = importObservers.iterator();
		while (iterator.hasNext()) {
			ASTImportObserver importObserver = iterator.next();
			importObserver.update(name);
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
