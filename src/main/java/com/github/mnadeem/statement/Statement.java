package com.github.mnadeem.statement;

public class Statement {

	private String lhs;
	private Operator operator;
	private String csv;
	private int index;
	private Conjunction conjunction;

	public Statement(String lhs, Operator operator, String csv, int index) {
		super();
		this.lhs = lhs;
		this.operator = operator;
		this.csv = csv;
		this.index = index;
	}

	public String getLhs() {
		return lhs;
	}

	public Operator getOperator() {
		return operator;
	}

	public String getCsv() {
		return csv;
	}

	public int getIndex() {
		return index;
	}

	public Conjunction getConjunction() {
		return conjunction;
	}

	public void setConjunction(Conjunction conjunction) {
		this.conjunction = conjunction;
	}

	@Override
	public String toString() {
		return "Statement [lhs=" + lhs + ", operator=" + operator + ", csv=" + csv + ", index=" + index
				+ ", conjunction=" + conjunction + "]";
	}
	
}
