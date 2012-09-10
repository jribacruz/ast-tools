package ast.tools.generator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.jdt.core.dom.AST;

import ast.tools.context.ASTWriter;
import ast.tools.generator.context.GeneratorContext;
import ast.tools.generator.core.IGeneratorElement;
import ast.tools.helper.CompilationUnitHelper;
import ast.tools.helper.MethodDeclarationHelper;
import ast.tools.model.TModifier;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateMethod implements IGeneratorElement {

	@XmlAttribute(required = true)
	private String name;

	@XmlAttribute
	private String returnGenericType;

	@XmlAttribute
	@XmlList
	private List<String> returnTypes;

	@XmlAttribute
	private boolean nullReturn;

	@XmlAttribute
	private boolean constructor;

	@XmlAttribute
	@XmlList
	private Set<TModifier> modifiers;

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
		this.returnTypes= new ArrayList<String>();
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
		return returnTypes.toArray(new String[] {});
	}

	public void setReturnTypes(List<String> returnTypes) {
		this.returnTypes = returnTypes;
	}

	public Set<TModifier> getModifiers() {
		return modifiers;
	}

	public void setModifiers(Set<TModifier> modifiers) {
		this.modifiers = modifiers;
	}

	public boolean isNullReturn() {
		return nullReturn;
	}

	public void setNullReturn(boolean nullReturn) {
		this.nullReturn = nullReturn;
	}

	public boolean isConstructor() {
		return constructor;
	}

	public void setConstructor(boolean constructor) {
		this.constructor = constructor;
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

	@Override
	public ASTWriter apply() {
		return new ASTWriter() {

			@Override
			public void write(CompilationUnitHelper unitHelper, AST ast) {
				MethodDeclarationHelper helper;
				if (constructor) {
					helper = new MethodDeclarationHelper(ast, getName(), modifiers, constructor);
					unitHelper.addMethod(helper.getMethodDeclaration());
				} else {
					helper = new MethodDeclarationHelper(ast, getName(), modifiers);
					helper.setReturnType(getReturnGenericType(), getReturnTypes());
					for (Parameter p : getParameters()) {
						helper.addParameter(p.getName(), p.getGenericType(), p.getTypes());
					}
					if(isNullReturn()) {
						helper.setNullReturn();
					}
					unitHelper.addMethod(helper.getMethodDeclaration());
				}
			}
		};
	}

}
