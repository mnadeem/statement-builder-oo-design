# Object Oriented Framework To Parse and evaluate expressions

## Statements Identification

```java
@Test
	public void names() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and TOTAL_COST IN 500,500,700";
		Statements statements = new Statements(raw);

		assertThat(statements.containsNStatements(3), equalTo(true));
		assertThat(statements.getNames().contains("SOFTWARE_COST"), equalTo(true));
		assertThat(statements.getNames().contains("INFRA_COST"), equalTo(true));
		assertThat(statements.getNames().contains("TOTAL_COST"), equalTo(true));
		
	}

```

## Statements Evaluation

### Equals Expression

```
@Test
	public void shouldAssertTrueForEqualValue() {
		String raw = "Asabasef == 2 ";
		Statements statements = new Statements(raw);
		EqualsExpression expression = new EqualsExpression(null);
		assertThat(expression.evaluate(statements, 0, NameValue.ofDefault("2")), equalTo(true));
	}
```

### In Expression

```
	@Test
	public void shouldAssertFalseForOutValue() {
		String raw = "INFRA_COST IN 4,6,7 ";
		Statements statements = new Statements(raw);
		Expression expression = new StringInExpression(null);
		assertThat(expression.evaluate(statements, 0, NameValue.ofDefault("2")), equalTo(false));
	}

```

### Composite Expression

```

	@Test
	public void shouldAssertTrueForEqualValue() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and CITY IN ABC,XYZ,PQR";
		Statements statements = new Statements(raw);
		Expression expression = new CompositeExpression();
		assertThat(expression.evaluate(statements, 0, nameValue("25", "800", "ABC")), equalTo(true));
	}
	
	@Test
	public void shouldAssertFalseIfFirstParamDoesNotMatch() {
		String raw = "SOFTWARE_COST >= 23 AND INFRA_COST <= 900 and CITY IN ABC,XYZ,PQR";
		Statements statements = new Statements(raw);
		Expression expression = new CompositeExpression();
		assertThat(expression.evaluate(statements, 0, nameValue("20", "800", "ABC")), equalTo(false));
	}

```