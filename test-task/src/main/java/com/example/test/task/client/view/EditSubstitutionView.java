package com.example.test.task.client.view;

import java.util.Date;
import java.util.Map;

public interface EditSubstitutionView{
	
	public interface Presenter{
		void onCancelAction();
		void onSaveAction();
		void onEndDataSet();
		void onRuleTypesSelection();
	}
	
	void setPresenter(Presenter presenter);
	
	void setRoles(Map<String,String> roles);
	void setSubstitors(Map<String,String> substitutors);
	void setRuleTypes(Map<String,String> ruleTypes);
	
	void setRole(String id);
	void setSubstitutor(String id);
	void setRuleType(String id);
	
	void setBeginDate(Date beginDate);
	void setEndDate(Date endDate);
	
	
	String getRole();
	String getSubstituror();
	String getRuleType();
	
	Date getBeginDate();
	Date getEndDate();
	
	void setTimeIntervalEnabled(boolean isTimeIntervalEnabled);
	void setCancelControllEnabled(boolean enabled);
	void setSaveControllEnabled(boolean enabled);
	
	void onDataSavingOk();
	
	void go();
}
