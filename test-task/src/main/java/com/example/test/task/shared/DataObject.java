package com.example.test.task.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * The most general data object which just has id.
 * 
 * @author Ilya Sviridov
 * 
 */
@Entity
@Inheritance (strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class DataObject implements Serializable {
	private static final long serialVersionUID = -658657438535744127L;
	/**
	 * Id
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
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
