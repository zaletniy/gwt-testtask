package com.example.test.task.client.presenter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.example.test.task.client.AsyncMockStubber;
import com.example.test.task.client.Messages;
import com.example.test.task.client.SubstitutionManagementServiceAsync;
import com.example.test.task.client.event.CreateSubstitutionEvent;
import com.example.test.task.client.event.EditSubstitutionEvent;
import com.example.test.task.client.event.UpdateDataEvent;
import com.example.test.task.client.view.EditSubstitutionView;
import com.example.test.task.client.view.StatusIndicator;
import com.example.test.task.server.SubstitutionManagementServiceImpl;
import com.example.test.task.shared.Substitution;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * BDD test for {@link EditSubstitutionPresenter}
 * 
 * @author Ilya Sviridov
 * 
 */
public class EditSubstitutionPresenterTest {
	EventBus eventBus = new SimpleEventBus();
	Messages messages = mock(Messages.class);
	SubstitutionManagementServiceImpl serviceStub = new SubstitutionManagementServiceImpl();

	EditSubstitutionView view = mock(EditSubstitutionView.class);
	StatusIndicator statusIndicator = mock(StatusIndicator.class);
	SubstitutionManagementServiceAsync service = mock(SubstitutionManagementServiceAsync.class);
	EditSubstitutionPresenter presenter;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {

		presenter = new EditSubstitutionPresenter(eventBus, view, messages, statusIndicator, service);

		// reference data retrieving stubbing
		AsyncMockStubber.callSuccessWith(serviceStub.getAllNamedData()).when(service)
				.getAllNamedData(any(AsyncCallback.class));

		AsyncMockStubber.callSuccessWith(serviceStub.getAllNamedData()).when(service)
				.getAllNamedData(any(AsyncCallback.class));

		AsyncMockStubber.callSuccessWith(24).when(service)
				.saveSubstitution(any(Substitution.class), any(AsyncCallback.class));

		// wiring checking
		verify(view).setPresenter(presenter);

	}

