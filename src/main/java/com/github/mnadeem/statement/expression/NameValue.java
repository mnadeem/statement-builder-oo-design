package com.github.mnadeem.statement.expression;

import java.util.HashMap;
import java.util.Map;

public class NameValue {

	private Map<String, String> nameValues = new HashMap<String, String>();
	private String KEY_DEFAULT = "*";

	public static NameValue ofDefault(String value) {
		NameValue nameValue = new NameValue();
		nameValue.addDefault(value);
		return nameValue;
	}
	
	public static NameValue of(String key, String value) {
		NameValue nameValue = new NameValue();
		nameValue.put(key, value);
		return nameValue;
	}

	public NameValue addDefault(String value) {
		this.nameValues.put(KEY_DEFAULT, value);
		return this;
	}

	public NameValue put(String key, String value) {
		this.nameValues.put(key, value);
		return this;
	}
	
	public String get(String key) {
		return this.nameValues.get(key);
	}

	public String getDefault() {
		return this.nameValues.get(KEY_DEFAULT);
	}	

}
