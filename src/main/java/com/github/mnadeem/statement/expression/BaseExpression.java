package com.github.mnadeem.statement.expression;

import com.github.mnadeem.statement.Operator;
import com.github.mnadeem.statement.Statements;

public abstract class BaseExpression implements Expression {

	private String name;
	private Operator operator;

	public BaseExpression(String name, Operator operator) {
		this.name = name;
		this.operator = operator;
	}

	@Override
	public boolean canHandle(Statements statements) {
		return statements.containsNStatements(1) && statements.firstStatementHas(operator) && isFirstNameValid(statements);
	}

	protected boolean isFirstNameValid(Statements statements) {
		if (name == null) {
			return true;
		}
		return statements.get(0).getLhs().equalsIgnoreCase(name);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String getValue(NameValue nameValue) {
		String value = getName() == null ? nameValue.getDefault() : nameValue.get(getName());
		return value == null ? value : value.trim();
	}
}
