package com.example.test.task.client.view;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.example.test.task.client.Messages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;

/**
 * The GWT implementation of {@link EditSubstitutionView}
 * 
 * @author Ilya Sviridov
 * 
 */
public class EditSubstitutionViewImpl implements EditSubstitutionView {
	/**
	 * uiBinder
	 */
	private static EditSubstitutionViewImplUiBinder uiBinder = GWT.create(EditSubstitutionViewImplUiBinder.class);
	/**
	 * Messages
	 */
	private Messages messages = GWT.create(Messages.class);

	/**
	 * Cancel button
	 */
	@UiField
	PushButton cancelButton;

	/**
	 * Save button
	 */
	@UiField
	PushButton saveButton;

	/**
	 * Role listbox
	 */
	@UiField
	ListBox roleLbx;

	/**
	 * Substitution listbox
	 */
	@UiField
	ListBox substituteLbx;

	/**
	 * Rule typr list box
	 */
	@UiField
	ListBox ruleTypeLbx;

	/**
	 * Begin data date box
	 */
	@UiField
	DateBox beginDbx;

	/**
	 * End date date box
	 */
	@UiField
	DateBox endDbx;

	/**
	 * Dialig box object
	 */
	@UiField
	DialogBox dialogBox;

	/**
	 * {@link WindowContainer} object
	 */
	@UiField
	WindowContainer windowContainer;

	/**
	 * Presenter
	 */
	Presenter presenter;

	/**
	 * Interface for binding
	 * 
	 * @author Ilya Sviridov
	 * 
	 */
	interface EditSubstitutionViewImplUiBinder extends UiBinder<DialogBox, EditSubstitutionViewImpl> {
	}

	/**
	 * Default constructor
	 */
	public EditSubstitutionViewImpl() {
		dialogBox = uiBinder.createAndBindUi(this);
		ruleTypeLbx.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				presenter.onRuleTypesSelection();
			}
		});
		beginDbx.setFormat(new DateBoxFormat());
		endDbx.setFormat(new DateBoxFormat());
	}

	/**
	 * Wires view with presenter
	 * 
	 * @param presenter
	 *            presenter
	 */
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	/**
	 * Go method
	 */
	public void go() {
		dialogBox.center();
		dialogBox.show();
	}

	/**
	 * Cancel button click handler
	 * 
	 * @param event
	 */
	@UiHandler("cancelButton")
	void onCancelButtonClick(ClickEvent event) {
		presenter.onCancelAction();
		dialogBox.hide();
	}

	/**
	 * Save button click handler
	 * 
	 * @param event
	 */
	@UiHandler("saveButton")
	void onSaveButtonClick(ClickEvent event) {
		presenter.onSaveAction();
	}

	/**
	 * Fills listbox
	 * 
	 * @param roles
	 */
	public void setRoles(Map<String, String> roles) {
		roleLbx.clear();
		fillListBoxState(roles, roleLbx);
	}

	/**
	 * Fills listbox
	 * 
	 * @param substitutors
	 */
	public void setSubstitors(Map<String, String> substitutors) {
		substituteLbx.clear();
		fillListBoxState(substitutors, substituteLbx);
	}

	/**
	 * Fills listbox
	 * 
	 * @param ruleTypes
	 */
	public void setRuleTypes(Map<String, String> ruleTypes) {
		ruleTypeLbx.clear();
		fillListBoxState(ruleTypes, ruleTypeLbx);
	}

	/**
	 * Set view with selected begin date on editing
	 * 
	 * @param id
	 */
	public void setBeginDate(Date beginDate) {
		beginDbx.setValue(beginDate);
	}

	/**
	 * Set view with selected end date on editing
	 * 
	 * @param id
	 */
	public void setEndDate(Date endDate) {
		endDbx.setValue(endDate);
	}

	/**
	 * Makes date selection controls enabled/disabled
	 * 
	 * @param isTimeIntervalEnabled
	 */
	public void setTimeIntervalEnabled(boolean isTimeIntervalEnabled) {
		beginDbx.setEnabled(isTimeIntervalEnabled);
		endDbx.setEnabled(isTimeIntervalEnabled);
	}

	/**
	 * Fils listbox with given data
	 * 
	 * @param data
	 * @param listBox
	 */
	private void fillListBoxState(Map<String, String> data, ListBox listBox) {
		for (Entry<String, String> entry : data.entrySet()) {
			listBox.addItem(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Selects item in list box by value
	 * 
	 * @param listBox
	 * @param value
	 */
	private void setSelectedItemByValue(ListBox listBox, String value) {
		for (int i = 0; i < listBox.getItemCount(); i++) {
			if (value.equals(listBox.getValue(i)))
				listBox.setSelectedIndex(i);
		}
	}

	/**
	 * Returns input data
	 * 
	 * @return selected role
	 */
	public String getRole() {
		return roleLbx.getValue(roleLbx.getSelectedIndex());
	}

	/**
	 * Returns input data
	 * 
	 * @return substitutor
	 */
	public String getSubstituror() {
		return substituteLbx.getValue(substituteLbx.getSelectedIndex());
	}

	/**
	 * Returns input data
	 * 
	 * @return rule type
	 */
	public String getRuleType() {
		return ruleTypeLbx.getValue(ruleTypeLbx.getSelectedIndex());
	}

	/**
	 * Returns input date
	 * 
	 * @return begin date
	 */
	public Date getBeginDate() {
		return beginDbx.getValue();
	}

	/**
	 * Returns input data
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return endDbx.getValue();
	}

	/**
	 * Set view with selected role on editing
	 * 
	 * @param id
	 */
	public void setRole(String id) {
		setSelectedItemByValue(roleLbx, id);
	}

	/**
	 * Set view with selected substitutor on editing
	 * 
	 * @param id
	 */
	public void setSubstitutor(String id) {
		setSelectedItemByValue(substituteLbx, id);
	}

	/**
	 * Set view with selected rule type on editing
	 * 
	 * @param id
	 */
	public void setRuleType(String id) {
		setSelectedItemByValue(ruleTypeLbx, id);
	}

	/**
	 * Makes cancel action control enabled/disabled
	 * 
	 * @param enabled
	 */
	public void setCancelControllEnabled(boolean enabled) {
		cancelButton.setEnabled(enabled);
	}

	/**
	 * Makes save action control enabled/disabled
	 * 
	 * @param enabled
	 */
	public void setSaveControllEnabled(boolean enabled) {
		saveButton.setEnabled(enabled);
	}

	/**
	 * Informs view that data saved without errors
	 */
	public void onDataSavingOk() {
		dialogBox.hide();
	}

	/**
	 * Close button action handler of window
	 * 
	 * @param event
	 */
	@UiHandler("windowContainer")
	void onWindowContainerClick(ClickEvent event) {
		onCancelButtonClick(event);
	}

	/**
	 * Date box format class
	 * 
	 * @author Ilya Sviridov
	 * 
	 */
	public class DateBoxFormat implements Format {
		/**
		 * Constructor
		 */
		public void reset(DateBox dateBox, boolean abandon) {
		}

		/**
		 * to Date
		 */
		public Date parse(DateBox dateBox, String text, boolean reportError) {
			return (text == null | "".equals(text)) ? null : DateTimeFormat.getFormat(messages.dateBoxDateFormat())
					.parse(text);
		}

		/**
		 * to Sting
		 */
		public String format(DateBox dateBox, Date date) {
			return date == null ? "" : messages.dateFormat(date);
		}
	}
}
