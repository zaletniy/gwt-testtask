package com.example.test.task.client.view;

import com.example.test.task.shared.IntervalableRuleType;
import com.example.test.task.shared.NamedData;
import com.example.test.task.shared.RuleType;
import com.example.test.task.shared.Substitution;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.datepicker.client.DateBox;

public class EditSubstitutionViewImpl implements EditSubstitutionView {

	private static EditSubstitutionViewImplUiBinder uiBinder = GWT
			.create(EditSubstitutionViewImplUiBinder.class);
	@UiField
	PushButton cancelButton;
	@UiField
	PushButton saveButton;
	@UiField
	ComplexObjectListBox<NamedData> roleLbx;
	@UiField
	ComplexObjectListBox<NamedData> substituteLbx;
	@UiField
	ComplexObjectListBox<NamedData> ruleTypeLbx;
	@UiField
	DateBox beginDbx;
	@UiField
	DateBox endDbx;

	@UiField
	DialogBox dialogBox;

	Presenter presenter;

	NamedData[] ruleTypes;

	Substitution substitution;

	interface EditSubstitutionViewImplUiBinder extends
			UiBinder<DialogBox, EditSubstitutionViewImpl> {
	}

	public EditSubstitutionViewImpl() {
		dialogBox = uiBinder.createAndBindUi(this);
		ruleTypeLbx.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String selectedItem = ruleTypeLbx.getValue(ruleTypeLbx
						.getSelectedIndex());
				for (NamedData ruleType : ruleTypes) {
					if (selectedItem.equals(Integer.toString(ruleType.getId()))) {
						if (ruleType instanceof IntervalableRuleType) {
							beginDbx.setEnabled(true);
							endDbx.setEnabled(true);
						} else {
							beginDbx.setEnabled(false);
							endDbx.setEnabled(false);
						}
					}
				}
			}
		});
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	public void setRoles(NamedData[] roles) {
		roleLbx.clear();
		// TODO: localize
		roleLbx.addObjectItem("", "", null);
		for (NamedData namedData : roles)
			roleLbx.addObjectItem(namedData.getName(),
					Integer.toString(namedData.getId()), namedData);

	}

	public void setSubstituteNames(NamedData[] substituteNames) {
		// TODO: localize
		substituteLbx.clear();
		for (NamedData namedData : substituteNames) {
			substituteLbx.addObjectItem(namedData.getName(),
					Integer.toString(namedData.getId()), namedData);
		}
	}

	public void setRuleTypes(NamedData[] ruleTypes) {
		// TODO: localize
		this.ruleTypes = ruleTypes;
		ruleTypeLbx.clear();
		for (NamedData namedData : ruleTypes) {
			ruleTypeLbx.addObjectItem(namedData.getName(),
					Integer.toString(namedData.getId()), namedData);
		}
		ruleTypeLbx.fireEvent(GWT.<ChangeEvent> create(ChangeEvent.class));
	}

	public void setData(Substitution substitution) {
		if (substitution == null) {
			this.substitution = substitution;
			roleLbx.setSelectedValue("");
		} else {
			// TODO: localize
			this.substitution = substitution;
			roleLbx.setSelectedValue(substitution.getRoleId());
			substituteLbx
					.setSelectedValue(substitution.getSubstitutionNameId());
			ruleTypeLbx.setSelectedValue(substitution.getRuleType().getId());

			if (substitution.getRuleType() instanceof IntervalableRuleType) {
				IntervalableRuleType ruleType = (IntervalableRuleType) substitution
						.getRuleType();
				beginDbx.setEnabled(true);
				endDbx.setEnabled(true);
				beginDbx.setValue(ruleType.getBegin());
				endDbx.setValue(ruleType.getEnd());
			} else {
				beginDbx.setEnabled(false);
				endDbx.setEnabled(false);
			}
		}
	}

	public Substitution getSubstitution() {
		// TODO: validation
		if (substitution == null) {
			substitution = new Substitution();
		}

		if (roleLbx.getSelectedObject() == null
				|| substituteLbx.getSelectedObject() == null
				|| ruleTypeLbx.getSelectedObject() == null
				|| beginDbx.getValue() == null || endDbx.getValue() == null) {
			Window.alert("Please fill all fields");
			return null;
		}
		substitution.setRoleId(roleLbx.getSelectedObject().getId());
		substitution.setSubstitutionNameId(substituteLbx.getSelectedObject()
				.getId());
		// substitution.set
		if (ruleTypeLbx.getSelectedObject() instanceof IntervalableRuleType) {
			IntervalableRuleType source = (IntervalableRuleType) ruleTypeLbx
					.getSelectedObject();
			IntervalableRuleType dest = new IntervalableRuleType(
					source.getId(), source.getName(), beginDbx.getValue(),
					endDbx.getValue());
			substitution.setRuleType(dest);
		} else {
			substitution
					.setRuleType((RuleType) ruleTypeLbx.getSelectedObject());
		}

		return substitution;
	}

	public void go() {
		dialogBox.center();
		dialogBox.show();
	}

	@UiHandler("cancelButton")
	void onCancelButtonClick(ClickEvent event) {
		dialogBox.hide();
	}

	@UiHandler("saveButton")
	void onSaveButtonClick(ClickEvent event) {
		presenter.onSaveClick();
	}

	public void dataSaved() {
		dialogBox.hide();
	}

	public void dataSavingError(Throwable throwable) {
		Window.alert("Error during saving data" + throwable.getMessage());
	}
}
