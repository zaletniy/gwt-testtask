package com.example.test.task.client;

import com.example.test.task.client.presenter.EditSubstitutionPresenter;
import com.example.test.task.client.presenter.SubstitutionManagementPresenter;
import com.example.test.task.client.view.EditSubstitutionViewImpl;
import com.example.test.task.client.view.SpanStatusIndicator;
import com.example.test.task.client.view.StatusIndicator;
import com.example.test.task.client.view.SubstitutionManagementViewImpl;
import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.example.test.task.client.Messages;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestTask implements EntryPoint {
	static{
		ResourceBundle.INSTANCE.css().ensureInjected();
	}

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final SubstitutionManagementServiceAsync substitutionService = GWT
			.create(SubstitutionManagementService.class);

	private EventBus eventBus = new SimpleEventBus();
	
	private StatusIndicator statusIndicator=new SpanStatusIndicator();
	
	private Messages messages=GWT.create(Messages.class);

	EditSubstitutionPresenter editSubstitutionPresenter = null;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		SubstitutionManagementViewImpl<SubstitutionDetails> view = new SubstitutionManagementViewImpl<SubstitutionDetails>(RootPanel.get("application"));
		SubstitutionManagementPresenter presenter = new SubstitutionManagementPresenter(
				view, substitutionService, statusIndicator, eventBus,messages);
		presenter.go();

		EditSubstitutionViewImpl editSubstitutionViewImpl = new EditSubstitutionViewImpl();
		editSubstitutionPresenter = new EditSubstitutionPresenter(eventBus,
				editSubstitutionViewImpl,statusIndicator,substitutionService);


	}
}
