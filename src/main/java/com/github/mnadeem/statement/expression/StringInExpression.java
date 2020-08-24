package com.github.mnadeem.statement.expression;

import java.util.Arrays;

import com.github.mnadeem.statement.Statement;
import com.github.mnadeem.statement.Statements;

public class StringInExpression extends BaseExpression {

	public StringInExpression(String name) {
		super(name);
	}

	@Override
	public boolean evaluate(Statements statements, int statementIndex, NameValue nameValue) {
		Statement statement = statements.get(statementIndex);
		String value = getValue(nameValue);
		return Arrays.asList(statement.getCsv().split(",")).contains(value);
	}
}
