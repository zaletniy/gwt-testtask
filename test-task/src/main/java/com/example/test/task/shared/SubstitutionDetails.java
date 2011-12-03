package com.example.test.task.shared;

import java.io.Serializable;
import java.util.Date;

public class SubstitutionDetails extends DataObject implements Serializable {
	private static final long serialVersionUID = -9127568820814721368L;

	String name;
	String role;
	String ruleType;
	Date beginDate;
	Date endDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date startDate) {
		this.beginDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public SubstitutionDetails(int id, String name, String role, String ruleType,
			Date beginDate, Date endDate) {
		super(id);
		this.id = id;
		this.name = name;
		this.role = role;
		this.ruleType = ruleType;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public SubstitutionDetails() {
		super();
	}
}