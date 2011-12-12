package com.example.test.task.shared;

import java.io.Serializable;

/**
 * 
 * Rule type model.
 * 
 * @author Ilya Sviridov
 * 
 */
public class RuleType extends NamedData implements Serializable {

	private static final long serialVersionUID = -4651772996557673291L;

	/**
	 * Is it interval rule
	 */
	boolean interval = false;

	/**
	 * Constructor
	 */
	public RuleType() {
		super();
	}

	/**
	 * Constructor
	 */

	public RuleType(int id, String name, boolean interval) {
		super(id, name);
		this.interval = interval;

	}

	/**
	 * interval getter
	 * 
	 * @return
	 */
	public boolean isInterval() {
		return interval;
	}

	/**
	 * interval setter
	 * 
	 * @param interval
	 */
	public void setInterval(boolean interval) {
		this.interval = interval;
	}
}
