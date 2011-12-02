package com.example.test.task.client.presenter;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.test.task.client.SubstitutionManagementServiceAsync;
import com.example.test.task.client.event.CreateSubstitutionEvent;
import com.example.test.task.client.event.CreateSubstitutionEventHandler;
import com.example.test.task.client.event.EditSubstitutionEvent;
import com.example.test.task.client.event.EditSubstitutionEventHandler;
import com.example.test.task.client.event.UpdateDataEvent;
import com.example.test.task.client.view.EditSubstitutionView;
import com.example.test.task.client.view.StatusIndicator;
import com.example.test.task.shared.EditViewReferenceData;
import com.example.test.task.shared.NamedData;
import com.example.test.task.shared.RuleType;
import com.example.test.task.shared.Substitution;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class EditSubstitutionPresenter implements Presenter,
		com.example.test.task.client.view.EditSubstitutionView.Presenter {

	EventBus eventBus;
	final EditSubstitutionView view;
	SubstitutionManagementServiceAsync service;
	boolean isInited = false;

	Substitution substitution;

	Map<String, NamedData> roles;
	Map<String, NamedData> substitutors;
	Map<String, RuleType> ruleTypes;
	StatusIndicator statusIndicator;

	public EditSubstitutionPresenter(EventBus eventBus,
			EditSubstitutionView view, StatusIndicator statusIndicator,
			SubstitutionManagementServiceAsync service) {
		super();
		this.eventBus = eventBus;
		this.view = view;
		this.service = service;
		this.statusIndicator = statusIndicator;
		view.setPresenter(this);

		eventBus.addHandler(EditSubstitutionEvent.TYPE,
				new EditSubstitutionEventHandler() {
					public void onSubstitutionEditEvent(
							EditSubstitutionEvent event) {
						editData(event.getSubstitutionId());
					}
				});

		eventBus.addHandler(CreateSubstitutionEvent.TYPE,
				new CreateSubstitutionEventHandler() {
					public void createSubstitution() {
						editData(null);
					}
				});

	}

	protected void init() {
		statusIndicator.setInfoStatus("Loading data...");
		service.getAllNamedData(new AsyncCallback<EditViewReferenceData>() {
			public void onSuccess(EditViewReferenceData result) {
				roles = new HashMap<String, NamedData>();
				for (NamedData role : result.getRoles())
					roles.put(Integer.toString(role.getId()), role);

				ruleTypes = new HashMap<String, RuleType>();
				for (RuleType ruleType : result.getRuleTypes())
					ruleTypes.put(Integer.toString(ruleType.getId()), ruleType);

				substitutors = new HashMap<String, NamedData>();
				for (NamedData substitutor : result.getSubstitutors())
					substitutors.put(Integer.toString(substitutor.getId()),
							substitutor);

				view.setRoles(namedDataToMap(result.getRoles(), " - Select role -"));
				view.setRuleTypes(namedDataToMap(result.getRuleTypes(),
						" - Select rule -"));
				view.setSubstitors(namedDataToMap(result.getSubstitutors(),
						" - Select substitutor -"));
				view.setTimeIntervalEnabled(false);
				
				statusIndicator.clear();
			}

			public void onFailure(Throwable caught) {
				statusIndicator.setWarnStatus("Error :"
						+ caught.getLocalizedMessage());
			}
		});
	}

	public void editData(Integer substitutionId) {
		if (!isInited)
			init();

		if (substitutionId != null) {
			statusIndicator.setInfoStatus("Loading data...");
			service.getSubstitution(substitutionId,
					new AsyncCallback<Substitution>() {
						public void onFailure(Throwable caught) {
							statusIndicator.setErrorStatus("Some error"
									+ caught.getLocalizedMessage());
						}

						public void onSuccess(Substitution result) {
							statusIndicator.clear();
							substitution = result;
							setData(view, substitution);
							view.go();
						}
					});
		} else {
			this.substitution = null;
			resetView();
			view.go();
		}
	}

	public void onCancelAction() {
		// TODO some activity or just ignore.
	}

	public void onSaveAction() {
		view.setSaveControllEnabled(false);
		view.setCancelControllEnabled(false);

		if (!isDataValid()) {
			view.setSaveControllEnabled(true);
			view.setCancelControllEnabled(true);
		} else {
			if(this.substitution==null){
				this.substitution=new Substitution();
			}
			substitution.setSubstitutionNameId(substitutors.get(view.getSubstituror()).getId());
			substitution.setRoleId(roles.get(view.getRole()).getId());
			substitution.setRuleType(ruleTypes.get(view.getRuleType()));
			if (substitution.getRuleType().isInterval()) {
				substitution.setBeginDate(view.getBeginDate());
				substitution.setEndDate(view.getEndDate());
			}
			
			statusIndicator.setInfoStatus("Saving substitution...");
			service.saveSubstitution(substitution,
					new AsyncCallback<Integer>() {
						public void onFailure(Throwable caught) {
							statusIndicator.setErrorStatus("Saving substitution error: "+caught.getLocalizedMessage());
							view.setSaveControllEnabled(true);
							view.setCancelControllEnabled(true);
						}

						public void onSuccess(Integer result) {
							eventBus.fireEvent(new UpdateDataEvent());
							view.onDataSavingOk();
							resetView();
							statusIndicator.clear();
						}
					});
		}
	}

	public void onEndDataSet() {
		statusIndicator.clear();
		if(view.getBeginDate()==null){
			statusIndicator.setWarnStatus("Begin data is not set");
			return;
		}
		if(view.getEndDate()==null){
			statusIndicator.setWarnStatus("End data is not set");
			return;
		}
		
		if(view.getEndDate().before(view.getBeginDate())){
			statusIndicator.setWarnStatus("End can't be before begin data");
			return;
		}
	}

	public void onRuleTypesSelection() {
		String selectedRuleType = view.getRuleType();
		if ("".equals(selectedRuleType)) {
			view.setTimeIntervalEnabled(false);
		} else {
			if (ruleTypes.get(selectedRuleType).isInterval()) {
				view.setTimeIntervalEnabled(true);
			} else {
				view.setTimeIntervalEnabled(false);
			}
		}
	}

	public void go(HasWidgets container) {
	}

	private void setData(EditSubstitutionView view, Substitution substitution) {
		view.setRole(Integer.toString(substitution.getRoleId()));

		view.setSubstitutor(Integer.toString(substitution
				.getSubstitutionNameId()));

		if (substitution.getRuleType().isInterval()) {
			view.setBeginDate(substitution.getBeginDate());
			view.setEndDate(substitution.getEndDate());
		} else {
			view.setEndDate(null);
			view.setEndDate(null);
			view.setTimeIntervalEnabled(false);
		}

		view.setRuleType(Integer.toString(substitution.getRuleType().getId()));
	}

	private Map<String, String> namedDataToMap(
			Collection<? extends NamedData> datas, String emptyItemName) {
		Map<String, String> hashMap = new LinkedHashMap<String, String>();
		if (emptyItemName != null) {
			hashMap.put(emptyItemName, "");
		}
		for (NamedData data : datas) {
			hashMap.put(data.getName(), Integer.toString(data.getId()));
		}

		return hashMap;
	}

	private void resetView() {
		view.setRole("");
		view.setSubstitutor("");
		view.setRuleType("");
		view.setBeginDate(null);
		view.setEndDate(null);
		view.setTimeIntervalEnabled(false);
		view.setCancelControllEnabled(true);
		view.setSaveControllEnabled(true);
	}

	private boolean isDataValid() {
		if (view.getRole().isEmpty()) {
			statusIndicator.setWarnStatus("The role is not selected");
			return false;
		}

		if (view.getSubstituror().isEmpty()) {
			statusIndicator.setWarnStatus("The substitutor is not selected");
			return false;
		}

		if (view.getRuleType().isEmpty()) {
			statusIndicator.setWarnStatus("The rule is not selected");
			return false;
		}

		if (ruleTypes.get(view.getRuleType()).isInterval()) {
			if (view.getBeginDate() == null) {
				statusIndicator.setWarnStatus("The beginDate is not selected");
				return false;
			}
			if (view.getEndDate() == null) {
				statusIndicator.setWarnStatus("The endDate is not selected");
				return false;
			}
		}

		return true;

	}

}
