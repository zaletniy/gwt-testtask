package com.example.test.task.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Substitution model.
 * 
 * @author Ilya Sviridov
 * 
 */
public class Substitution extends DataObject implements Serializable {

	private static final long serialVersionUID = -2801786595784092447L;

	/**
	 * Sunstitutor name id
	 */
	int substitutionNameId;

	/**
	 * role id
	 */
	int roleId;

	/**
	 * Begin date
	 */
	Date beginDate;

	/**
	 * End date
	 */
	Date endDate;

	/**
	 * Rule type
	 */
	RuleType ruleType;

	/**
	 * Constructor
	 */
	public Substitution() {
		super();
	}

	/**
	 * Constructor
	 */
	public Substitution(int id, int substitutionNameId, int roleId, RuleType ruleType, Date beginDate, Date endDate) {
		super(id);
		this.substitutionNameId = substitutionNameId;
		this.roleId = roleId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.ruleType = ruleType;
	}

	/**
	 * Substitution getter
	 * 
	 * @return
	 */
	public int getSubstitutionNameId() {
		return substitutionNameId;
	}

	/**
	 * Substitution setter
	 * 
	 * @return
	 */
	public void setSubstitutionNameId(int substitutionNameId) {
		this.substitutionNameId = substitutionNameId;
	}

	/**
	 * Role id getter
	 * 
	 * @return
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * Role id getter
	 * 
	 * @return
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * Rule type getter
	 * 
	 * @return
	 */
	public RuleType getRuleType() {
		return ruleType;
	}

	/**
	 * Rule type setter
	 * 
	 * @return
	 */
	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	/**
	 * begin date getter
	 * 
	 * @return
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * begin date setter
	 * 
	 * @return
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * End date getter
	 * 
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * end date setter
	 * 
	 * @return
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
