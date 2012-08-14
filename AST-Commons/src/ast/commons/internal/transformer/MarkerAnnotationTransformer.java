package ast.commons.internal.transformer;

import org.apache.commons.collections.Transformer;
import org.eclipse.jdt.core.dom.MarkerAnnotation;

import ast.commons.model.impl.AnnotationImpl;

public class MarkerAnnotationTransformer implements Transformer {

	@Override
	public Object transform(Object annotation) {
		MarkerAnnotation markerAnnotation = (MarkerAnnotation) annotation;
		return new AnnotationImpl(markerAnnotation.getTypeName().toString());
	}

}
