package com.example.test.task.client.presenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.test.task.client.Messages;
import com.example.test.task.client.NonStringMessages;
import com.example.test.task.client.SubstitutionManagementServiceAsync;
import com.example.test.task.client.event.CreateSubstitutionEvent;
import com.example.test.task.client.event.EditSubstitutionEvent;
import com.example.test.task.client.event.UpdateDataEvent;
import com.example.test.task.client.event.UpdateDataEventHandler;
import com.example.test.task.client.view.StatusIndicator;
import com.example.test.task.client.view.SubstitutionManagementView;
import com.example.test.task.shared.SubstitutionDetails;
import com.example.test.task.shared.Utils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.ProvidesKey;

public class SubstitutionManagementPresenter implements Presenter,
		SubstitutionManagementView.Presenter<SubstitutionDetails> {
	private Messages messages=GWT.create(Messages.class);
	private NonStringMessages nonStringMessages=GWT.create(NonStringMessages.class);
	SubstitutionManagementView<SubstitutionDetails> view;
	SubstitutionManagementServiceAsync service;
	StatusIndicator statusIndicator;

	EventBus eventBus;

	public SubstitutionManagementPresenter(
			SubstitutionManagementView<SubstitutionDetails> view,
			SubstitutionManagementServiceAsync service,StatusIndicator statusIndicator, EventBus eventBus) {
		super();
		this.view = view;
		this.service = service;
		this.eventBus = eventBus;
		this.statusIndicator=statusIndicator;
		view.setPresenter(this);
		initView();
		fetchData();

		eventBus.addHandler(UpdateDataEvent.TYPE, new UpdateDataEventHandler() {
			public void onUpdateDataEvent(UpdateDataEvent event) {
				fetchData();
			}
		});
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
	}

	public void onCreateAction() {
		eventBus.fireEvent(new CreateSubstitutionEvent());
	}

	public void onUpdateAction() {
		if(view.getSelectedItems().size()==1){
			 eventBus.fireEvent(new EditSubstitutionEvent(view.getSelectedItems().iterator().next().getId()));
		}else{
			statusIndicator.setErrorStatus("Internal error");
		}
	}

	@SuppressWarnings("rawtypes")
	public void onDeleteAction() {
		
		Collection<SubstitutionDetails> selectedItems=view.getSelectedItems();
		if(selectedItems.isEmpty()) return;
		 
		statusIndicator.setInfoStatus("Deleting...");
		List<Integer> ids=new ArrayList<Integer>();
		for(SubstitutionDetails item:selectedItems)
			ids.add(item.getId());
		
		service.deleteSubstitution(ids,new AsyncCallback<List>() {
			public void onFailure(Throwable caught) {
				statusIndicator.setErrorStatus("Error during deleting "+caught.getMessage());				
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(List result) {
				view.setData(result);
				statusIndicator.clear();
				view.enableDeleteControl(false);
				view.enableUpdateControl(false);
			}
		}); 
	}

	@SuppressWarnings("rawtypes")
	protected void fetchData() {
		
		  statusIndicator.setInfoStatus("Loading data...");
		  service.getSubstitutions(new AsyncCallback<List>() {
			
			@SuppressWarnings("unchecked")
			public void onSuccess(List result) {
				view.setData(result);
				statusIndicator.clear();				
			}
			
			public void onFailure(Throwable caught) {
				statusIndicator.setErrorStatus("Loading problems: "+caught.getMessage());				
			}
		});
		 
	}

	public void onSelect(Collection<SubstitutionDetails> selctedItems) {
		if (selctedItems.size() == 1) {
			view.enableDeleteControl(true);
			view.enableUpdateControl(true);
		} else {
			// more than one selected
			if (selctedItems.size() > 0) {
				view.enableDeleteControl(true);
				view.enableUpdateControl(false);
			} else {
				// nothing selected
				view.enableDeleteControl(false);
				view.enableUpdateControl(false);
			}
		}
	}

	private void initView(){
		ProvidesKey<SubstitutionDetails> providesKey=new ProvidesKey<SubstitutionDetails>() {
			public Integer getKey(SubstitutionDetails item) {
				return item.getId();
			};
		};
		
		LinkedHashMap<String, Column<SubstitutionDetails, ?>> columns=new LinkedHashMap<String, Column<SubstitutionDetails, ?>>();
		LinkedHashMap<String, Comparator<SubstitutionDetails>> comparators=new LinkedHashMap<String, Comparator<SubstitutionDetails>>();
		
		//name column
		TextColumn<SubstitutionDetails> nameColumn=new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				return object.getName();
			}
		};
		nameColumn.setSortable(true);
		
		columns.put(messages.tableColumnNameSubstitute(), nameColumn);
		comparators.put(messages.tableColumnNameSubstitute(), new Comparator<SubstitutionDetails>() {
			public int compare(SubstitutionDetails o1, SubstitutionDetails o2) {
				return Utils.compare(o1.getName(), o2.getName());
			}
		});
		
		
		//role column
		TextColumn<SubstitutionDetails> roleColumn=new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				//messages.
				return object.getRole();
			}
		};
		roleColumn.setSortable(true);
		
		columns.put(messages.tableColumnNameRole(), roleColumn);
		comparators.put(messages.tableColumnNameRole(), new Comparator<SubstitutionDetails>() {
			public int compare(SubstitutionDetails o1, SubstitutionDetails o2) {
				return Utils.compare(o1.getRole(), o2.getRole());
			}
		});
		
		

		//ruletype column
		TextColumn<SubstitutionDetails> ruleTypeColumn=new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				return object.getRuleType();
			}
		};
		ruleTypeColumn.setSortable(true);
		
		columns.put(messages.tableColumnNameRuleType(), ruleTypeColumn);
		comparators.put(messages.tableColumnNameRuleType(), new Comparator<SubstitutionDetails>() {
			public int compare(SubstitutionDetails o1, SubstitutionDetails o2) {
				return Utils.compare(o1.getRuleType(), o2.getRuleType());
			}
		});
		
		
		//beginDate column
		TextColumn<SubstitutionDetails> beginDateColumn=new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				return object.getEndDate()!=null?nonStringMessages.dateFormat(object.getBeginDate()):"";
			}
		};
		beginDateColumn.setSortable(true);
		
		columns.put(messages.tableColumnNameBegin(), beginDateColumn);
		comparators.put(messages.tableColumnNameBegin(), new Comparator<SubstitutionDetails>() {
			public int compare(SubstitutionDetails o1, SubstitutionDetails o2) {
				return Utils.compare(o1.getBeginDate(), o2.getBeginDate());
			}
		});
		
		
		//beginDate column
		TextColumn<SubstitutionDetails> endDateColumn=new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				return object.getEndDate()!=null?nonStringMessages.dateFormat(object.getEndDate()):"";
			}
		};
		endDateColumn.setSortable(true);
		
		columns.put(messages.tableColumnNameEnd(), endDateColumn);
		comparators.put(messages.tableColumnNameEnd(), new Comparator<SubstitutionDetails>() {
			public int compare(SubstitutionDetails o1, SubstitutionDetails o2) {
				return Utils.compare(o1.getEndDate(), o2.getEndDate());
			}
		});

		view.init(providesKey, columns,comparators);
	}
	
}
