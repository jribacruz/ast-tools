package ast.tools.visitor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import ast.tools.context.ASTContext;
import ast.tools.context.impl.ASTContextImpl;
import ast.tools.core.ASTProcessor;
import ast.tools.internal.predicate.MarkerAnnotationPredicate;
import ast.tools.internal.predicate.NormalAnnotationPredicate;
import ast.tools.internal.predicate.SingleMemberAnnotationPredicate;
import ast.tools.internal.transformer.MarkerAnnotationTransformer;
import ast.tools.internal.transformer.NormalAnnotationTransformer;
import ast.tools.internal.transformer.SingleMemberAnnotationTransformer;
import ast.tools.model.Annotation;
import ast.tools.model.Attribute;
import ast.tools.model.impl.AttributeImpl;

public class ASTVisitor extends org.eclipse.jdt.core.dom.ASTVisitor {
	private ASTContext context;
	private ASTProcessor processor;

	private boolean isInterface = true;
	private String className;
	private String superClassName;
	private Set<Annotation> annotations;
	private Set<Attribute> attributes;

	public ASTVisitor(ASTProcessor processor) {
		super();
		this.annotations = new HashSet<Annotation>();
		this.attributes = new HashSet<Attribute>();
		this.processor = processor;
	}

	public ASTContext getContext() {
		return this.context == null ? new ASTContextImpl(isInterface, className, superClassName, annotations, attributes)
		: this.context;
	}

	@Override
	public boolean visit(TypeDeclaration node) {

		if (!node.isInterface()) {
			this.isInterface = false;
			this.className = node.getName().toString();
			if (node.getSuperclassType() != null) {
				this.superClassName = node.getSuperclassType().toString();
			}
			this.annotations = processAnnotations(node);
			processor.notifyClassObservers(className, superClassName, annotations);
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldDeclaration declaration) {

		String name = getName(declaration);
		String type = getType(declaration);
		String genericType = getGenericType(declaration);
		Set<Annotation> attributeAnnotations = processAnnotations(declaration);

		Attribute attribute = new AttributeImpl(name, type, genericType, attributeAnnotations);
		this.attributes.add(attribute);
		processor.notifyAttributeObservers(name, type, genericType, attributeAnnotations);

		return super.visit(declaration);
	}

	/**
	 * Return attribute name
	 * 
	 * @param declaration
	 * @return
	 */
	private String getName(FieldDeclaration declaration) {
		return ((VariableDeclarationFragment) declaration.fragments().get(0)).getName().toString();
	}

	/**
	 * Return attribute type
	 * 
	 * @param declaration
	 * @return
	 */
	private String getType(FieldDeclaration declaration) {
		return declaration.getType().getClass() == ParameterizedType.class ? ((ParameterizedType) declaration.getType())
				.getType().toString() : declaration.getType().toString();
	}

	/**
	 * Return attribute generic type
	 * 
	 * @param declaration
	 * @return
	 */
	private String getGenericType(FieldDeclaration declaration) {
		return declaration.getType().getClass() == ParameterizedType.class ? ((ParameterizedType) declaration.getType())
				.typeArguments().get(0).toString() : null;
	}

	private Set<Annotation> processAnnotations(BodyDeclaration declaration) {
		Set<Annotation> annotationsSet = new HashSet<Annotation>();

		annotationsSet.addAll(processSingleMemberAnnotation(declaration));
		annotationsSet.addAll(processNormalAnnotation(declaration));
		annotationsSet.addAll(processMarkerAnnotation(declaration));

		return annotationsSet;
	}

	@SuppressWarnings("unchecked")
	private Collection<Annotation> processMarkerAnnotation(BodyDeclaration node) {
		Collection<MarkerAnnotation> markerAnnotationList = CollectionUtils.select(node.modifiers(),
				new MarkerAnnotationPredicate());
		return CollectionUtils.collect(markerAnnotationList, new MarkerAnnotationTransformer());
	}

	@SuppressWarnings("unchecked")
	private Collection<Annotation> processSingleMemberAnnotation(BodyDeclaration node) {
		Collection<SingleMemberAnnotation> singleMemberAnnotationList = CollectionUtils.select(node.modifiers(),
				new SingleMemberAnnotationPredicate());
		return CollectionUtils.collect(singleMemberAnnotationList, new SingleMemberAnnotationTransformer());
	}

	@SuppressWarnings("unchecked")
	private Collection<Annotation> processNormalAnnotation(BodyDeclaration node) {
		Collection<NormalAnnotation> normalAnnotationList = CollectionUtils.select(node.modifiers(),
				new NormalAnnotationPredicate());
		return CollectionUtils.collect(normalAnnotationList, new NormalAnnotationTransformer());
	}

}
