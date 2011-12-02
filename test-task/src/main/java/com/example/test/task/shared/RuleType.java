package com.example.test.task.shared;

import java.io.Serializable;

public class RuleType extends NamedData implements Serializable {

	private static final long serialVersionUID = -4651772996557673291L;
	
	boolean interval = false;

	
	public RuleType() {
		super();
	}

	public RuleType(int id, String name, boolean interval) {
		super(id, name);
		this.interval = interval;

	}

	public boolean isInterval() {
		return interval;
	}

	public void setInterval(boolean interval) {
		this.interval = interval;
	}
}
