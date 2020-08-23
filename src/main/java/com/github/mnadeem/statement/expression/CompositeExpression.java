package com.github.mnadeem.statement.expression;

import java.util.List;

import com.github.mnadeem.statement.Conjunction;
import com.github.mnadeem.statement.Statement;
import com.github.mnadeem.statement.Statements;

public class CompositeExpression implements Expression {

	private List<String> names;

	public CompositeExpression(List<String> names) {
		this.names = names;
	}

	@Override
	public boolean canHandle(Statements statements) {
		return statements.containsNStatements(this.names.size()) && shouldEqual(statements);
	}

	private boolean shouldEqual(Statements statements) {
		for (int i = 0; i < names.size(); i++) {
			if (!this.names.get(i).equalsIgnoreCase(statements.get(i).getLhs())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean evaluate(Statements statements, int statementIndex, NameValue nameValue) {
		boolean result = true;
		
		int currentStatement = -1;
		for (Statement statement : statements.getAll()) {
			currentStatement++;
			Expression expression = Expressions.get(statement);
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
