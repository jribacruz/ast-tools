package ast.tools.internal.visitor;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import ast.tools.context.ASTContext;
import ast.tools.core.ASTProcessor;
import ast.tools.internal.context.impl.ASTContextImpl;
import ast.tools.internal.predicate.MarkerAnnotationPredicate;
import ast.tools.internal.predicate.NormalAnnotationPredicate;
import ast.tools.internal.predicate.SingleMemberAnnotationPredicate;
import ast.tools.internal.transformer.MarkerAnnotationTransformer;
import ast.tools.internal.transformer.NormalAnnotationTransformer;
import ast.tools.internal.transformer.SingleMemberAnnotationTransformer;
import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;
import ast.tools.model.TImport;
import ast.tools.model.TMethod;
import ast.tools.model.TModifier;
import ast.tools.model.TParameter;

import com.google.common.collect.Lists;

public class ExtendedASTVisitor extends ASTVisitor {
	protected ASTContext context;
	protected ASTProcessor processor;

	protected boolean isInterface = true;
	protected String className;
	protected String superClassName;
	protected String packageName;
	protected Set<TAnnotation> annotations;
	protected Set<TAttribute> attributes;
	protected Set<TMethod> methods;
	protected Set<TImport> imports;
	protected Set<String> interfaces;

	public ExtendedASTVisitor(ASTProcessor processor) {
		super();
		this.processor = processor;
		this.annotations = new HashSet<TAnnotation>();
		this.attributes = new HashSet<TAttribute>();
		this.methods = new HashSet<TMethod>();
		this.imports = new HashSet<TImport>();
		this.interfaces = new HashSet<String>();

	}

	public ASTContext getContext() {
		return this.context == null ? new ASTContextImpl(isInterface, className, superClassName, annotations, attributes,
				methods, imports, interfaces, packageName) : this.context;
	}

	/**
	 * Return attribute name
	 * 
	 * @param declaration
	 * @return
	 */
	protected String getName(FieldDeclaration declaration) {
		return ((VariableDeclarationFragment) declaration.fragments().get(0)).getName().toString();
	}

	/**
	 * Return attribute name
	 * 
	 * @param declaration
	 * @return
	 */
	protected String getName(MethodDeclaration declaration) {
		return declaration.getName().toString();
	}

	/**
	 * Return attribute type
	 * 
	 * @param declaration
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getTypes(FieldDeclaration declaration) {
		return declaration.getType().getClass() == ParameterizedType.class ? ((ParameterizedType) declaration.getType())
				.typeArguments() : Lists.newArrayList(declaration.getType().toString());
	}

	/**
	 * 
	 * @param declaration
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getReturnTypes(MethodDeclaration declaration) {
		return declaration.getReturnType2().getClass() == ParameterizedType.class ? ((ParameterizedType) declaration
				.getReturnType2()).typeArguments() : Lists.newArrayList(declaration.getReturnType2().toString());
	}

	/**
	 * Return attribute generic type
	 * 
	 * @param declaration
	 * @return
	 */
	protected String getGenericType(FieldDeclaration declaration) {
		return declaration.getType().getClass() == ParameterizedType.class ? ((ParameterizedType) declaration.getType())
				.toString() : null;
	}

	/**
	 * 
	 * @param declaration
	 * @return
	 */
	protected String getReturnGenericType(MethodDeclaration declaration) {
		return declaration.getReturnType2().getClass() == ParameterizedType.class ? ((ParameterizedType) declaration
				.getReturnType2()).toString() : null;
	}

	protected Set<TParameter> getParameters(MethodDeclaration declaration) {
		return null;
	}

	protected Set<TModifier> getModifiers(FieldDeclaration declaration) {
		return null;
	}

	protected Set<TModifier> getModifiers(MethodDeclaration declaration) {
		return null;
	}

	protected Set<TAnnotation> processAnnotations(BodyDeclaration declaration) {
		Set<TAnnotation> annotationsSet = new HashSet<TAnnotation>();

		annotationsSet.addAll(processSingleMemberAnnotation(declaration));
		annotationsSet.addAll(processNormalAnnotation(declaration));
		annotationsSet.addAll(processMarkerAnnotation(declaration));

		return annotationsSet;
	}

	@SuppressWarnings("unchecked")
	protected Collection<TAnnotation> processMarkerAnnotation(BodyDeclaration node) {
		Collection<MarkerAnnotation> markerAnnotationList = CollectionUtils.select(node.modifiers(),
				new MarkerAnnotationPredicate());
		return CollectionUtils.collect(markerAnnotationList, new MarkerAnnotationTransformer());
	}

	@SuppressWarnings("unchecked")
	protected Collection<TAnnotation> processSingleMemberAnnotation(BodyDeclaration node) {
		Collection<SingleMemberAnnotation> singleMemberAnnotationList = CollectionUtils.select(node.modifiers(),
				new SingleMemberAnnotationPredicate());
		return CollectionUtils.collect(singleMemberAnnotationList, new SingleMemberAnnotationTransformer());
	}

	@SuppressWarnings("unchecked")
	protected Collection<TAnnotation> processNormalAnnotation(BodyDeclaration node) {
		Collection<NormalAnnotation> normalAnnotationList = CollectionUtils.select(node.modifiers(),
				new NormalAnnotationPredicate());
		return CollectionUtils.collect(normalAnnotationList, new NormalAnnotationTransformer());
	}

}
