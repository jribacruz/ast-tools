package ast.tools.helper;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import ast.tools.model.TModifier;
import ast.tools.util.TUtils;

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

	public MethodDeclarationHelper(AST ast, String name, Set<TModifier> modifiers) {
		super();
		this.name = name;
		this.modifiers = modifiers;
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
	public void setNullReturn() {
		ReturnStatement returnStatement = ast.newReturnStatement();
		NullLiteral nullLiteral = ast.newNullLiteral();
		returnStatement.setExpression(nullLiteral);
		this.body.statements().add(returnStatement);
	}

	@SuppressWarnings("unchecked")
	private void setModifiers() {
		Iterator<TModifier> iterator = this.modifiers.iterator();
		while (iterator.hasNext()) {
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

	private void setReturnType(PrimitiveType.Code type) {
		this.declaration.setReturnType2(ast.newPrimitiveType(type));
	}

	private void setReturnType(String type) {
		if (TUtils.isPrimitiveType(type)) {
			this.setReturnType(TUtils.stringToPrimitiveCode(type));
		} else {
			this.declaration.setReturnType2(ast.newSimpleType(ast.newSimpleName(type)));
		}
	}

	@SuppressWarnings("unchecked")
	public void setReturnType(String genericType, String... types) {
		if (!StringUtils.isEmpty(genericType)) {

			ParameterizedType parameterizedType = ast
					.newParameterizedType(ast.newSimpleType(ast.newSimpleName(genericType)));
			for (String type : types) {
				parameterizedType.typeArguments().add(ast.newSimpleType(ast.newSimpleName(type)));
			}
			this.declaration.setReturnType2(parameterizedType);
		} else {
			if (types != null && types.length == 1) {
				this.setReturnType(types[0]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void addParameter(String name, PrimitiveType.Code type) {
		SingleVariableDeclaration singleVariableDeclaration = ast.newSingleVariableDeclaration();
		singleVariableDeclaration.setName(ast.newSimpleName(name));
		singleVariableDeclaration.setType(ast.newPrimitiveType(type));
		this.declaration.parameters().add(singleVariableDeclaration);
	}

	@SuppressWarnings("unchecked")
	private void addParameter(String name, String type) {
		if (TUtils.isPrimitiveType(type)) {
			this.addParameter(name, TUtils.stringToPrimitiveCode(type));
		} else {
			SingleVariableDeclaration singleVariableDeclaration = ast.newSingleVariableDeclaration();
			singleVariableDeclaration.setName(ast.newSimpleName(name));
			singleVariableDeclaration.setType(ast.newSimpleType(ast.newSimpleName(type)));
			this.declaration.parameters().add(singleVariableDeclaration);
		}
	}

	@SuppressWarnings("unchecked")
	public void addParameter(String name, String genericType, String... types) {

		if (!StringUtils.isEmpty(genericType)) {

			SingleVariableDeclaration singleVariableDeclaration = ast.newSingleVariableDeclaration();
			singleVariableDeclaration.setName(ast.newSimpleName(name));

			ParameterizedType parameterizedType = ast
					.newParameterizedType(ast.newSimpleType(ast.newSimpleName(genericType)));
			for (String type : types) {
				parameterizedType.typeArguments().add(ast.newSimpleType(ast.newSimpleName(type)));
			}
			singleVariableDeclaration.setType(parameterizedType);

			this.declaration.parameters().add(singleVariableDeclaration);
		} else {
			if (types != null && types.length == 1) {
				this.addParameter(name, types[0]);
			}
		}
	}

}
