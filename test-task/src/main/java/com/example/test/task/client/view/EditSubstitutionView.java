package com.example.test.task.client.view;

import java.util.Date;
import java.util.Map;

/**
 * The interface for edit view.
 * 
 * @author Ilya Sviridov
 *
 */
public interface EditSubstitutionView{
	/**
	 * Edit view presenter interface.
	 * 
	 * @author Ilya Sviridov
	 *
	 */
	public interface Presenter{
		/**
		 * Called on cancel action
		 */
		void onCancelAction();
		
		/**
		 * Called on save action
		 */
		void onSaveAction();
		
		/**
		 * Called on end date setting action
		 */
		void onEndDataSet();
		
		/**
		 * Called on rule type selection 
		 */
		void onRuleTypesSelection();
	}
	
	/**
	 * Wires view with presenter
	 * @param presenter presenter
	 */
	void setPresenter(Presenter presenter);
	
	/**
	 * Fills listbox
	 * @param roles
	 */
	void setRoles(Map<String,String> roles);
	
	/**
	 * Fills listbox
	 * @param substitutors
	 */
	void setSubstitors(Map<String,String> substitutors);
	
	/**
	 * Fills listbox
	 * @param ruleTypes
	 */
	void setRuleTypes(Map<String,String> ruleTypes);
	
	/**
	 * Set view with selected role on editing
	 * @param id
	 */
	void setRole(String id);
	
	/**
	 * Set view with selected substitutor on editing
	 * @param id
	 */
	void setSubstitutor(String id);

	/**
	 * Set view with selected rule type on editing
	 * @param id
	 */
	void setRuleType(String id);

	/**
	 * Set view with selected begin date on editing
	 * @param id
	 */
	void setBeginDate(Date beginDate);

	/**
	 * Set view with selected end date on editing
	 * @param id
	 */
	void setEndDate(Date endDate);
	
	/**
	 * Returns input data
	 * @return selected role
	 */
	String getRole();
	
	/**
	 * Returns input data
	 * @return substitutor
	 */
	String getSubstituror();
	
	/**
	 * Returns input data
	 * @return rule type
	 */
	String getRuleType();
	
	/**
	 * Returns input date
	 * @return begin date
	 */
	Date getBeginDate();
	
	/**
	 * Returns input data
	 * @return end date
	 */
	Date getEndDate();
	
	/**
	 * Makes date selection controls enabled/disabled
	 * @param isTimeIntervalEnabled
	 */
	void setTimeIntervalEnabled(boolean isTimeIntervalEnabled);
	
	/**
	 * Makes cancel action control enabled/disabled
	 * @param enabled
	 */
	void setCancelControllEnabled(boolean enabled);
	
	/**
	 * Makes save action control enabled/disabled
	 * @param enabled
	 */
	void setSaveControllEnabled(boolean enabled);
	
	/**
	 * Informs view that data saved without errors
	 */
	void onDataSavingOk();
	
	/**
	 * Go method
	 */
	void go();
}
