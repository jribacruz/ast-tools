package ast.tools.helper;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import ast.tools.model.TModifier;

public class MethodDeclarationHelper {
	private String name;
	private Set<TModifier> modifiers;
	private boolean constructor;
	private Block body;
	private AST ast;
	private MethodDeclaration declaration;

	public MethodDeclarationHelper(AST ast, String name, Set<TModifier> modifier, boolean constructor) {
		super();
		this.name = name;
		this.modifiers = modifier;
		this.constructor = constructor;
		this.ast = ast;
		this.declaration = ast.newMethodDeclaration();
		this.init();
	}

	public MethodDeclarationHelper(AST ast, String name, Set<TModifier> modifier) {
		super();
		this.name = name;
		this.modifiers = modifier;
		this.constructor = false;
		this.ast = ast;
		this.declaration = ast.newMethodDeclaration();
		this.init();
	}

	private void init() {
		declaration.setName(ast.newSimpleName(this.name));
		declaration.setConstructor(this.constructor);
		this.body = ast.newBlock();
		declaration.setBody(this.body);
		this.setModifiers();
	}

	@SuppressWarnings("unchecked")
	private void setModifiers() {
		Iterator<TModifier> iterator = this.modifiers.iterator();
		while(iterator.hasNext()) {
			TModifier modifier = iterator.next();
			this.declaration.modifiers().addAll(ast.newModifiers(modifier.getModifierType()));
		}
	}


	public MethodDeclaration getMethodDeclaration() {
		return this.declaration;
	}

	public Block getBody() {
		return this.declaration.getBody();
	}

	public void setReturnType(PrimitiveType.Code type) {
		this.declaration.setReturnType2(ast.newPrimitiveType(type));
	}

	public void setReturnType(String type) {
		this.declaration.setReturnType2(ast.newSimpleType(ast.newSimpleName(type)));
	}

	@SuppressWarnings("unchecked")
	public void setReturnType(String genericType, String... types) {
		ParameterizedType parameterizedType = ast.newParameterizedType(ast.newSimpleType(ast.newSimpleName(genericType)));
		for (String type : types) {
			parameterizedType.typeArguments().add(ast.newSimpleType(ast.newSimpleName(type)));
		}
		this.declaration.setReturnType2(parameterizedType);
	}

	@SuppressWarnings("unchecked")
	public void addParameter(String name, PrimitiveType.Code type) {
		SingleVariableDeclaration singleVariableDeclaration = ast.newSingleVariableDeclaration();
		singleVariableDeclaration.setName(ast.newSimpleName(name));
		singleVariableDeclaration.setType(ast.newPrimitiveType(type));
		this.declaration.parameters().add(singleVariableDeclaration);
	}

	@SuppressWarnings("unchecked")
	public void addParameter(String name, String type) {
		SingleVariableDeclaration singleVariableDeclaration = ast.newSingleVariableDeclaration();
		singleVariableDeclaration.setName(ast.newSimpleName(name));
		singleVariableDeclaration.setType(ast.newSimpleType(ast.newSimpleName(type)));
		this.declaration.parameters().add(singleVariableDeclaration);
	}

	@SuppressWarnings("unchecked")
	public void addParameter(String name, String genericType, String... types) {
		SingleVariableDeclaration singleVariableDeclaration = ast.newSingleVariableDeclaration();
		singleVariableDeclaration.setName(ast.newSimpleName(name));

		ParameterizedType parameterizedType = ast.newParameterizedType(ast.newSimpleType(ast.newSimpleName(genericType)));
		for (String type : types) {
			parameterizedType.typeArguments().add(ast.newSimpleType(ast.newSimpleName(type)));
		}
		singleVariableDeclaration.setType(parameterizedType);

		this.declaration.parameters().add(singleVariableDeclaration);
	}

}
