package ast.tools.helper;

import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class MethodDeclarationHelper {
	private String name;
	private int modifier;
	private boolean constructor;
	private Block body;
	private AST ast;
	private MethodDeclaration declaration;

	public MethodDeclarationHelper(AST ast, String name, int modifier, boolean constructor) {
		super();
		this.name = name;
		this.modifier = modifier;
		this.constructor = constructor;
		this.ast = ast;
		this.declaration = ast.newMethodDeclaration();
		this.init();
	}

	public MethodDeclarationHelper(AST ast, String name, int modifier) {
		super();
		this.name = name;
		this.modifier = modifier;
		this.constructor = false;
		this.ast = ast;
		this.declaration = ast.newMethodDeclaration();
		this.init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		declaration.setName(ast.newSimpleName(this.name));
		List<?> declarationModifier = ast.newModifiers(this.modifier);
		declaration.modifiers().add(declarationModifier.get(0));
		declaration.setConstructor(this.constructor);
		this.body = ast.newBlock();
		declaration.setBody(this.body);
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
