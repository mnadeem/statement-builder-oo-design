package com.github.mnadeem.statement;

public class MutableInteger {
	
	private Integer value;
	
	private MutableInteger(Integer value) {
		this.value = value;
	}

	public static MutableInteger of(Integer val) {
		return new MutableInteger(val);
	}

	public void increment() {
		this.value = value + 1;
	}
	
	public Integer get() {
		return value;
	}

}
