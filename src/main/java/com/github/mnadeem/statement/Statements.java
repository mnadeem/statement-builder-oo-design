package com.github.mnadeem.statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Statements {

	private final List<Statement> statements;

	public Statements(String raw) {
		this.statements = build(raw);
	}

	private List<Statement> build(String raw) {
		List<Statement> result = new ArrayList<Statement>();
		
		MutableInteger tokenIndex = MutableInteger.of(0);
		int tokenIndexStart = tokenIndex.get();

		List<String> tokens = Arrays.asList(raw.split(" "));

		for (Iterator<String> iterator = tokens.iterator(); iterator.hasNext();) {
			tokenIndexStart = tokenIndex.get();
			
			String token =  iterator.next();
			tokenIndex.increment();

			String lhs = token.trim();

			Operator operator = Operator.getOperator(iterator.next());
			tokenIndex.increment();

			String csv = extractCSV(operator, iterator, tokenIndex);

			Statement statement = new Statement(lhs, operator, csv, tokenIndexStart);
			result.add(statement);

			if (iterator.hasNext()) {
				Conjunction conjunction = Conjunction.getConjunction(iterator.next());
				tokenIndex.increment();
				statement.setConjunction(conjunction);
			}
		}

		return result;
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
