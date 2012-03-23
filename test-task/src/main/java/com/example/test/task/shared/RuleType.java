package com.example.test.task.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

/**
 * 
 * Rule type model.
 * 
 * @author Ilya Sviridov
 * 
 */
@Entity
@Table(name = "rule_type",uniqueConstraints={@UniqueConstraint(columnNames={"id","name"})})
public class RuleType extends NamedData implements Serializable {

	private static final long serialVersionUID = -4651772996557673291L;

	/**
	 * Is it interval rule
	 */
	@Column(name = "is_interval")
	@Type(type = "org.hibernate.type.NumericBooleanType")
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