	/**
	 * 
	 * Tests the verification messages during validation
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testEditSubstitutionSaveDataValidationProblems() {
		Substitution substitution = serviceStub.getSubstitution(1);
		// item for editing retrieving
		AsyncMockStubber.callSuccessWith(substitution).when(service).getSubstitution(eq(1), any(AsyncCallback.class));

		eventBus.fireEvent(new EditSubstitutionEvent(1));

		// definition of needed messages
		doReturn("validationRoleIsNotSelected").when(messages).validationRoleIsNotSelected();
		doReturn("validationSubstitutorIsNotSelected").when(messages).validationSubstitutorIsNotSelected();
		doReturn("validationRuleIsNotSelected").when(messages).validationRuleIsNotSelected();
		doReturn("validationBeginDataIsNotSelected").when(messages).validationBeginDataIsNotSelected();
		doReturn("validationEndDataIsNotSelected").when(messages).validationEndDataIsNotSelected();
		doReturn("validationEndCantBeBeforeBeginData").when(messages).validationEndCantBeBeforeBeginData();

		// nothing selected yet
		doReturn("").when(view).getRole();
		doReturn("").when(view).getRuleType();
		doReturn("").when(view).getSubstituror();

		// role selection validation
		{
			presenter.onSaveAction();
			verify(statusIndicator).setWarnStatus(eq("validationRoleIsNotSelected"));
		}

		// role is defined
		doReturn(substitution.getRoleId() + "").when(view).getRole();
		// substitutor selection validation
		{
			presenter.onSaveAction();
			verify(statusIndicator).setWarnStatus(eq("validationSubstitutorIsNotSelected"));
		}

		// defining substitutor
		doReturn(substitution.getSubstitutionNameId() + "").when(view).getSubstituror();
		{// rule type validation
			presenter.onSaveAction();
			verify(statusIndicator).setWarnStatus(eq("validationRuleIsNotSelected"));
		}

		doReturn(substitution.getRuleType().getId() + "").when(view).getRuleType();
		{// begin date type validation
			presenter.onSaveAction();
			verify(statusIndicator).setWarnStatus(eq("validationBeginDataIsNotSelected"));
		}

		doReturn(new Date()).when(view).getBeginDate();
		{// end date validation
			presenter.onSaveAction();
			verify(statusIndicator).setWarnStatus(eq("validationEndDataIsNotSelected"));
		}

		{// begin data should be earlier that end data
			reset(statusIndicator);
			doReturn(new Date(new Date().getTime() - 10000)).when(view).getEndDate();
			presenter.onSaveAction();
			verify(statusIndicator).setWarnStatus(eq("validationEndCantBeBeforeBeginData"));
		}

		doReturn(new Date(new Date().getTime() + 10000)).when(view).getEndDate();
		presenter.onSaveAction();
		verify(view).onDataSavingOk();

	}

	/**
	 * 
	 * Tests handling on begin date set action handling
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testOnEndDataSetActionHandling() {
		Substitution substitution = serviceStub.getSubstitution(1);
		// item for editing retrieving
		AsyncMockStubber.callSuccessWith(substitution).when(service).getSubstitution(eq(1), any(AsyncCallback.class));

		eventBus.fireEvent(new EditSubstitutionEvent(1));

		// definition of needed messages
		doReturn("validationBeginDataIsNotSet").when(messages).validationBeginDataIsNotSet();
		doReturn("validationEndDataIsNotSelected").when(messages).validationEndDataIsNotSet();
		doReturn("validationEndCantBeBeforeBeginData").when(messages).validationEndCantBeBeforeBeginData();

		doReturn(null).when(view).getBeginDate();
		doReturn(null).when(view).getEndDate();

		{// begin data is not set
			reset(statusIndicator);
			presenter.onEndDataSet();
			verify(statusIndicator).clear();
			verify(statusIndicator).setWarnStatus("validationBeginDataIsNotSet");
		}

		doReturn(new Date()).when(view).getBeginDate();
		{// end data is not set
			reset(statusIndicator);
			presenter.onEndDataSet();
			verify(statusIndicator).clear();
			verify(statusIndicator).setWarnStatus("validationEndDataIsNotSelected");
		}

		doReturn(new Date(new Date().getTime() - 10000)).when(view).getEndDate();
		{// end data is not set
			reset(statusIndicator);
			presenter.onEndDataSet();
			verify(statusIndicator).clear();
			verify(statusIndicator).setWarnStatus("validationEndCantBeBeforeBeginData");
		}

		doReturn(new Date(new Date().getTime() + 10000)).when(view).getEndDate();
		{// all is ok
			reset(statusIndicator);
			presenter.onEndDataSet();
			verify(statusIndicator).clear();
			verify(statusIndicator, never()).setWarnStatus(anyString());
		}
	}

	/**
	 * 
	 * Tests saving data
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSavingData() {
		// let us spy event buss to make sure that event is fired
		EventBus spyEventBus = spy(eventBus);
		presenter = new EditSubstitutionPresenter(spyEventBus, view, messages, statusIndicator, service);

		Substitution substitution = serviceStub.getSubstitution(1);
		// item for editing retrieving
		AsyncMockStubber.callSuccessWith(substitution).when(service).getSubstitution(eq(1), any(AsyncCallback.class));

		eventBus.fireEvent(new EditSubstitutionEvent(1));

		doReturn(substitution.getRoleId() + "").when(view).getRole();
		doReturn(substitution.getSubstitutionNameId() + "").when(view).getSubstituror();
		doReturn(substitution.getRuleType().getId() + "").when(view).getRuleType();
		doReturn(new Date()).when(view).getBeginDate();
		doReturn(new Date()).when(view).getEndDate();

		// usually bad smell, but not for such active used object as status
		// indicator
		reset(statusIndicator);
		presenter.onSaveAction();
		verify(spyEventBus).fireEvent(any(UpdateDataEvent.class));
		verify(view).onDataSavingOk();
		verify(statusIndicator).clear();

	}

	/**
	 * 
	 * Tests validation behavior on begin date > end date
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testEditNonIntervalData() {
		// let us spy event buss to make sure that event is fired
		EventBus spyEventBus = spy(eventBus);
		presenter = new EditSubstitutionPresenter(spyEventBus, view, messages, statusIndicator, service);

		Substitution substitution = serviceStub.getSubstitution(4);
		// item for editing retrieving
		AsyncMockStubber.callSuccessWith(substitution).when(service).getSubstitution(eq(1), any(AsyncCallback.class));

		eventBus.fireEvent(new EditSubstitutionEvent(1));

		doReturn(substitution.getRoleId() + "").when(view).getRole();
		doReturn(substitution.getSubstitutionNameId() + "").when(view).getSubstituror();
		doReturn(substitution.getRuleType().getId() + "").when(view).getRuleType();
		doReturn(new Date()).when(view).getBeginDate();
		doReturn(new Date()).when(view).getEndDate();

		verify(view, times(4)).setTimeIntervalEnabled(false);

		// usually bad smell, but not for such active used object as status
		// indicator
		reset(statusIndicator);
		presenter.onSaveAction();
		verify(spyEventBus).fireEvent(any(UpdateDataEvent.class));
		verify(view).onDataSavingOk();
		verify(statusIndicator).clear();
	}

	/**
	 * 
	 * Tests creating substition rule
	 * 
	 */
	@Test
	public void testCreatingSubstitutionRule() {
		eventBus.fireEvent(new CreateSubstitutionEvent());
		Substitution substitution = serviceStub.getSubstitution(4);

		doReturn(substitution.getRoleId() + "").when(view).getRole();
		doReturn(substitution.getSubstitutionNameId() + "").when(view).getSubstituror();
		doReturn("1").when(view).getRuleType();
		doReturn(new Date()).when(view).getBeginDate();
		doReturn(new Date()).when(view).getEndDate();
		presenter.onSaveAction();

		verify(view).onDataSavingOk();
	}

