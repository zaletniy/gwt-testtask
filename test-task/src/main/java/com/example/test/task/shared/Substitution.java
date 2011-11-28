package com.example.test.task.shared;

import java.io.Serializable;

public class Substitution extends DataObject implements Serializable{

	private static final long serialVersionUID = -2801786595784092447L;
	int substitutionNameId;
	int roleId;
	RuleType ruleType;
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
	public Substitution(int id, int substitutionNameId, int roleId,
			RuleType ruleType) {
		super(id);
		this.substitutionNameId = substitutionNameId;
		this.roleId = roleId;
		this.ruleType = ruleType;
	}
	public Substitution() {
		super();
	}
	
}
