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

			Statement statement = buildStatement(token, tokenIndex, iterator);
			token = null;

			if (currentParanBegins) {
				statement.setParanBegins(true);
			}

			if (iterator.hasNext()) {
				token =  iterator.next();
				tokenIndex.increment();
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

	private Statement buildStatement(String token, MutableInteger tokenIndex, Iterator<String> iterator) {
		int tokenIndexStart = tokenIndex.get();

		String lhs = token.trim();

		if (validationRequired && !validVariables.contains(lhs)) {
			throw new IllegalArgumentException("Invalid Variable : " + lhs);
		}

		Operator operator = Operator.getOperator(iterator.next());
		tokenIndex.increment();

		String csv = extractCSV(operator, iterator, tokenIndex);

		Statement statement = new Statement(lhs, operator, csv, tokenIndexStart);
		return statement;
	}

	private String extractCSV(Operator operator, Iterator<String> iterator, MutableInteger tokenIndex) {
		String token =  iterator.next().trim();
		tokenIndex.increment();
		return token;
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
}
