package com.github.mnadeem.statement.expression;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.github.mnadeem.statement.Statements;

public class StringInExpressionTest {
	
	@Test
	public void shouldAssertTrueForInValue() {
		String raw = "INFRA_COST IN 4,6,7 ";
		Statements statements = new Statements(raw);
		Expression expression = new StringInExpression(null);
		assertThat(expression.evaluate(statements, 0, NameValue.ofDefault("4")), equalTo(true));
	}
	
	@Test
	public void shouldAssertFalseForOutValue() {
		String raw = "INFRA_COST IN 4,6,7 ";
		Statements statements = new Statements(raw);
		Expression expression = new StringInExpression(null);
		assertThat(expression.evaluate(statements, 0, NameValue.ofDefault("2")), equalTo(false));
	}

}
