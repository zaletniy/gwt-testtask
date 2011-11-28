package com.example.test.task.shared;

import java.io.Serializable;


public abstract class DataObject implements Serializable{
	private static final long serialVersionUID = -658657438535744127L;
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DataObject(int id) {
		super();
		this.id = id;
	}
	public DataObject() {
		super();
	}
	
}
