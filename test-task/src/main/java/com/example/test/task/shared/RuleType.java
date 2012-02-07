package com.example.test.task.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Rule type model.
 * 
 * @author Ilya Sviridov
 * 
 */
@Entity
@Table(name = "rule_type")
public class RuleType extends NamedData implements Serializable {

	private static final long serialVersionUID = -4651772996557673291L;

	/**
	 * Is it interval rule
	 */
	@Column(name = "is_interval")
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
