package com.example.test.task.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

/**
 * Substituition model with data.
 * 
 * @author Ilya Sviridov
 * 
 */
//TODO
//@Entity
//@Table(name="substitution")
//@SecondaryTables({ @SecondaryTable(name = "substitutor", pkJoinColumns = { @PrimaryKeyJoinColumn(name="substitutor_id") }) })
public class SubstitutionDetails extends DataObject implements Serializable {
	private static final long serialVersionUID = -9127568820814721368L;

	/**
	 * Name
	 */
	@Column(table = "substitutor", name = "name")
	String name;

	/**
	 * Role
	 */
	String role;

	/**
	 * Rule type
	 */
	String ruleType;

	/**
	 * Begin date
	 */
	Date beginDate;

	/**
	 * End date
	 */
	Date endDate;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the ruleType
	 */
	public String getRuleType() {
		return ruleType;
	}

	/**
	 * @param ruleType
	 *            the ruleType to set
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param name
	 * @param role
	 * @param ruleType
	 * @param beginDate
	 * @param endDate
	 */
	public SubstitutionDetails(int id, String name, String role,
			String ruleType, Date beginDate, Date endDate) {
		super(id);
		this.id = id;
		this.name = name;
		this.role = role;
		this.ruleType = ruleType;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	/**
	 * Constructor
	 */
	public SubstitutionDetails() {
		super();
	}
}