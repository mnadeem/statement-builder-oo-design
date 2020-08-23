package com.github.mnadeem.statement;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class StatementsTest {
	
	@Test
	public void singleStatementGreater() {
		String raw = "Asabasef > 2";
		Statements statements = new Statements(raw);
		Statement firstStatement = statements.get(0);

		assertThat(statements.containsNStatements(1), equalTo(true));
		assertThat(firstStatement.getLhs(), equalTo("Asabasef"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER));
		assertThat(firstStatement.getCsv(), equalTo("2"));
	}
	
	@Test
	public void singleStatementLesser() {
		String raw = "AAABBBEC_DD < 10000";
		Statements statements = new Statements(raw);
		Statement firstStatement = statements.get(0);

		assertThat(statements.containsNStatements(1), equalTo(true));
		assertThat(firstStatement.getLhs(), equalTo("AAABBBEC_DD"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.LESS));
		assertThat(firstStatement.getCsv(), equalTo("10000"));
	}
	
	@Test
	public void singleStatementEquals() {
		String raw = "ddsdfFFFFFFFFFF == 90909090";
		Statements statements = new Statements(raw);
		Statement firstStatement = statements.get(0);

		assertThat(statements.containsNStatements(1), equalTo(true));
		assertThat(firstStatement.getLhs(), equalTo("ddsdfFFFFFFFFFF"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.EQUALS));
		assertThat(firstStatement.getCsv(), equalTo("90909090"));
	}
	
	@Test
	public void singleStatementIn() {
		String raw = "VALUE_IN IN 1,4,5,6,6";
		Statements statements = new Statements(raw);
		Statement firstStatement = statements.get(0);

		assertThat(statements.containsNStatements(1), equalTo(true));
		assertThat(firstStatement.getLhs(), equalTo("VALUE_IN"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.IN));
		assertThat(firstStatement.getCsv(), equalTo("1,4,5,6,6"));
	}
	
	@Test
	public void twoStatementsFirstGreaterSecondLesser() {
		String raw = "SOFTWARE_COST > 23 AND INFRA_COST < 900";
		Statements statements = new Statements(raw);
		

		assertThat(statements.containsNStatements(2), equalTo(true));
		
		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS));
		assertThat(secondStatement.getCsv(), equalTo("900"));	
		
	}
	
	@Test
	public void threeStatementsFirstGreaterOrEqualSecondLesserOrEqualThirdIN() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and TOTAL_COST IN 500,500,700";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));
		
		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900"));	
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500,500,700"));	
	}
	
	@Test
	public void names() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and TOTAL_COST IN 500,500,700";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));
		assertThat(statements.getNames().contains("SOFTWARE_COST"), equalTo(true));
		assertThat(statements.getNames().contains("INFRA_COST"), equalTo(true));
		assertThat(statements.getNames().contains("TOTAL_COST"), equalTo(true));
		
	}

}
