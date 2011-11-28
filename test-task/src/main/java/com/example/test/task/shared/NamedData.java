package com.example.test.task.shared;

import java.io.Serializable;

public class NamedData extends DataObject implements Serializable{
	private static final long serialVersionUID = -3014279964664221172L;
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NamedData() {
		super();
	}
	public NamedData(int id, String name) {
		super(id);
		this.name = name;
	}
}
