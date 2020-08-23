package com.github.mnadeem.statement.expression;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.github.mnadeem.statement.Statements;

public class EqualsExpressionTest {

	@Test
	public void shouldAssertTrueForEqualValue() {
		String raw = "Asabasef == 2 ";
		Statements statements = new Statements(raw);
		EqualsExpression expression = new EqualsExpression(null);
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, NameValue.ofDefault("2")), equalTo(true));
	}

	@Test
	public void shouldAssertFalseForGreaterValue() {
		String raw = "Asabasef == 2 ";
		Statements statements = new Statements(raw);
		EqualsExpression expression = new EqualsExpression(null);
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, NameValue.ofDefault("3")), equalTo(false));
	}

	@Test
	public void shouldAssertFalseForLesserValue() {
		String raw = "Asabasef == 2 ";
		Statements statements = new Statements(raw);
		EqualsExpression expression = new EqualsExpression(null);
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, NameValue.ofDefault("1")), equalTo(false));
	}

	@Test
	public void shouldAssertTrueForNamedStatement() {
		String raw = "SOFT_COST == 2 ";
		Statements statements = new Statements(raw);
		EqualsExpression expression = new EqualsExpression("SOFT_COST");
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, NameValue.ofDefault("1")), equalTo(false));
	}
	
	@Test
	public void shouldAssertFalseForNamedStatement() {
		String raw = "SOFT_COST == 2 ";
		Statements statements = new Statements(raw);
		EqualsExpression expression = new EqualsExpression("sdf");
		assertThat(expression.canHandle(statements), equalTo(false));
		assertThat(expression.evaluate(statements, 0, NameValue.ofDefault("1")), equalTo(false));
	}
}
