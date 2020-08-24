package com.github.mnadeem.statement.expression;

import com.github.mnadeem.statement.Statement;
import com.github.mnadeem.statement.Statements;

public class EqualsExpression extends BaseExpression {

	public EqualsExpression(String name) {
		super(name);
	}

	@Override
	public boolean evaluate(Statements statements, int statementIndex, NameValue nameValue) {
		Statement statement = statements.get(statementIndex);
		String value = getValue(nameValue);
		return value != null && Integer.valueOf(statement.getCsv()) == Integer.valueOf(value) ? true : false;
	}

}
