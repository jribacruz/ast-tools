package ast.commons.internal.transformer;

import java.util.Iterator;

import org.apache.commons.collections.Transformer;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;

import ast.commons.model.impl.AnnotationImpl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SingleMemberAnnotationTransformer implements Transformer {

	public SingleMemberAnnotationTransformer() {
		super();
	}

	@Override
	public Object transform(Object annotation) {
		SingleMemberAnnotation singleMemberAnnotation = (SingleMemberAnnotation) annotation;
		String name = singleMemberAnnotation.getTypeName().toString();
		Multimap<String, Object> value = getValue(singleMemberAnnotation);
		return new AnnotationImpl(name, value);
	}

	public Multimap<String, Object> getValue(SingleMemberAnnotation annotation) {
		Multimap<String, Object> multimap = ArrayListMultimap.create();
		if (annotation.getValue().getClass() == ArrayInitializer.class) {
			ArrayInitializer array = (ArrayInitializer) annotation.getValue();
			Iterator<?> iterator = array.expressions().iterator();
			while (iterator.hasNext()) {
				Object item = iterator.next();
				if (item.getClass() == StringLiteral.class) {
					StringLiteral literal = (StringLiteral) item;
					multimap.put("value", literal.getEscapedValue());
				} else if (item.getClass() == NumberLiteral.class) {
					NumberLiteral literal = (NumberLiteral) item;
					multimap.put("value", literal.getToken());
				}
			}
		} else {
			multimap.put("value", annotation.getValue().toString());
		}
		return multimap;
	}

}
