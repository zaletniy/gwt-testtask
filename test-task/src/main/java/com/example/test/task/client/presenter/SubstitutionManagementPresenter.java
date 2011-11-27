package com.example.test.task.client.presenter;

import java.util.Arrays;

import com.example.test.task.client.SubstitutionManagementServiceAsync;
import com.example.test.task.client.view.SubstitutionManagementView;
import com.example.test.task.shared.Substitution;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class SubstitutionManagementPresenter implements Presenter,
		SubstitutionManagementView.Presenter<Substitution> {

	SubstitutionManagementView<Substitution> view;
	SubstitutionManagementServiceAsync service;

	public SubstitutionManagementPresenter(
			SubstitutionManagementView<Substitution> view, SubstitutionManagementServiceAsync service) {
		super();
		this.view = view;
		this.service=service;
		view.setPresenter(this);
		fetchData();
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
	}

	public void onCreateButtonClicked() {
		// TODO Auto-generated method stub

	}

	public void onUpdateButtonClicked() {
		// TODO Auto-generated method stub

	}

	public void onDeleteButtonClicked() {
		// TODO Auto-generated method stub

	}

	public void updateData() {
		fetchData();
	}
	

	protected void fetchData(){
		view.onLoadDataStart();
		service.getSubstitutions(new AsyncCallback<Substitution[]>() {
			
			public void onFailure(Throwable arg0) {
				arg0.printStackTrace();
				view.onLoadDataError();
			}

			public void onSuccess(Substitution[] result) {
				view.setData(Arrays.asList(result));
				view.onLoadDataFinish();
				
			}
		});
	}
}
