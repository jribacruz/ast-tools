package ast.tools.internal.transformer;

import org.apache.commons.collections.Transformer;
import org.eclipse.jdt.core.dom.MarkerAnnotation;

import ast.tools.internal.model.impl.TAnnotationImpl;

public class MarkerAnnotationTransformer implements Transformer {

	@Override
	public Object transform(Object annotation) {
		MarkerAnnotation markerAnnotation = (MarkerAnnotation) annotation;
		return new TAnnotationImpl(markerAnnotation.getTypeName().toString());
	}

}
