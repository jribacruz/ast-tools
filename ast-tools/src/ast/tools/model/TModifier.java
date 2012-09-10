package ast.tools.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.jdt.core.dom.Modifier;

@XmlType
@XmlEnum(String.class)
public enum TModifier {
	@XmlEnumValue("public")
	PUBLIC(Modifier.PUBLIC),

	@XmlEnumValue("protected")
	PROTECTED(Modifier.PROTECTED),

	@XmlEnumValue("private")
	PRIVATE(Modifier.PRIVATE),

	@XmlEnumValue("abstract")
	ABSTRACT(Modifier.ABSTRACT),

	@XmlEnumValue("final")
	FINAL(Modifier.FINAL),

	@XmlEnumValue("static")
	STATIC(Modifier.STATIC),

	@XmlEnumValue("volatile")
	VOLATILE(Modifier.VOLATILE),

	@XmlEnumValue("trasient")
	TRANSIENT(Modifier.TRANSIENT),

	@XmlEnumValue("synchronized")
	SYNCHRONIZED(Modifier.SYNCHRONIZED),

	@XmlEnumValue("native")
	NATIVE(Modifier.NATIVE),

	@XmlEnumValue("stricfp")
	STRICTFP(Modifier.STRICTFP);

	private int modifierType;

	private TModifier(int modifierType) {
		this.modifierType = modifierType;
	}

	public int getModifierType() {
		return modifierType;
	}

}
