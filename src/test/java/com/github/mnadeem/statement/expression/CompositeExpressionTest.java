package com.github.mnadeem.statement.expression;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.github.mnadeem.statement.Statements;

public class CompositeExpressionTest {
	
	@Test
	public void shouldAssertTrueForSingleStatement() {
		String raw = "SOFTWARE_COST >= 23";
		Statements statements = new Statements(raw);
		Expression expression = statements.getExpression();
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, nameValue("25", "800", "ABC")), equalTo(true));
	}

	@Test
	public void shouldAssertFalseForSingleStatement() {
		String raw = "SOFTWARE_COST >= 23";
		Statements statements = new Statements(raw);
		Expression expression = statements.getExpression();
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, nameValue("20", "800", "ABC")), equalTo(false));
	}

	@Test
	public void shouldAssertTrueForEqualValue() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and CITY IN ABC,XYZ,PQR";
		Statements statements = new Statements(raw);
		Expression expression = statements.getExpression();
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, nameValue("25", "800", "ABC")), equalTo(true));
	}
	
	@Test
	public void shouldAssertFalseIfFirstParamDoesNotMatch() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and CITY IN ABC,XYZ,PQR";
		Statements statements = new Statements(raw);
		Expression expression = statements.getExpression();
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, nameValue("20", "800", "ABC")), equalTo(false));
	}
	
	@Test
	public void shouldAssertFalseIfSecondParamDoesNotMatch() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and CITY IN ABC,XYZ,PQR";
		Statements statements = new Statements(raw);
		Expression expression = statements.getExpression();
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, nameValue("25", "901", "ABC")), equalTo(false));
	}

	@Test
	public void shouldAssertFalseIfThirdParamDoesNotMatch() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and CITY IN ABC,XYZ,PQR";
		Statements statements = new Statements(raw);
		Expression expression = statements.getExpression();
		assertThat(expression.canHandle(statements), equalTo(true));
		assertThat(expression.evaluate(statements, 0, nameValue("25", "800", "APC")), equalTo(false));
	}

	private NameValue nameValue(String softCost, String infCost, String city) {
		NameValue result = new NameValue();
		result.put("SOFTWARE_COST", softCost);
		result.put("INFRA_COST", infCost);
		result.put("CITY", city);
		return result;
	}
}
