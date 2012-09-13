package ast.tools.observable;

import java.util.Iterator;
import java.util.Set;

import ast.tools.observer.ASTObserver;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

public class ASTObservable {

	private SetMultimap<Class<?>, ASTObserver> observers;

	public ASTObservable() {
		super();
		this.observers = HashMultimap.create();
	}

	public <T> void notifyObservers(T item) {
		if (observers.containsKey(item.getClass())) {
			Set<ASTObserver> observer = observers.get(item.getClass());
			Iterator<ASTObserver> iterator = observer.iterator();
			while (iterator.hasNext()) {
				ASTObserver observer2 = iterator.next();
				observer2.update(item);
			}
		}
	}

	public void registerObserver(Class<?> classItem, ASTObserver observer) {
		observers.put(classItem, observer);
	}

}
