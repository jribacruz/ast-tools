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
public class CreateMethod {

	@XmlAttribute(required = true)
	private String name;

	@XmlAttribute
	private String returnGenericType;

	@XmlAttribute
	private String returnTypes;

	@XmlAttribute
	private String modifiers;

	@XmlElementWrapper(name = "parameters")
	@XmlElement(name = "parameter")
	private List<Parameter> parameters;

	@XmlElementWrapper(name = "annotations")
	@XmlElement(name = "annotation")
	private List<Annotation> annotations;

	public CreateMethod() {
		super();
		this.parameters = new ArrayList<Parameter>();
		this.annotations = new ArrayList<Annotation>();
	}

	public String getName() {
		return GeneratorContext.replaceTokens(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public String getReturnGenericType() {
		return returnGenericType;
	}

	public void setReturnGenericType(String returnGenericType) {
		this.returnGenericType = returnGenericType;
	}

	public String[] getReturnTypes() {
		return StringUtils.split(returnTypes, ",");
	}

	public void setReturnTypes(String returnTypes) {
		this.returnTypes = returnTypes;
	}

	public String[] getModifiers() {
		return StringUtils.split(modifiers, ",");
	}

	public void setModifiers(String modifiers) {
		this.modifiers = modifiers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateMethod [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (returnGenericType != null) {
			builder.append("returnGenericType=");
			builder.append(returnGenericType);
			builder.append(", ");
		}
		if (returnTypes != null) {
			builder.append("returnTypes=");
			builder.append(returnTypes);
			builder.append(", ");
		}
		if (modifiers != null) {
			builder.append("modifiers=");
			builder.append(modifiers);
			builder.append(", ");
		}
		if (parameters != null) {
			builder.append("parameters=");
			builder.append(parameters);
			builder.append(", ");
		}
		if (annotations != null) {
			builder.append("annotations=");
			builder.append(annotations);
		}
		builder.append("]");
		return builder.toString();
	}

}
