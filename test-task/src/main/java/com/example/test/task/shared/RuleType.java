package com.example.test.task.shared;

import java.io.Serializable;

public class RuleType extends NamedData implements Serializable {

	private static final long serialVersionUID = -4651772996557673291L;

	public RuleType(int id, String name) {
		super(id, name);
	}

	public RuleType() {
		super();
	}
	
}
