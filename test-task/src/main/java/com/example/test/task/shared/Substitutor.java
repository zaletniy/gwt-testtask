package com.example.test.task.shared;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * That entity represents the reference data of people who can be applied with substitution rule.
 *   
 * @author Ilya Sviridov
 *
 */
@Entity
@Table(name="substitutor")
public class Substitutor extends NamedData implements Serializable{
	private static final long serialVersionUID = 8444703634460498869L;
	
	public Substitutor() {
	}
	
	public Substitutor(int id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return "Substitutor [name=" + name + ", id=" + id + "]";
	}

}
