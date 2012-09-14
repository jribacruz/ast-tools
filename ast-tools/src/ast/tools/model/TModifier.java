package ast.tools.model;

import org.eclipse.jdt.core.dom.Modifier;
public enum TModifier {
	PUBLIC(Modifier.PUBLIC),

	PROTECTED(Modifier.PROTECTED),

	PRIVATE(Modifier.PRIVATE),

	ABSTRACT(Modifier.ABSTRACT),

	FINAL(Modifier.FINAL),

	STATIC(Modifier.STATIC),

	VOLATILE(Modifier.VOLATILE),

	TRANSIENT(Modifier.TRANSIENT),

	SYNCHRONIZED(Modifier.SYNCHRONIZED),

	NATIVE(Modifier.NATIVE),

	STRICTFP(Modifier.STRICTFP);

	private int modifierType;

	private TModifier(int modifierType) {
		this.modifierType = modifierType;
	}

	public int getModifierType() {
		return modifierType;
	}

}
