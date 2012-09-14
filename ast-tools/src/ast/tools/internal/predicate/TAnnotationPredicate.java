package ast.tools.internal.predicate;

import ast.tools.internal.model.impl.TAnnotationImpl;
import ast.tools.model.TAnnotation;

import com.google.common.base.Predicate;

public class TAnnotationPredicate implements Predicate<TAnnotation> {

	private String name;

	@Override
	public boolean apply(TAnnotation annotation) {
		return annotation.equals(new TAnnotationImpl(name));
	}

}
