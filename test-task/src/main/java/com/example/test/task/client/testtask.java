package com.example.test.task.client;

import com.example.test.task.client.presenter.SubstitutionManagementPresenter;
import com.example.test.task.client.view.SubstitutionManagementViewImpl;
import com.example.test.task.shared.Substitution;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestTask implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final SubstitutionManagementServiceAsync substitutionService = GWT.create(SubstitutionManagementService.class);

  private final Messages messages = GWT.create(Messages.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  SubstitutionManagementViewImpl<Substitution> view=new SubstitutionManagementViewImpl<Substitution>();
	  SubstitutionManagementPresenter presenter=new SubstitutionManagementPresenter(view,substitutionService);
	  presenter.go(RootPanel.get());
  }
}
