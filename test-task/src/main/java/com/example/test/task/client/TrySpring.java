package com.example.test.task.client;

import java.util.List;

import com.example.test.task.shared.NamedData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class TrySpring implements EntryPoint {

	private final SubstitutionManagementServiceAsync substitutionManagementServiceAsync = GWT
			.create(SubstitutionManagementService.class);

	public void onModuleLoad() {
		Button button = new Button("Button");
		RootPanel.get("tryspring").add(button);
		button.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				substitutionManagementServiceAsync
						.getAllNamedDataFromDB(new AsyncCallback<List<NamedData>>() {

							public void onSuccess(List<NamedData> result) {
								Window.alert("Somethig was returned " + result);
							}

							public void onFailure(Throwable caught) {
								Window.alert("Some error "
										+ caught.getLocalizedMessage());
							}
						});
			}
		});
	}

}
