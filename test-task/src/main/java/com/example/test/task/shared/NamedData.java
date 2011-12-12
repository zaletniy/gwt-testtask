package com.example.test.task.shared;

import java.io.Serializable;

/**
 * 
 * Data object which has name.
 * 
 * @author Ilya Sviridov
 * 
 */
public class NamedData extends DataObject implements Serializable {
	private static final long serialVersionUID = -3014279964664221172L;

	/**
	 * Name
	 */
	String name;

	/**
	 * Name getter
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name setter
	 * 
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Constructor
	 */
	public NamedData() {
		super();
	}

	/**
	 * Constructor
	 */
	public NamedData(int id, String name) {
		super(id);
		this.name = name;
	}
}
