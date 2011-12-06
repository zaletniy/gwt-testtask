/**
 * 
 */
package com.example.test.task.client.presenter;

import static org.junit.Assert.*;

import static org.easymock.EasyMock.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.test.task.client.SubstitutionManagementService;
import com.example.test.task.client.SubstitutionManagementServiceAsync;
import com.example.test.task.client.TestCallBack;
import com.example.test.task.client.view.StatusIndicator;
import com.example.test.task.client.view.SubstitutionManagementView;
import com.example.test.task.shared.EditViewReferenceData;
import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.junit.tools.GWTTestSuite;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import com.example.test.task.client.Messages;

/**
 * Behavior test for substitution presenter
 * 
 * @author Ilya Sviridov
 * 
 */
public class SubstitutionManagementPresenterTest extends GWTTestSuite {

	@Test
	public void test() {
		EventBus eventBus = new SimpleEventBus();
		
		Messages messages=createNiceMock(Messages.class);
		anyObject();
		SubstitutionManagementView<SubstitutionDetails> viewMock = createMock(SubstitutionManagementView.class);
		SubstitutionManagementServiceAsync serciveMock = createMock(SubstitutionManagementServiceAsync.class);
		StatusIndicator statusIndicatorMock = createMock(StatusIndicator.class);
		
		SubstitutionManagementPresenter presenter = new SubstitutionManagementPresenter(
				viewMock, serciveMock, statusIndicatorMock, eventBus,messages);
		
		presenter.go();
		replay(serciveMock);
		presenter.go();
		
		verify(viewMock);

	}

}
