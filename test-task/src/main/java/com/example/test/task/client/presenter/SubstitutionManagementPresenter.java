package com.example.test.task.client.presenter;

import java.util.Arrays;
import java.util.List;

import com.example.test.task.client.SubstitutionManagementServiceAsync;
import com.example.test.task.client.event.CreateSubstitutionEvent;
import com.example.test.task.client.event.EditSubstitutionEvent;
import com.example.test.task.client.event.UpdateDataEvent;
import com.example.test.task.client.event.UpdateDataEventHandler;
import com.example.test.task.client.view.SubstitutionManagementView;
import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class SubstitutionManagementPresenter implements Presenter,
		SubstitutionManagementView.Presenter<SubstitutionDetails> {

	SubstitutionManagementView<SubstitutionDetails> view;
	SubstitutionManagementServiceAsync service;
	SubstitutionDetails[] data;

	EventBus eventBus;

	public SubstitutionManagementPresenter(
			SubstitutionManagementView<SubstitutionDetails> view,
			SubstitutionManagementServiceAsync service, EventBus eventBus) {
		super();
		this.view = view;
		this.service = service;
		this.eventBus = eventBus;
		view.setPresenter(this);
		fetchData();
		
		eventBus.addHandler(UpdateDataEvent.TYPE, new UpdateDataEventHandler() {
			public void onUpdateDataEvent(UpdateDataEvent event) {
				fetchData();
			}
		});
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
	}

	public void onCreateButtonClicked() {
		eventBus.fireEvent(new CreateSubstitutionEvent());
	}

	public void onUpdateButtonClicked() {
		Integer selectedItedId=view.getCheckedItemIds().get(0);
		for(SubstitutionDetails item:data){
			if(selectedItedId.equals(item.getId())){
				eventBus.fireEvent(new EditSubstitutionEvent(item.getId()));
			}
		}
	}

	public void onDeleteButtonClicked() {
		List<Integer> selectedItems=view.getCheckedItemIds();
		if(selectedItems.isEmpty())
			return;
		
		view.onLoadDataStart();
		service.deleteSubstitution(
				selectedItems.toArray(new Integer[] {}),
				new AsyncCallback<SubstitutionDetails[]>() {
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						view.onLoadDataError();
					}

					public void onSuccess(SubstitutionDetails[] result) {
						view.setData(Arrays.asList(result));
						view.onLoadDataFinish();
					}
				});
	}

	public void updateData() {
		fetchData();
	}

	protected void fetchData() {
		view.onLoadDataStart();
		service.getSubstitutions(new AsyncCallback<SubstitutionDetails[]>() {

			public void onFailure(Throwable arg0) {
				arg0.printStackTrace();
				view.onLoadDataError();
			}

			public void onSuccess(SubstitutionDetails[] result) {
				data=result;
				view.setData(Arrays.asList(result));
				view.onLoadDataFinish();
			}
		});
	}
}
