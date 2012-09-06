package ast.tools.generator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

import ast.tools.generator.context.GeneratorContext;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateField {

	@XmlAttribute(required = true)
	private String name;

	@XmlAttribute
	private String modifiers;

	@XmlAttribute
	private String types;

	@XmlAttribute
	private String genericType;

	@XmlElementWrapper(name = "annotations")
	@XmlElement(name = "annotation")
	private List<Annotation> annotations;

	public CreateField() {
		super();
		this.annotations = new ArrayList<Annotation>();
	}

	public String getName() {
		return GeneratorContext.replaceTokens(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getTypes() {
		return StringUtils.split(types, ",");
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getGenericType() {
		return genericType;
	}

	public void setGenericType(String genericType) {
		this.genericType = genericType;
	}

	public String[] getModifiers() {
		return StringUtils.split(this.modifiers, ",");
	}

	public void setModifiers(String modifiers) {
		this.modifiers = modifiers;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

}
