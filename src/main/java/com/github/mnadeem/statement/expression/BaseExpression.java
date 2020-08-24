package com.github.mnadeem.statement.expression;

public abstract class BaseExpression implements Expression {

	private String name;

	public BaseExpression(String name) {
		this.name = name;
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
