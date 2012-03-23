package com.example.test.task.shared;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="role",uniqueConstraints={@UniqueConstraint(columnNames={"id","name"})})
public class Role extends NamedData {
	private static final long serialVersionUID = -790515412176678222L;

	public Role() {
		super();
	}

	public Role(int id, String name) {
		super(id, name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [name=" + name + ", id=" + id + ", hashCode()="
				+ hashCode() + "]";
	}
}
