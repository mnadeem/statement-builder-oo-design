package com.github.mnadeem.statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Statements {

	private static final String PARANTHESES_START = "(";
	private static final String PARANTHESES_END = ")";
	private static final String SEPERATOR_SPACE = " ";

	private final List<Statement> statements;
	private final boolean validationRequired;
	private final List<String> validVariables;

	public Statements(String raw) {
		this(Collections.emptyList(), raw);
	}

	public Statements(List<String> validVariables, String raw) {
		this.validationRequired = (validVariables != null && !validVariables.isEmpty());
		this.validVariables = validVariables;
		this.statements = build(raw);
	}

	private List<Statement> build(String raw) {
		List<Statement> result = new ArrayList<Statement>();

		MutableInteger tokenIndex = MutableInteger.of(0);

		List<String> tokens = Arrays.asList(raw.trim().split("\\s+"));
		boolean paranBegins = false;
		boolean paranEnds = false;

		for (Iterator<String> iterator = tokens.iterator(); iterator.hasNext();) {
			
			boolean currentParanBegins = false;

			String token =  iterator.next();
			tokenIndex.increment();

			if (isCompositeStart(token)) {
				token =  iterator.next();
				tokenIndex.increment();
				paranBegins = true;
				currentParanBegins = true;
			}

			StatementToken statementToken = buildStatement(token, tokenIndex, iterator);
			Statement statement = statementToken.getStatement();
			token = statementToken.getNextToken();

			if (currentParanBegins) {
				statement.setParanBegins(true);
			}

			if (isCompositeEnd(token)) {
				paranEnds = true;

				if (!paranBegins) {
					throw new IllegalStateException("Parantheses Does not start");
				}

				statement.setParanEnds(true);
				if (iterator.hasNext()) {
					token =  iterator.next();
					tokenIndex.increment();
				}
				if (Conjunction.isConjunction(token)) {
					statement.setConjunction(Conjunction.getConjunction(token));
				}
			} else {
				Conjunction conjunction = Conjunction.getConjunction(token);
				statement.setConjunction(conjunction);
			}

			result.add(statement);
		}

		if (paranBegins && !paranEnds) {
			throw new IllegalStateException("Parantheses Not properly closed!");
		}

		return result;
	}

	private boolean isCompositeEnd(String token) {
		return token != null && PARANTHESES_END.equals(token.trim());
	}

	private boolean isCompositeStart(String token) {
		return token != null && PARANTHESES_START.equals(token.trim());
	}

	private StatementToken buildStatement(String token, MutableInteger tokenIndex, Iterator<String> iterator) {
		int tokenIndexStart = tokenIndex.get();

		String lhs = token.trim();

		if (validationRequired && !validVariables.contains(lhs)) {
			throw new IllegalArgumentException("Invalid Variable : " + lhs);
		}

		Operator operator = Operator.getOperator(iterator.next());
		tokenIndex.increment();

		CsvToken csvToken = extractCSV(operator, iterator, tokenIndex);

		Statement statement = new Statement(lhs, operator, csvToken.getCsv(), tokenIndexStart);
		return new StatementToken(statement, csvToken.getNextToken());
	}

	private CsvToken extractCSV(Operator operator, Iterator<String> iterator, MutableInteger tokenIndex) {
		StringBuilder result = new StringBuilder();
		String nextToken = null;

		String token =  iterator.next();
		result.append(token);
		tokenIndex.increment();
		
		while(iterator.hasNext()) {
			token =  iterator.next();
			tokenIndex.increment();

			if (token != null && (!PARANTHESES_END.equals(token.trim()) && !Conjunction.isConjunction(token) && !Operator.isOperator(token) && !validVariables.contains(token.trim()))) {
				result.append(SEPERATOR_SPACE).append(token);
			} else {
				nextToken = token != null ? token.trim() : null;
				break;
			}
		}

		return new CsvToken(result.toString(), nextToken);
	}

	public void forEach(Consumer<Statement> consumer) {
		this.statements.forEach(consumer);
	}

	public List<Statement> getAll() {
		return new ArrayList<Statement>(this.statements);
	}
	
	public List<String> getNames() {
		return this.statements.stream().map(Statement::getLhs).collect(Collectors.toList());
	}
	
	public boolean containsNStatements(int n) {
		return this.statements.size() == n;
	}

	public boolean firstStatementHas(Operator operator) {
		return this.statements.get(0).getOperator() == operator;
	}
	
	public Statement get(int index) {
		return this.statements.get(index);
	}

	private static class CsvToken {
		private String csv;
		private String nextToken;
		
		public CsvToken(String csv, String nextToken) {
			this.csv = csv;
			this.nextToken = nextToken;
		}

		public String getCsv() {
			return csv;
		}

		public String getNextToken() {
			return nextToken;
		}
	}
	
	private static class StatementToken {
		private Statement statement;
		private String nextToken;
		
		public StatementToken(Statement statement, String nextToken) {
			this.statement = statement;
			this.nextToken = nextToken;
		}

		public Statement getStatement() {
			return statement;
		}

		public String getNextToken() {
			return nextToken;
		}
	}
}
