package com.github.mnadeem.statement.expression;

import com.github.mnadeem.statement.Statement;
import com.github.mnadeem.statement.Statements;

public class GreaterExpression extends BaseExpression {

	public GreaterExpression(String name) {
		super(name);
	}

	@Override
	public boolean evaluate(Statements statements, int statementIndex, NameValue nameValue) {
		Statement statement = statements.get(statementIndex);
		return Integer.valueOf(getValue(nameValue)) > Integer.valueOf(statement.getCsv()) ? true : false;
	}
}
