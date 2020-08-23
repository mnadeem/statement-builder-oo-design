package com.github.mnadeem.statement;

public enum Operator {
	EQUALS("=="), IN("IN"), GREATER(">"), LESS("<"), GREATER_OR_EQUAL(">="), LESS_OR_EQUAL("<=");

	private String code;
	
	private Operator(String code) {
		this.code = code;
	}

	public static Operator getOperator(String value) {
		String trimmed = value.trim();
        for(Operator operator : values()) {
            if(operator.code.equalsIgnoreCase(trimmed)) { return operator; }
        }
        throw new IllegalArgumentException("Unknow Operator " + value);
    }

	public boolean isIN() {
		return IN == this;
	}

	public boolean isGreater() {
		return GREATER ==  this;
	}

	public boolean isGreaterOrEqual() {
		return GREATER_OR_EQUAL == this;
	}
	
	public boolean isEquals() {
		return EQUALS == this;
	}
	
	public boolean isLess() {
		return LESS == this;
	}
	
	public boolean isLessOrEqual() {
		return LESS_OR_EQUAL == this;
	}
}
