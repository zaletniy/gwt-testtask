package com.example.test.task.client.view;

import com.example.test.task.shared.NamedData;
import com.example.test.task.shared.Substitution;

public interface EditSubstitutionView{
	
	public interface Presenter{
		void onCancelClick();
		void onSaveClick();
	}
	
	void setPresenter(Presenter presenter);
	
	void setRoles(NamedData[] roles);
	void setSubstituteNames(NamedData[] substituteNames);
	void setRuleTypes(NamedData[] ruleTypes);
	
	void setData(Substitution substitution);
	void dataSaved();
	void dataSavingError(Throwable throwable);
	
	Substitution getSubstitution();
	
	void go();
}
