package com.example.test.task.client;

import java.util.Date;

public interface NonStringMessages extends com.google.gwt.i18n.client.Messages {
	@DefaultMessage("{0,date,medium}")
	@Key("dateFormat")
	String dateFormat(Date arg0);
	
	@DefaultMessage("Not localized role")
	@AlternateMessage({ "fullTimeRole", "Full time",
			"partTimeRole", "Part time",})
	String roles(@Select String roleName);
	
	@DefaultMessage("Not localized rule")
	@AlternateMessage({ "alwaysRuleType", "Always",
			"intervalRuleType", "Interval",
			"inactiveRuleType", "Inactive",})
	String rules(@Select String ruleName);
}
