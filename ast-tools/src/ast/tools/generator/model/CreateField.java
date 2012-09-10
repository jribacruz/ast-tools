package ast.tools.generator.model;

import java.util.ArrayList;
import java.util.HashSet;
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
import ast.tools.helper.FieldDeclarationHelper;
import ast.tools.model.TModifier;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateField implements IGeneratorElement {

	@XmlAttribute(required = true)
	private String name;

	@XmlAttribute
	@XmlList
	private Set<TModifier> modifiers;

	@XmlAttribute
	@XmlList
	private List<String> types;

	@XmlAttribute
	private String genericType;

	@XmlElementWrapper(name = "annotations")
	@XmlElement(name = "annotation")
	private List<Annotation> annotations;

	public CreateField() {
		super();
		this.annotations = new ArrayList<Annotation>();
		this.modifiers = new HashSet<TModifier>();
		this.types = new ArrayList<String>();
	}

	public String getName() {
		// substitui os tokens do nome
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

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public String[] getTypes() {
		return types.toArray(new String[] {});
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateField [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (modifiers != null) {
			builder.append("modifiers=");
			builder.append(modifiers);
			builder.append(", ");
		}
		if (types != null) {
			builder.append("types=");
			builder.append(types);
			builder.append(", ");
		}
		if (genericType != null) {
			builder.append("genericType=");
			builder.append(genericType);
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
				FieldDeclarationHelper helper = new FieldDeclarationHelper(ast, getName(), modifiers);
				helper.setType(getGenericType(), getTypes());
				unitHelper.addField(helper.getFieldDeclaration());
			}
		};
	}

}
