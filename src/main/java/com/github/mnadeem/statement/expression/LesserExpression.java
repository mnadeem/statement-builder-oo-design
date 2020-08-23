package com.github.mnadeem.statement.expression;

import com.github.mnadeem.statement.Operator;
import com.github.mnadeem.statement.Statement;
import com.github.mnadeem.statement.Statements;

public class LesserExpression extends BaseExpression {
	
	public LesserExpression(String name) {
		super(name, Operator.LESS);
	}

	@Override
	public boolean evaluate(Statements statements, int statementIndex, NameValue nameValue) {
		Statement statement = statements.get(statementIndex);
		return Integer.valueOf(getValue(nameValue)) < Integer.valueOf(statement.getCsv())  ? true : false;
	}

}
