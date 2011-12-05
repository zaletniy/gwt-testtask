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
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestTask implements EntryPoint {
	static{
		ResourceBundle.INSTANCE.css().ensureInjected();
	}
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final SubstitutionManagementServiceAsync substitutionService = GWT
			.create(SubstitutionManagementService.class);

	private final Messages messages = GWT.create(Messages.class);

	private EventBus eventBus = new SimpleEventBus();
	
	private StatusIndicator statusIndicator=new SpanStatusIndicator();

	EditSubstitutionPresenter editSubstitutionPresenter = null;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		SubstitutionManagementViewImpl<SubstitutionDetails> view = new SubstitutionManagementViewImpl<SubstitutionDetails>();
		SubstitutionManagementPresenter presenter = new SubstitutionManagementPresenter(
				view, substitutionService, statusIndicator, eventBus);
		presenter.go(RootPanel.get("application"));

		EditSubstitutionViewImpl editSubstitutionViewImpl = new EditSubstitutionViewImpl();
		editSubstitutionPresenter = new EditSubstitutionPresenter(eventBus,
				editSubstitutionViewImpl,statusIndicator,substitutionService);


	}
}
