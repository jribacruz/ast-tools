package ast.tools.observer;

import java.util.List;


public interface ASTInterfaceObserver {
	void update(String intefaceName, String superInterfaceName, List<String> genericTypeArguments);
}
