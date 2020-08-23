package com.github.mnadeem.statement.expression;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.mnadeem.statement.Statement;

public class Expressions {

	public static final Map<String, Expression> EXPRESSIONS = Collections.unmodifiableMap(new HashMap<String, Expression>() {

		private static final long serialVersionUID = 1L;

		{
	        put("SOFTWARE_COST", new GenericExpression("SOFTWARE_COST"));
	        put("CITY", new StringInExpression("CITY"));
	        put("INFRA_COST", new LesserOrEqualExpression("INFRA_COST"));
	        put("COUNTRY", new StringInExpression("COUNTRY"));
	    }
	});

	public static Expression get(Statement statement) {
		return EXPRESSIONS.get(statement.getLhs());
	}

}
