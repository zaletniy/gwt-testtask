/**
 * 
 */
package com.example.test.task.client.presenter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.test.task.client.AsyncMockStubber;
import com.example.test.task.client.Messages;
import com.example.test.task.client.SubstitutionManagementServiceAsync;
import com.example.test.task.client.event.CreateSubstitutionEvent;
import com.example.test.task.client.event.EditSubstitutionEvent;
import com.example.test.task.client.event.UpdateDataEvent;
import com.example.test.task.client.event.UpdateDataEventHandler;
import com.example.test.task.client.view.StatusIndicator;
import com.example.test.task.client.view.SubstitutionManagementView;
import com.example.test.task.server.SubstitutionManagementServiceImpl;
import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Behavior test for substitution presenter.
 * 
 * @author Ilya Sviridov
 * 
 */
public class SubstitutionManagementPresenterTest {
	EventBus eventBus = new SimpleEventBus();
	Messages messages = mock(Messages.class);
	SubstitutionManagementServiceImpl serviceStub = new SubstitutionManagementServiceImpl();
	@SuppressWarnings("unchecked")
	SubstitutionManagementView<SubstitutionDetails> view = mock(SubstitutionManagementView.class);
	SubstitutionManagementServiceAsync service = mock(SubstitutionManagementServiceAsync.class);
	StatusIndicator statusIndicator = mock(StatusIndicator.class);

	SubstitutionManagementPresenter presenter;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {

		presenter = new SubstitutionManagementPresenter(view, service, statusIndicator, eventBus, messages);

		// reference data retrieving stubbing
		AsyncMockStubber.callSuccessWith(serviceStub.getSubstitutions()).when(service)
				.getSubstitutions(any(AsyncCallback.class));

		// wiring checking
		verify(view).setPresenter(presenter);

	}

	/**
	 * Tests initializing
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testInit() {
		EventBus spyEventBus = spy(eventBus);

		presenter = new SubstitutionManagementPresenter(view, service, statusIndicator, spyEventBus, messages);

		presenter.go();
		verify(view).go();
		verify(view).setData(any(List.class));

		verify(spyEventBus).addHandler(eq(UpdateDataEvent.TYPE), any(UpdateDataEventHandler.class));
	}

	/**
	 * Tests init errors
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testInitServerError() {
		doReturn("Server side problems").when(messages).statusLoadingProblems(anyString());
		AsyncMockStubber.callErrorWith(new RuntimeException("Server side problems")).when(service)
				.getSubstitutions(any(AsyncCallback.class));

		presenter = new SubstitutionManagementPresenter(view, service, statusIndicator, eventBus, messages);

		verify(statusIndicator).setErrorStatus("Server side problems");
		presenter.go();
		verify(view).go();
	}

	/**
	 * 
	 * Tests selectiong handling
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testOnSelectActionHandling() {
		SubstitutionDetails[] selected = new SubstitutionDetails[] { new SubstitutionDetails(),
				new SubstitutionDetails(), new SubstitutionDetails() };
		presenter.onSelect(Arrays.asList(selected));
		verify(view).enableDeleteControl(true);
		verify(view).enableUpdateControl(false);

		reset(view);
		presenter.onSelect(Collections.EMPTY_LIST);
		verify(view).enableDeleteControl(false);
		verify(view).enableUpdateControl(false);

		selected = new SubstitutionDetails[] { new SubstitutionDetails() };
		reset(view);
		presenter.onSelect(Arrays.asList(selected));
		verify(view).enableDeleteControl(true);
		verify(view).enableUpdateControl(true);
	}

	/**
	 * Tests delete action
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testOnDeleteActionHandling() {
		doReturn(serviceStub.getSubstitutions()).when(view).getSelectedItems();

		AsyncMockStubber.callSuccessWith(Collections.EMPTY_LIST).when(service)
				.deleteSubstitution(any(List.class), any(AsyncCallback.class));

		presenter.onDeleteAction();
		verify(view).setData(eq(Collections.EMPTY_LIST));
		verify(view).enableDeleteControl(false);
		verify(view).enableUpdateControl(false);
		verify(statusIndicator).clear();

	}

	/**
	 * Tests updated action
	 */
	@Test
	public void testOnUpdateActionHandling() {
		doReturn(serviceStub.getSubstitutions().subList(0, 1)).when(view).getSelectedItems();

		EventBus spyEventBus = spy(eventBus);

		presenter = new SubstitutionManagementPresenter(view, service, statusIndicator, spyEventBus, messages);

		presenter.onUpdateAction();
		verify(spyEventBus).fireEvent(any(EditSubstitutionEvent.class));
	}

	/**
	 * Tests update action when nothing selected
	 */
	@Test
	public void testOnUpdateActionHandlingNothingSelected() {
		doReturn(serviceStub.getSubstitutions().subList(0, 0)).when(view).getSelectedItems();
		doReturn("statusInternalError").when(messages).statusInternalError();
		EventBus spyEventBus = spy(eventBus);

		presenter = new SubstitutionManagementPresenter(view, service, statusIndicator, spyEventBus, messages);

		presenter.onUpdateAction();
		verify(statusIndicator).setErrorStatus("statusInternalError");
	}

	/**
	 * Tests create event publishing
	 */
	@Test
	public void testOnCreateActionHandling() {
		EventBus spyEventBus = spy(eventBus);

		presenter = new SubstitutionManagementPresenter(view, service, statusIndicator, spyEventBus, messages);

		presenter.onCreateAction();
		verify(spyEventBus).fireEvent(any(CreateSubstitutionEvent.class));
	}

	/**
	 * Tests update event publishing
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testOnUpdateEventHandling() {
		doReturn(serviceStub.getSubstitutions()).when(view).getSelectedItems();
		eventBus.fireEvent(new UpdateDataEvent());
		verify(view).setData(any(List.class));

	}

}
