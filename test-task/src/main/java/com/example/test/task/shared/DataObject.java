package com.example.test.task.shared;

import java.io.Serializable;

/**
 * The most general data object which just has id.
 * 
 * @author Ilya Sviridov
 * 
 */
public abstract class DataObject implements Serializable {
	private static final long serialVersionUID = -658657438535744127L;
	/**
	 * Id
	 */
	int id;

	/**
	 * id getter
	 */
	public int getId() {
		return id;
	}

	/**
	 * id setter
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public DataObject(int id) {
		super();
		this.id = id;
	}

	/**
	 * Default constructor
	 */
	public DataObject() {
		super();
	}

}
