package com.example.test.task.shared;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.sun.istack.internal.NotNull;

/**
 * The full model of substitution.
 * 
 * @author Ilya Sviridov
 *
 */

@Entity
@Table(name="substitution")
public class SubstitutionFull extends DataObject {
	private static final long serialVersionUID = -6557329547359803832L;
	
	@OneToOne
	@NotNull
	@LazyToOne(LazyToOneOption.FALSE)
	@JoinColumn(name="substitutor_id")
	Substitutor substitutor;
	
	@OneToOne
	@NotNull
	@LazyToOne(LazyToOneOption.FALSE)
	@JoinColumn(name="rule_type_id")
	RuleType ruleType;
	
	@OneToOne
	@NotNull
	@LazyToOne(LazyToOneOption.FALSE)
	@JoinColumn(name="role_id")
	Role role;
	/**
	 * Begin date
	 */
	@Column(name="begin_date")
	Date beginDate;

	/**
	 * End date
	 */
	@Column(name="end_date")
	Date endDate;

	/**
	 * @return the substitutor
	 */
	public Substitutor getSubstitutor() {
		return substitutor;
	}

	/**
	 * @param substitutor the substitutor to set
	 */
	public void setSubstitutor(Substitutor substitutor) {
		this.substitutor = substitutor;
	}

	/**
	 * @return the ruleType
	 */
	public RuleType getRuleType() {
		return ruleType;
	}

	/**
	 * @param ruleType the ruleType to set
	 */
	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
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
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubstitutionFull [substitutor=" + substitutor + ", ruleType="
				+ ruleType + ", role=" + role + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + ", id=" + id + "]";
	}

	public SubstitutionFull() {
		super();
	}

	public SubstitutionFull(Substitutor substitutor, RuleType ruleType,
			Role role, Date beginDate, Date endDate) {
		super();
		this.substitutor = substitutor;
		this.ruleType = ruleType;
		this.role = role;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
	
}
