package ast.tools.observer;

public interface ASTObserver {
	<T> void update(T item);
}
