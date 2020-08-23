package com.github.mnadeem.statement;

public class MutableBoolean {
	
	private Boolean value = null;
	
	private MutableBoolean(Boolean value) {
		this.value = value;
	}

	public static MutableBoolean ofFalse() {
		return new MutableBoolean(Boolean.FALSE);
	}
	
	public static MutableBoolean ofTrue() {
		return new MutableBoolean(Boolean.TRUE);
	}
	
	public void setValue(Boolean value) {
		this.value = value;
	}

	public void setFalse() {
		this.value = Boolean.FALSE;
	}
	
	public void setTrue() {
		this.value = Boolean.TRUE;
	}
	
	public Boolean getValue() {
		return value;
	}
	
	public Boolean isTrue() {
		return value == Boolean.TRUE;
	}
	
	public Boolean isFalse() {
		return value == Boolean.FALSE;
	}
}
