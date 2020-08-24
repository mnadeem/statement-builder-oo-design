package com.github.mnadeem.statement.expression;

import com.github.mnadeem.statement.Conjunction;
import com.github.mnadeem.statement.Statement;
import com.github.mnadeem.statement.Statements;

public class CompositeExpression implements Expression {
	
	@Override
	public boolean evaluate(Statements statements, int statementIndex, NameValue nameValue) {
		boolean result = true;
		
		int currentStatement = -1;
		for (Statement statement : statements.getAll()) {
			currentStatement++;
			Expression expression = Expressions.get(statement);
			if (expression == null) {
				throw new IllegalStateException("Exceptin not found for " + statement.getLhs());
			}
			result = expression.evaluate(statements, currentStatement, nameValue);
			Conjunction conjunction = statement.getConjunction();
			if (conjunction != null && conjunction.isAnd() && !result) {
				break ;
			} else if (conjunction != null && conjunction.isOr() && result) {
				break ;
			}
		}

		return result;
	}

	@Override
	public String getName() {
		return null;
	}
}
