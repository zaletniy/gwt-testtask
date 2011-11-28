package com.example.test.task.client.presenter;

import java.util.Map;

import com.example.test.task.client.SubstitutionManagementServiceAsync;
import com.example.test.task.client.event.CreateSubstitutionEvent;
import com.example.test.task.client.event.CreateSubstitutionEventHandler;
import com.example.test.task.client.event.EditSubstitutionEvent;
import com.example.test.task.client.event.EditSubstitutionEventHandler;
import com.example.test.task.client.event.UpdateDataEvent;
import com.example.test.task.client.view.EditSubstitutionView;
import com.example.test.task.shared.NamedData;
import com.example.test.task.shared.Substitution;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

public class EditSubstitutionPresenter implements Presenter,
		com.example.test.task.client.view.EditSubstitutionView.Presenter {

	EventBus eventBus;
	final EditSubstitutionView view;
	SubstitutionManagementServiceAsync service;
	Substitution substitution;

	public EditSubstitutionPresenter(EventBus eventBus,
			EditSubstitutionView view,
			SubstitutionManagementServiceAsync service) {
		super();
		this.eventBus = eventBus;
		this.view = view;
		this.service = service;
		fetchData();
		view.setPresenter(this);

		eventBus.addHandler(EditSubstitutionEvent.TYPE,
				new EditSubstitutionEventHandler() {
					public void onSubstitutionEditEvent(
							EditSubstitutionEvent event) {
						editData(event.getSubstitutionId());
					}
				});

		eventBus.addHandler(CreateSubstitutionEvent.TYPE,
				new CreateSubstitutionEventHandler() {
					public void createSubstitution() {
						editData(null);
					}
				});

	}

	protected void fetchData() {
		service.getAllNamedData(new AsyncCallback<Map>() {
			public void onSuccess(Map result) {
				view.setRoles((NamedData[]) (result.get("roles")));
				view.setSubstituteNames((NamedData[]) (result
						.get("substitutors")));
				view.setRuleTypes((NamedData[]) (result.get("ruleTypes")));
			}

			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}
		});
	}

	public void editData(Integer substitutionId) {
		if(substitutionId!=null){
		service.getSubstitution(substitutionId, new AsyncCallback<Substitution>() {
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
			}
			public void onSuccess(Substitution result) {
				substitution=result;
				view.setData(substitution);
				view.go();
			}
		});
		}else{
			this.substitution=null;
			view.setData(null);
			view.go();
		}
	}

	public void onCancelClick() {
		// TODO Auto-generated method stub

	}

	public void onSaveClick() {
		substitution = view.getSubstitution();
		if(substitution==null)
			return;
		service.saveSubstitution(substitution, new AsyncCallback<Integer>() {
			public void onFailure(Throwable caught) {
				view.dataSavingError(caught);
			}

			public void onSuccess(Integer result) {
				eventBus.fireEvent(new UpdateDataEvent(substitution));
				view.dataSaved();
			}
		});
	}

	public void go(HasWidgets container) {
	}
}
