package ast.tools.observer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ast.tools.model.Annotation;

public class ASTObservable {
	private Set<ASTClassObserver> classObservers;
	private Set<ASTAttributeObserver> attributeObservers;

	public ASTObservable() {
		super();
		this.classObservers = new HashSet<ASTClassObserver>();
		this.attributeObservers = new HashSet<ASTAttributeObserver>();
	}

	public void notifyClassObservers(String className, String superClassName, Set<Annotation> annotations) {
		Iterator<ASTClassObserver> iterator = classObservers.iterator();
		while (iterator.hasNext()) {
			ASTClassObserver classObserver = iterator.next();
			classObserver.update(className, superClassName, annotations);
		}
	}

	public void notifyAttributeObservers(String name, String type, String genericType, Set<Annotation> annotations) {
		Iterator<ASTAttributeObserver> iterator = attributeObservers.iterator();
		while (iterator.hasNext()) {
			ASTAttributeObserver attributeObserver = iterator.next();
			attributeObserver.update(name, type, genericType, annotations);
		}
	}

	public void registerObserver(ASTAttributeObserver observer) {
		this.attributeObservers.add(observer);
	}

	public void removeObserver(ASTAttributeObserver observer) {
		this.attributeObservers.remove(observer);
	}

	public void registerObserver(ASTClassObserver observer) {
		this.classObservers.add(observer);
	}

	public void removeObserver(ASTClassObserver observer) {
		this.classObservers.remove(observer);
	}

	public Set<ASTClassObserver> getClassObservers() {
		return classObservers;
	}

	public Set<ASTAttributeObserver> getAttributeObservers() {
		return attributeObservers;
	}

}
