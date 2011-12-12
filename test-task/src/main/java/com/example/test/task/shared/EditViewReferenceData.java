package com.example.test.task.shared;

import java.io.Serializable;
import java.util.List;

/**
 * Container for all reference data.
 * 
 * @author Ilya Sviridov
 * 
 */
public class EditViewReferenceData implements Serializable {
	private static final long serialVersionUID = -519759987007776918L;

	/**
	 * Roles
	 */
	List<NamedData> roles;

	/**
	 * Substitutors
	 */
	List<NamedData> substitutors;

	/**
	 * Rule types
	 */
	List<RuleType> ruleTypes;

	/**
	 * Roles getter
	 * 
	 * @return
	 */
	public List<NamedData> getRoles() {
		return roles;
	}

	/**
	 * Roles setter
	 * 
	 * @param roles
	 */
	public void setRoles(List<NamedData> roles) {
		this.roles = roles;
	}

	/**
	 * Substituors getter
	 * 
	 * @return
	 */
	public List<NamedData> getSubstitutors() {
		return substitutors;
	}

	/**
	 * Substituors setter
	 * 
	 * @return
	 */
	public void setSubstitutors(List<NamedData> substitutors) {
		this.substitutors = substitutors;
	}

	/**
	 * Rule types getter
	 * 
	 * @return
	 */
	public List<RuleType> getRuleTypes() {
		return ruleTypes;
	}

	/**
	 * Rule types setter
	 * 
	 * @return
	 */
	public void setRuleTypes(List<RuleType> ruleTypes) {
		this.ruleTypes = ruleTypes;
	}

	/**
	 * Constructor 
	 * @param roles
	 * @param substitutors
	 * @param ruleTypes
	 */
	public EditViewReferenceData(List<NamedData> roles, List<NamedData> substitutors, List<RuleType> ruleTypes) {
		super();
		this.roles = roles;
		this.substitutors = substitutors;
		this.ruleTypes = ruleTypes;
	}

	/**
	 * Default constructor
	 */
	public EditViewReferenceData() {
		super();
	}

}
