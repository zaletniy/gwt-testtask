package com.example.test.task.shared;

import java.io.Serializable;
import java.util.Date;

public class IntervalableRuleType extends RuleType implements Serializable {

	private static final long serialVersionUID = -2040402524388498213L;
	Date begin;
	Date end;
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public IntervalableRuleType(int id, String name, Date begin, Date end) {
		super(id, name);
		this.begin = begin;
		this.end = end;
	}
	public IntervalableRuleType() {
		super();
	}
	
	
}