	/**
	 * Tests editing
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testEditSubstitutionEditEventHandling() {

		// item for editing retrieving
		AsyncMockStubber.callSuccessWith(serviceStub.getSubstitution(4)).when(service)
				.getSubstitution(eq(1), any(AsyncCallback.class));

		eventBus.fireEvent(new EditSubstitutionEvent(4));

		verify(service).getSubstitution(eq(4), any(AsyncCallback.class));
		verify(view).setRoles(anyMap());
		verify(view).setRuleTypes(anyMap());
		verify(view).setSubstitors(anyMap());
		verify(view).setTimeIntervalEnabled(false);
	}

	/**
	 * 
	 * Tests selection handling
	 */
	@Test
	public void testOnRuleTypeSelection() {
		eventBus.fireEvent(new CreateSubstitutionEvent());

		{// if selected empty item
			reset(view);
			doReturn("").when(view).getRuleType();
			presenter.onRuleTypesSelection();
			verify(view).setTimeIntervalEnabled(false);
		}

		{// if selected non interval rule type
			reset(view);
			doReturn("1").when(view).getRuleType();
			presenter.onRuleTypesSelection();
			verify(view).setTimeIntervalEnabled(false);
		}

		{// if selected interval rule type
			reset(view);
			doReturn("2").when(view).getRuleType();
			presenter.onRuleTypesSelection();
			verify(view).setTimeIntervalEnabled(true);
		}

	}

	/**
	 * Tests error case handling
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testServerErrors() {
		doReturn("Server side problems").when(messages).statusLoadingProblems(anyString());
		AsyncMockStubber.callErrorWith(new RuntimeException("Server side problems")).when(service)
				.getAllNamedData(any(AsyncCallback.class));

		eventBus.fireEvent(new EditSubstitutionEvent(1));
		verify(statusIndicator).setWarnStatus("Server side problems");
	}

	/**
	 * Tests error case handling
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testServerErrorLoadSubstitution() {
		doReturn("Server side problems").when(messages).statusLoadingProblems(anyString());
		AsyncMockStubber.callErrorWith(new RuntimeException("Server side problems")).when(service)
				.getSubstitution(eq(1), any(AsyncCallback.class));
		eventBus.fireEvent(new EditSubstitutionEvent(1));
		verify(statusIndicator).setErrorStatus("Server side problems");
	}

}
