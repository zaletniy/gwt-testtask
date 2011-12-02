package com.example.test.task.shared;

import java.io.Serializable;
import java.util.Date;

public class Substitution extends DataObject implements Serializable {

	private static final long serialVersionUID = -2801786595784092447L;
	int substitutionNameId;
	int roleId;
	Date beginDate;
	Date endDate;

	RuleType ruleType;

	public Substitution() {
		super();
	}
	
	public Substitution(int id, int substitutionNameId, int roleId,
			RuleType ruleType, Date beginDate, Date endDate) {
		super(id);
		this.substitutionNameId = substitutionNameId;
		this.roleId = roleId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.ruleType = ruleType;
	}

	public int getSubstitutionNameId() {
		return substitutionNameId;
	}

	public void setSubstitutionNameId(int substitutionNameId) {
		this.substitutionNameId = substitutionNameId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}


	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
