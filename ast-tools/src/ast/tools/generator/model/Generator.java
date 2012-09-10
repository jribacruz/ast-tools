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

	@XmlElement(name = "update-class")
	private List<UpdateClass> updateClass;

	public Generator() {
		super();
		this.updateClass = new ArrayList<UpdateClass>();
	}

	public List<UpdateClass> getUpdateClass() {
		return updateClass;
	}

	public void setUpdateClass(List<UpdateClass> updateClass) {
		this.updateClass = updateClass;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Generator [");
		if (updateClass != null) {
			builder.append("compilationUnits=");
			builder.append(updateClass);
		}
		builder.append("]");
		return builder.toString();
	}

}
