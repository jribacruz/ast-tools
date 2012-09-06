package ast.tools.generator.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import ast.tools.generator.context.GeneratorContext;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class CompilationUnit {

	@XmlAttribute(required = true)
	private String name;

	@XmlAttribute(name = "package", required = true)
	private String packageName;

	@XmlElementWrapper(name = "imports")
	@XmlElement(name = "create-import")
	private List<CreateImport> imports;

	@XmlElementWrapper(name = "fields")
	@XmlElement(name = "create-field")
	private List<CreateField> fields;

	@XmlElementWrapper(name = "methods")
	@XmlElement(name = "create-method")
	private List<CreateMethod> methods;

	public CompilationUnit() {
		super();
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
