package ast.tools.helper;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import ast.tools.model.TModifier;

public class FieldDeclarationHelper {
	private AST ast;
	private FieldDeclaration declaration;
	private String name;
	private Set<TModifier> modifiers;

	public FieldDeclarationHelper(AST ast, String name, Set<TModifier> modifiers) {
		super();
		this.ast = ast;
		this.name = name;
		this.modifiers = modifiers;
		this.init();
	}

	private void init() {
		VariableDeclarationFragment fragment = ast.newVariableDeclarationFragment();
		fragment.setName(ast.newSimpleName(this.name));
		this.declaration = ast.newFieldDeclaration(fragment);
		setModifiers();
	}

	@SuppressWarnings("unchecked")
	private void setModifiers() {
		Iterator<TModifier> iterator = this.modifiers.iterator();
		while(iterator.hasNext()) {
			TModifier modifier = iterator.next();
			this.declaration.modifiers().add(modifier.getModifierType());
		}
	}



}
