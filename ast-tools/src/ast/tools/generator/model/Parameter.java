package ast.tools.generator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

import ast.tools.generator.context.GeneratorContext;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Parameter {

	@XmlAttribute(required = true)
	private String name;

	@XmlAttribute
	private String genericType;

	@XmlAttribute
	private String types;

	public Parameter() {
		super();
	}

	public String getName() {
		return GeneratorContext.replaceTokens(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenericType() {
		return genericType;
	}

	public void setGenericType(String genericType) {
		this.genericType = genericType;
	}

	public String[] getTypes() {
		return StringUtils.split(types, ",");
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parameter [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (genericType != null) {
			builder.append("genericType=");
			builder.append(genericType);
			builder.append(", ");
		}
		if (types != null) {
			builder.append("types=");
			builder.append(types);
		}
		builder.append("]");
		return builder.toString();
	}

}
