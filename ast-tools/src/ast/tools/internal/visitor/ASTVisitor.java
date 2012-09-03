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
import ast.tools.internal.model.impl.TAttributeImpl;
import ast.tools.internal.model.impl.TImportImpl;
import ast.tools.internal.model.impl.TMethodImpl;
import ast.tools.model.TAnnotation;
import ast.tools.model.TAttribute;
import ast.tools.model.TMethod;
import ast.tools.model.TModifier;
import ast.tools.model.TParameter;

public class ASTVisitor extends ExtendedASTVisitor {

	protected ASTContext context;
	protected ASTProcessor processor;

	public ASTVisitor(ASTProcessor processor) {
		super();
		this.processor = processor;
	}

	@Override
	public boolean visit(PackageDeclaration node) {
		this.packageName = node.getName().toString();
		return super.visit(node);
	}

	@Override
	public boolean visit(ImportDeclaration node) {
		imports.add(new TImportImpl(node.getName().toString()));
		processor.notifyImportObservers(node.getName().toString());
		return super.visit(node);
	}

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
			// Notifica os observadores da classe (ASTClassObservers)
			processor.notifyClassObservers(this.className, this.genericTypeArguments, this.superClassName,
					this.superClassGenericTypeArguments, this.annotations, this.interfaces);
		} else {
			this.className = declaration.getName().toString();
			if (declaration.getSuperclassType() != null) {
				// this.superClassName = node.getSuperclassType().toString();
			}
			processor.notifyInterfaceObservers(this.className, this.superClassName, this.genericTypeArguments);
		}
		return super.visit(declaration);
	}

	@Override
	public boolean visit(FieldDeclaration declaration) {
		String name = getName(declaration);
		List<String> types = getTypes(declaration);
		String genericType = getGenericType(declaration);
		Set<TAnnotation> attributeAnnotations = processAnnotations(declaration);
		Set<TModifier> modifiers = getModifiers(declaration);

		// cria o objeto TAttribute
		TAttribute attribute = new TAttributeImpl(name, types, genericType, attributeAnnotations, modifiers);

		this.attributes.add(attribute);

		// notifica os observadores de attributos (ASTAttributeObservers)
		processor.notifyAttributeObservers(name, types, genericType, modifiers, attributeAnnotations);

		return super.visit(declaration);
	}

	@Override
	public boolean visit(MethodDeclaration declaration) {
		String name = getName(declaration);
		String returnGenericType = getReturnGenericType(declaration);
		boolean constructor = declaration.getReturnType2() == null;
		List<String> returnTypes = getReturnTypes(declaration);
		Set<TAnnotation> methodAnnotations = processAnnotations(declaration);
		Set<TModifier> modifiers = getModifiers(declaration);
		Set<TParameter> parameters = getParameters(declaration);

		TMethod method = new TMethodImpl(name, parameters, methodAnnotations, modifiers, returnTypes, returnGenericType, constructor);

		this.methods.add(method);

		processor.notifyMethodObservers(name, returnTypes, returnGenericType, modifiers, parameters, methodAnnotations, constructor);

		return super.visit(declaration);
	}

	public ASTContext getContext() {
		return this.context == null ? new ASTContextImpl(isInterface, className, annotations, attributes, methods, imports,
				interfaces, packageName, superClassName, genericTypeArguments, superClassGenericTypeArguments)
		: this.context;
	}

}
