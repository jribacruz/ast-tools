package ast.tools.generator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;

import plugin.commons.util.ProjectUtils;
import ast.tools.generator.context.GeneratorContext;
import ast.tools.generator.core.IGeneratorElement;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateClass {

	@XmlAttribute(required = true)
	private String name;

	@XmlAttribute(name = "package", required = true)
	private String packageName;

	@XmlElement(name = "create-import")
	private List<CreateImport> imports;

	@XmlElement(name = "create-field")
	private List<CreateField> fields;

	@XmlElement(name = "create-method")
	private List<CreateMethod> methods;

	public UpdateClass() {
		super();
		this.imports = new ArrayList<CreateImport>();
		this.fields = new ArrayList<CreateField>();
		this.methods = new ArrayList<CreateMethod>();
	}

	public String getName() {
		return GeneratorContext.replaceTokens(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return GeneratorContext.replaceTokens(packageName);
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<CreateImport> getImports() {
		return imports;
	}

	public void setImports(List<CreateImport> imports) {
		this.imports = imports;
	}

	public List<CreateField> getFields() {
		return fields;
	}

	public void setFields(List<CreateField> fields) {
		this.fields = fields;
	}

	public List<CreateMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<CreateMethod> methods) {
		this.methods = methods;
	}

	public List<IGeneratorElement> getGeneratorElements() {
		List<IGeneratorElement> elements = new ArrayList<IGeneratorElement>();
		elements.addAll(getImports());
		elements.addAll(getFields());
		elements.addAll(getMethods());
		return elements;
	}

	public ICompilationUnit getCompilationUnit(IJavaProject javaProject) {
		return ProjectUtils.getCompilationUnit(javaProject, this.getPackageName(), this.getName());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompilationUnit [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (packageName != null) {
			builder.append("packageName=");
			builder.append(packageName);
			builder.append(", ");
		}
		if (imports != null) {
			builder.append("imports=");
			builder.append(imports);
			builder.append(", ");
		}
		if (fields != null) {
			builder.append("fields=");
			builder.append(fields);
			builder.append(", ");
		}
		if (methods != null) {
			builder.append("methods=");
			builder.append(methods);
		}
		builder.append("]");
		return builder.toString();
	}

}
