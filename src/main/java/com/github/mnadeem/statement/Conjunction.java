package com.github.mnadeem.statement;

public enum Conjunction {
	AND("and"), OR("or");
	
	private String code;

	private Conjunction(String code) {
		this.code = code;
	}

	public static Conjunction getConjunction(String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		String trimmed = value.trim();
        for(Conjunction conj : values()) {
            if(conj.code.equalsIgnoreCase(trimmed)) { return conj; }
        }
        throw new IllegalArgumentException("Unknow Conjunction " + value);
    }
	
	public boolean isAnd() {
		return AND == this;
	}
	
	public boolean isOr() {
		return OR == this;
	}
}
