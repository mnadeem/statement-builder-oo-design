package com.github.mnadeem.statement.expression;

import java.util.Arrays;

import com.github.mnadeem.statement.Operator;
import com.github.mnadeem.statement.Statement;
import com.github.mnadeem.statement.Statements;

public class GenericExpression extends BaseExpression {

	public GenericExpression(String name) {
		super(name);
	}

	@Override
	public boolean evaluate(Statements statements, int statementIndex, NameValue nameValue) {
		String value = getValue(nameValue);
		Statement statement = statements.get(statementIndex);
		Operator operator = statement.getOperator();
		if (operator.isIN()) {
			return Arrays.asList(statement.getCsv().split(",")).contains(value); 
		} else if (operator.isGreater()) {
			return Integer.valueOf(value) > Integer.valueOf(statement.getCsv());
		} else if (operator.isGreaterOrEqual()) {
			return Integer.valueOf(value) >= Integer.valueOf(statement.getCsv());
		} else if (operator.isLess()) {
			return Integer.valueOf(value) < Integer.valueOf(statement.getCsv());
		} else if (operator.isLessOrEqual()) {
			return Integer.valueOf(value) <= Integer.valueOf(statement.getCsv());
		} else if (operator.isEquals()) {
			return Integer.valueOf(value) == Integer.valueOf(statement.getCsv());
		}		
		return false;
	}	
}
