package ast.tools.internal.visitor;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import ast.tools.context.ASTContext;
import ast.tools.core.ASTProcessor;
import ast.tools.internal.context.impl.ASTContextImpl;
import ast.tools.internal.model.impl.TFieldImpl;
import ast.tools.internal.model.impl.TImportImpl;
import ast.tools.internal.model.impl.TMethodImpl;
import ast.tools.model.TAnnotation;
import ast.tools.model.TField;
import ast.tools.model.TMethod;
import ast.tools.model.TModifier;
import ast.tools.model.TParameter;
import ast.tools.model.TTag;

/**
 * Classe que processa a classe, os campos
 * 
 * @author jrmc
 * 
 */
public class ASTVisitor extends ExtendedASTVisitor {

	protected ASTContext context;
	protected ASTProcessor processor;

	public ASTVisitor(ASTProcessor processor) {
		super();
		this.processor = processor;
	}

	/**
	 * processa a informação do pacote (nome qualificado do pacote)
	 */
	@Override
	public boolean visit(PackageDeclaration node) {
		this.packageName = node.getName().toString();
		return super.visit(node);
	}

	/**
	 * processa os dados do import (nome qualificado do import)
	 */
	@Override
	public boolean visit(ImportDeclaration node) {
		imports.add(new TImportImpl(node.getName().toString()));
		processor.notifyImportObservers(node.getName().toString());
		return super.visit(node);
	}

	/**
	 * processa os dados gerais da classe (nome, extenções, implementações, e
	 * possíveis tipos genéricos)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(TypeDeclaration declaration) {

		if (!declaration.isInterface()) {
			this.isInterface = false;
			this.className = declaration.getName().toString();
			this.genericTypeArguments.addAll(declaration.typeParameters());
			this.superClassName = getSuperClassName(declaration);
			this.interfaces = getInterfaces(declaration);
			this.annotations = processAnnotations(declaration);
			this.tags = getTags(declaration.getJavadoc());
			// Notifica os observadores da classe (ASTClassObservers)
			processor.notifyClassObservers(this.className, this.genericTypeArguments, this.superClassName,
					this.superClassGenericTypeArguments, this.annotations, this.interfaces, this.tags);
		} else {
			this.className = declaration.getName().toString();
			if (declaration.getSuperclassType() != null) {
				// this.superClassName = node.getSuperclassType().toString();
			}
			// Notifica os observadores da classe (ASTClassObservers)
			processor.notifyInterfaceObservers(this.className, this.superClassName, this.genericTypeArguments);
		}
		return super.visit(declaration);
	}

	/**
	 * Processa os campos da classe e adiciona na lista de campos da classe,
	 * 
	 * 
	 */
	@Override
	public boolean visit(FieldDeclaration declaration) {
		String name = getName(declaration);
		List<String> types = getTypes(declaration);
		String genericType = getGenericType(declaration);
		Set<TAnnotation> attributeAnnotations = processAnnotations(declaration);
		Set<TModifier> modifiers = getModifiers(declaration);
		List<TTag> tags = getTags(declaration.getJavadoc());
		// cria o objeto TAttribute
		TField attribute = new TFieldImpl(name, types, genericType, attributeAnnotations, modifiers, tags);

		this.fields.add(attribute);

		// notifica os observadores de attributos (ASTAttributeObservers)
		processor.notifyAttributeObservers(name, types, genericType, modifiers, attributeAnnotations, tags);

		return super.visit(declaration);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(MethodDeclaration declaration) {
		String name = getName(declaration);
		String returnGenericType = getReturnGenericType(declaration);
		boolean constructor = declaration.getReturnType2() == null;
		List<String> returnTypes = getReturnTypes(declaration);
		Set<TAnnotation> methodAnnotations = processAnnotations(declaration);
		Set<TModifier> modifiers = getModifiers(declaration);
		Set<TParameter> parameters = getParameters(declaration);
		List<String> thrownExceptions = declaration.thrownExceptions();
		List<TTag> tags = getTags(declaration.getJavadoc());

		// cria o objeto TMethod e adiciona a lista de metodos
		TMethod method = new TMethodImpl(name, parameters, methodAnnotations, modifiers, returnTypes, returnGenericType,
				constructor, thrownExceptions, tags);
		this.methods.add(method);

		// notifica os observadores sobre os dados do metodo
		processor.notifyMethodObservers(name, returnTypes, returnGenericType, modifiers, parameters, methodAnnotations,
				constructor, thrownExceptions, tags);

		return super.visit(declaration);
	}

	public ASTContext getContext() {
		return this.context == null ? new ASTContextImpl(isInterface, className, annotations, fields, methods, imports,
				interfaces, packageName, superClassName, genericTypeArguments, superClassGenericTypeArguments)
		: this.context;
	}

}
