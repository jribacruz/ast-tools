package ast.tools.generator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateImport {

	@XmlAttribute
	private String name;

	public String[] getNames() {
		return StringUtils.split(name, ".");
	}

	public void setName(String name) {
		this.name = name;
	}

}
