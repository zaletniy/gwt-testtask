package com.example.test.task.client;

import java.util.List;

import com.example.test.task.shared.Substitutor;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class TrySpring implements EntryPoint {

	private final SubstitutionManagementServiceAsync substitutionManagementServiceAsync = GWT
			.create(SubstitutionManagementService.class);

	public void onModuleLoad() {
		Button button = new Button("Button");
		RootPanel.get("trySpringApplication").add(button);
		
		final TextBox textBox=new TextBox();
		RootPanel.get("trySpringApplication").add(textBox);
		
		Button saveButton=new Button("Save");
		RootPanel.get("trySpringApplication").add(saveButton);
		
		saveButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				Substitutor substitutor=new Substitutor();
				substitutor.setName(textBox.getText());
				substitutionManagementServiceAsync.saveSubstititor(substitutor,new AsyncCallback<Void>() {

					public void onFailure(Throwable caught) {
						Window.alert("Some error '"
								+ caught.getLocalizedMessage()+"'");
					}

					public void onSuccess(Void result) {
						Window.alert("Data saved Somethig was returned " + result);
					}
					
				});
			}
		});
		
		button.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				substitutionManagementServiceAsync
						.getAllSubstitutors(new AsyncCallback<List<Substitutor>>() {

							public void onSuccess(List<Substitutor> result) {
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
