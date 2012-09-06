package ast.tools.generator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "generator")
@XmlAccessorType(XmlAccessType.FIELD)
public class Generator {

	@XmlElement(name = "compilation-unit")
	private List<CompilationUnit> compilationUnits;

	public Generator() {
		super();
		this.compilationUnits = new ArrayList<CompilationUnit>();
	}

	public List<CompilationUnit> getCompilationUnits() {
		return compilationUnits;
	}

	public void setCompilationUnits(List<CompilationUnit> compilationUnits) {
		this.compilationUnits = compilationUnits;
	}

}
