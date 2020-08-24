package com.github.mnadeem.statement.expression;

import com.github.mnadeem.statement.Statements;

public interface Expression {

	boolean evaluate(Statements statements, int statementIndex, NameValue nameValue);
	String getName();
}
