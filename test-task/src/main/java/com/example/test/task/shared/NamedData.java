package com.example.test.task.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * Data object which has name.
 * 
 * @author Ilya Sviridov
 * 
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"id","name"})})
public class NamedData extends DataObject implements Serializable {
	private static final long serialVersionUID = -3014279964664221172L;

	/**
	 * Name
	 */
	@Column(name="name", nullable=false, unique=true)
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

	@Override
	public String toString() {
		return "NamedData [name=" + name + ", id=" + id + "]";
	}
	
}
