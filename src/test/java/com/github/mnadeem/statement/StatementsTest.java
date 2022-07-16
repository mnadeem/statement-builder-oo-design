package com.github.mnadeem.statement;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

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
		assertThat(firstStatement.getConjunction(), equalTo(null));
	}
	
	@Test
	public void singleStatementValueContainingSpaces() {
		String raw = "Asabasef == A B C";
		Statements statements = new Statements(raw);
		Statement firstStatement = statements.get(0);

		assertThat(statements.containsNStatements(1), equalTo(true));
		assertThat(firstStatement.getLhs(), equalTo("Asabasef"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.EQUALS));
		assertThat(firstStatement.getCsv(), equalTo("A B C"));
		assertThat(firstStatement.getConjunction(), equalTo(null));
	}
	
	@Test
	public void singleStatementValueContainingSpacesWithVariables() {
		String raw = "Asabasef == A B C";
		Statements statements = new Statements(Arrays.asList("Asabasef"), raw);
		Statement firstStatement = statements.get(0);

		assertThat(statements.containsNStatements(1), equalTo(true));
		assertThat(firstStatement.getLhs(), equalTo("Asabasef"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.EQUALS));
		assertThat(firstStatement.getCsv(), equalTo("A B C"));
		assertThat(firstStatement.getConjunction(), equalTo(null));
	}
	
	@Test
	public void multipleStatementsValueContainingSpacesWithVariablesFs() {
		String raw = "SOFTWARE_COST >= 23 45 AND INFRA_COST <= 900 and TOTAL_COST IN 500,500,700";
		Statements statements = new Statements(Arrays.asList("SOFTWARE_COST", "INFRA_COST", "TOTAL_COST"), raw);
		
		assertThat(statements.containsNStatements(3), equalTo(true));
		
		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23 45"));
		assertThat(firstStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900"));	
		assertThat(secondStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500,500,700"));
		assertThat(thirdStatement.getConjunction(), equalTo(null));
	}
	
	@Test
	public void multipleStatementsValueContainingSpacesWithVariablesSs() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 456 and TOTAL_COST IN 500,500,700";
		Statements statements = new Statements(Arrays.asList("SOFTWARE_COST", "INFRA_COST", "TOTAL_COST"), raw);
		
		assertThat(statements.containsNStatements(3), equalTo(true));
		
		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		assertThat(firstStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900 456"));	
		assertThat(secondStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500,500,700"));	
		assertThat(thirdStatement.getConjunction(), equalTo(null));
	}
	
	@Test
	public void multipleStatementsValueContainingSpacesWithVariablesTs() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and TOTAL_COST IN 500 500 700 2234";
		Statements statements = new Statements(Arrays.asList("SOFTWARE_COST", "INFRA_COST", "TOTAL_COST"), raw);
		
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
		assertThat(thirdStatement.getCsv(), equalTo("500 500 700 2234"));	
	}
	
	
	@Test
	public void multipleStatementsValueContainingSpacesWithVariablesTsPf() {
		String raw = "( SOFTWARE_COST >= 23 ) AND INFRA_COST <= 900 and TOTAL_COST IN 500 500 700 2234";
		Statements statements = new Statements(Arrays.asList("SOFTWARE_COST", "INFRA_COST", "TOTAL_COST"), raw);
		
		assertThat(statements.containsNStatements(3), equalTo(true));
		
		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		assertThat(firstStatement.isParanBegins(), equalTo(true));
		assertThat(firstStatement.isParanEnds(), equalTo(true));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900"));
		assertThat(secondStatement.isParanBegins(), equalTo(false));
		assertThat(secondStatement.isParanEnds(), equalTo(false));
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500 500 700 2234"));	
		assertThat(thirdStatement.isParanBegins(), equalTo(false));
		assertThat(thirdStatement.isParanEnds(), equalTo(false));
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
		assertThat(firstStatement.getConjunction(), equalTo(null));
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
		assertThat(firstStatement.getConjunction(), equalTo(null));
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
		assertThat(firstStatement.getConjunction(), equalTo(null));
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
		assertThat(firstStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS));
		assertThat(secondStatement.getCsv(), equalTo("900"));
		assertThat(secondStatement.getConjunction(), equalTo(null));
		
	}
	
	@Test
	public void threeStatementsFirstGreaterOrEqualSecondLesserOrEqualThirdIN() {
		String raw = "SOFTWARE_COST >= 23 OR INFRA_COST <= 900 and TOTAL_COST IN 500,500,700";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));
		
		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		assertThat(firstStatement.getConjunction(), equalTo(Conjunction.OR));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900"));	
		assertThat(secondStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500,500,700"));	
		assertThat(thirdStatement.getConjunction(), equalTo(null));
		
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
	
	@Test(expected = IllegalStateException.class)
	public void noEndParan() {
		String raw = "( SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and TOTAL_COST IN 500,500,700";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));
		assertThat(statements.getNames().contains("SOFTWARE_COST"), equalTo(true));
		assertThat(statements.getNames().contains("INFRA_COST"), equalTo(true));
		assertThat(statements.getNames().contains("TOTAL_COST"), equalTo(true));
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void noStartParan() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and TOTAL_COST IN 500,500,700 )";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));
		assertThat(statements.getNames().contains("SOFTWARE_COST"), equalTo(true));
		assertThat(statements.getNames().contains("INFRA_COST"), equalTo(true));
		assertThat(statements.getNames().contains("TOTAL_COST"), equalTo(true));
		
	}
	
	@Test
	public void paranStartAtOneAndEndAt2() {
		String raw = "( SOFTWARE_COST >= 23 AND INFRA_COST <= 900 ) and TOTAL_COST IN 500,500,700";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));

		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		assertThat(firstStatement.isParanBegins(), equalTo(true));
		assertThat(firstStatement.isParanEnds(), equalTo(false));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900"));
		assertThat(secondStatement.isParanBegins(), equalTo(false));
		assertThat(secondStatement.isParanEnds(), equalTo(true));
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500,500,700"));
		assertThat(thirdStatement.isParanBegins(), equalTo(false));
		assertThat(thirdStatement.isParanEnds(), equalTo(false));
	}
	
	@Test
	public void paranStartAtOneAndEndAt3() {
		String raw = "( SOFTWARE_COST   >= 23 AND   INFRA_COST <= 900 and TOTAL_COST IN 500,500,700   )";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));

		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		assertThat(firstStatement.isParanBegins(), equalTo(true));
		assertThat(firstStatement.isParanEnds(), equalTo(false));
		assertThat(firstStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900"));
		assertThat(secondStatement.isParanBegins(), equalTo(false));
		assertThat(secondStatement.isParanEnds(), equalTo(false));
		assertThat(secondStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500,500,700"));
		assertThat(thirdStatement.isParanBegins(), equalTo(false));
		assertThat(thirdStatement.isParanEnds(), equalTo(true));
		assertThat(thirdStatement.getConjunction(), equalTo(null));
	}
	
	@Test
	public void paranStartAtTwoAndEndAt3() {
		String raw = "SOFTWARE_COST   >= 23 AND (  INFRA_COST <= 900 and TOTAL_COST IN 500,500,700   )";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));

		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		assertThat(firstStatement.isParanBegins(), equalTo(false));
		assertThat(firstStatement.isParanEnds(), equalTo(false));
		assertThat(firstStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900"));
		assertThat(secondStatement.isParanBegins(), equalTo(true));
		assertThat(secondStatement.isParanEnds(), equalTo(false));
		assertThat(secondStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500,500,700"));
		assertThat(thirdStatement.isParanBegins(), equalTo(false));
		assertThat(thirdStatement.isParanEnds(), equalTo(true));
		assertThat(thirdStatement.getConjunction(), equalTo(null));
	}

	@Test
	public void paranStartAtThreeAndEndAt3() {
		String raw = "SOFTWARE_COST   >= 23 AND   INFRA_COST <= 900 and ( TOTAL_COST IN 500,500,700   )";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));

		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		assertThat(firstStatement.isParanBegins(), equalTo(false));
		assertThat(firstStatement.isParanEnds(), equalTo(false));
		assertThat(firstStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900"));
		assertThat(secondStatement.isParanBegins(), equalTo(false));
		assertThat(secondStatement.isParanEnds(), equalTo(false));
		assertThat(secondStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500,500,700"));
		assertThat(thirdStatement.isParanBegins(), equalTo(true));
		assertThat(thirdStatement.isParanEnds(), equalTo(true));
		assertThat(thirdStatement.getConjunction(), equalTo(null));
	}
	
	@Test
	public void paranStartAt1AndEndAt1() {
		String raw = " ( SOFTWARE_COST   >= 23 ) AND   INFRA_COST <= 900 and ( TOTAL_COST IN 500,500,700   )";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));

		Statement firstStatement = statements.get(0);
		assertThat(firstStatement.getLhs(), equalTo("SOFTWARE_COST"));
		assertThat(firstStatement.getOperator(), equalTo(Operator.GREATER_OR_EQUAL));
		assertThat(firstStatement.getCsv(), equalTo("23"));
		assertThat(firstStatement.isParanBegins(), equalTo(true));
		assertThat(firstStatement.isParanEnds(), equalTo(true));
		assertThat(firstStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement secondStatement = statements.get(1);
		assertThat(secondStatement.getLhs(), equalTo("INFRA_COST"));
		assertThat(secondStatement.getOperator(), equalTo(Operator.LESS_OR_EQUAL));
		assertThat(secondStatement.getCsv(), equalTo("900"));
		assertThat(secondStatement.isParanBegins(), equalTo(false));
		assertThat(secondStatement.isParanEnds(), equalTo(false));
		assertThat(secondStatement.getConjunction(), equalTo(Conjunction.AND));
		
		Statement thirdStatement = statements.get(2);
		assertThat(thirdStatement.getLhs(), equalTo("TOTAL_COST"));
		assertThat(thirdStatement.getOperator(), equalTo(Operator.IN));
		assertThat(thirdStatement.getCsv(), equalTo("500,500,700"));
		assertThat(thirdStatement.isParanBegins(), equalTo(true));
		assertThat(thirdStatement.isParanEnds(), equalTo(true));
		assertThat(thirdStatement.getConjunction(), equalTo(null));
	}
}
