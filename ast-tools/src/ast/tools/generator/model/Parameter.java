package ast.tools.generator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

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
		return name;
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

}
