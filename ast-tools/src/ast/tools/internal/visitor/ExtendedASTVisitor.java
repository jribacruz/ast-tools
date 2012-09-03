package ast.tools.internal.visitor;

import java.util.ArrayList;
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
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import ast.tools.internal.model.impl.TParameterImpl;
import ast.tools.internal.predicate.MarkerAnnotationPredicate;
import ast.tools.internal.predicate.NormalAnnotationPredicate;
import ast.tools.internal.predicate.SingleMemberAnnotationPredicate;
import ast.tools.internal.transformer.MarkerAnnotationTransformer;
import ast.tools.internal.transformer.NormalAnnotationTransformer;
import ast.tools.internal.transformer.SingleMemberAnnotationTransformer;
import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;
import ast.tools.model.TClass;
import ast.tools.model.TImport;
import ast.tools.model.TInterface;
import ast.tools.model.TMethod;
import ast.tools.model.TModifier;
import ast.tools.model.TParameter;

import com.google.common.collect.Lists;

public class ExtendedASTVisitor extends ASTVisitor {

	protected TClass klass;

	protected boolean isInterface = true;
	protected String className;
	protected String superClassName;
	protected String packageName;
	protected Set<TAnnotation> annotations;
	protected Set<TAttribute> attributes;
	protected Set<TMethod> methods;
	protected Set<TImport> imports;
	protected Set<TInterface> interfaces;
	protected List<String> genericTypeArguments;
	protected List<String> superClassGenericTypeArguments;

	public ExtendedASTVisitor() {
		super();
		this.annotations = new HashSet<TAnnotation>();
		this.attributes = new HashSet<TAttribute>();
		this.methods = new HashSet<TMethod>();
		this.imports = new HashSet<TImport>();
		this.interfaces = new HashSet<TInterface>();
		this.genericTypeArguments = new ArrayList<String>();
		this.superClassGenericTypeArguments = new ArrayList<String>();

	}

	protected Set<TInterface> getInterfaces(TypeDeclaration declaration) {
		if (declaration.isInterface()) {

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected String getSuperClassName(TypeDeclaration declaration) {
		if (declaration.getSuperclassType() != null) {
			if (declaration.getSuperclassType().isSimpleType()) {
				SimpleType simpleType = (SimpleType) declaration.getSuperclassType();
				this.superClassName = simpleType.getName().toString();
			} else if (declaration.getSuperclassType().isParameterizedType()) {
				ParameterizedType parameterizedType = (ParameterizedType) declaration.getSuperclassType();
				this.superClassName = parameterizedType.getType().toString();
				this.superClassGenericTypeArguments.addAll(parameterizedType.typeArguments());
			}
		}
		return this.superClassName;
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
		Type type = declaration.getReturnType2();
		if (type != null) {
			if (type.getClass() == ParameterizedType.class) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				return parameterizedType.typeArguments();
			} else {
				return Lists.newArrayList(declaration.getReturnType2().toString());
			}
		}
		return new ArrayList<String>();
	}

	/**
	 * Return attribute generic type
	 * 
	 * @param declaration
	 * @return
	 */
	protected String getGenericType(FieldDeclaration declaration) {
		Type type = declaration.getType();
		if (type.getClass() == ParameterizedType.class) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			if (parameterizedType.getType().getClass() == SimpleType.class) {
				SimpleType simpleType = (SimpleType) parameterizedType.getType();
				return simpleType.getName().toString();
			}
		}
		return "";
	}

	/**
	 * 
	 * @param declaration
	 * @return
	 */
	protected String getReturnGenericType(MethodDeclaration declaration) {
		Type type = declaration.getReturnType2();
		// caso o método seja construtor nao processa
		if (type != null) {
			if (type.getClass() == ParameterizedType.class) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				if (parameterizedType.getType().getClass() == SimpleType.class) {
					SimpleType simpleType = (SimpleType) parameterizedType.getType();
					return simpleType.getName().toString();
				}
			}
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	protected Set<TParameter> getParameters(MethodDeclaration declaration) {
		Set<TParameter> parameters = new HashSet<TParameter>();

		List<SingleVariableDeclaration> parameterList = declaration.parameters();
		for (SingleVariableDeclaration singleVariableDeclaration : parameterList) {
			String parameterName = singleVariableDeclaration.getName().toString();
			String genericType = null;
			List<String> types = new ArrayList<String>();

			if (singleVariableDeclaration.getType().getClass() == ParameterizedType.class) {
				ParameterizedType parameterizedType = (ParameterizedType) singleVariableDeclaration.getType();
				types.addAll(parameterizedType.typeArguments());
				genericType = parameterizedType.getType().toString();
			} else {
				types.add(singleVariableDeclaration.getType().toString());
			}
			parameters.add(new TParameterImpl(parameterName, types, genericType));
		}

		return parameters;
	}

	@SuppressWarnings("unchecked")
	protected Set<TModifier> getModifiers(BodyDeclaration declaration) {
		Set<TModifier> modifiers = new HashSet<TModifier>();
		modifiers.addAll(declaration.modifiers());
		return modifiers;
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
