package com.example.test.task.shared;

import java.io.Serializable;
import java.util.List;

public class EditViewReferenceData implements Serializable {
	private static final long serialVersionUID = -519759987007776918L;
	List<NamedData> roles;
	List<NamedData> substitutors;
	List<RuleType> ruleTypes;
	public List<NamedData> getRoles() {
		return roles;
	}
	public void setRoles(List<NamedData> roles) {
		this.roles = roles;
	}
	public List<NamedData> getSubstitutors() {
		return substitutors;
	}
	public void setSubstitutors(List<NamedData> substitutors) {
		this.substitutors = substitutors;
	}
	public List<RuleType> getRuleTypes() {
		return ruleTypes;
	}
	public void setRuleTypes(List<RuleType> ruleTypes) {
		this.ruleTypes = ruleTypes;
	}
	public EditViewReferenceData(List<NamedData> roles,
			List<NamedData> substitutors, List<RuleType> ruleTypes) {
		super();
		this.roles = roles;
		this.substitutors = substitutors;
		this.ruleTypes = ruleTypes;
	}
	public EditViewReferenceData() {
		super();
	}
	
}
