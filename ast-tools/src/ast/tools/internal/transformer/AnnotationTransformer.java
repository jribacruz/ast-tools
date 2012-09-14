package ast.tools.internal.transformer;

import java.util.Iterator;

import org.apache.commons.collections.Transformer;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import ast.tools.internal.model.impl.TAnnotationImpl;

public class AnnotationTransformer {

	public class MarkerAnnotationTransformer implements Transformer {

		@Override
		public Object transform(Object annotation) {
			MarkerAnnotation markerAnnotation = (MarkerAnnotation) annotation;
			return new TAnnotationImpl(markerAnnotation.getTypeName().toString());
		}

	}

	public class NormalAnnotationTransformer implements Transformer {

		@Override
		public Object transform(Object annotation) {
			NormalAnnotation normalAnnotation = (NormalAnnotation) annotation;
			Iterator<?> iter = normalAnnotation.values().iterator();
			String name = normalAnnotation.getTypeName().toString();
			Multimap<String, Object> value = ArrayListMultimap.create();
			while (iter.hasNext()) {
				MemberValuePair pair = (MemberValuePair) iter.next();
				value.putAll(getValue(pair));
			}
			return new TAnnotationImpl(name, value);
		}

		public Multimap<String, Object> getValue(MemberValuePair pair) {
			Multimap<String, Object> multimap = ArrayListMultimap.create();
			Expression expression = pair.getValue();
			if (expression.getClass() == StringLiteral.class) {
				StringLiteral literal = (StringLiteral) expression;
				multimap.put(pair.getName().toString(), literal.getEscapedValue());
			} else if (expression.getClass() == ArrayInitializer.class) {
				ArrayInitializer array = (ArrayInitializer) expression;
				Iterator<?> iterator = array.expressions().iterator();
				while (iterator.hasNext()) {
					Object item = iterator.next();
					if (item.getClass() == StringLiteral.class) {
						StringLiteral literal = (StringLiteral) item;
						multimap.put(pair.getName().toString(), literal.getEscapedValue());
					} else if (item.getClass() == NumberLiteral.class) {
						NumberLiteral literal = (NumberLiteral) item;
						multimap.put(pair.getName().toString(), literal.getToken());
					} else if (item.getClass() == QualifiedName.class) {
						QualifiedName qualifiedName = (QualifiedName) item;
						multimap.put(pair.getName().toString(), qualifiedName.getFullyQualifiedName());
					}
				}
			} else if (expression.getClass() == QualifiedName.class) {
				QualifiedName qualifiedName = (QualifiedName) expression;
				multimap.put(pair.getName().toString(), qualifiedName.getFullyQualifiedName());
			}
			return multimap;
		}

	}

	public class SingleMemberAnnotationTransformer implements Transformer {

		public SingleMemberAnnotationTransformer() {
			super();
		}

		@Override
		public Object transform(Object annotation) {
			SingleMemberAnnotation singleMemberAnnotation = (SingleMemberAnnotation) annotation;
			String name = singleMemberAnnotation.getTypeName().toString();
			Multimap<String, Object> value = getValue(singleMemberAnnotation);
			return new TAnnotationImpl(name, value);
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

}
