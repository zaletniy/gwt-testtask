/**
 * 
 */
package com.example.test.task.client.view;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.Handler;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;

/**
 * @author Ilya Sviridov
 * 
 */
public class SubstitutionManagementViewImpl<T> extends Composite implements
		SubstitutionManagementView<T> {

	private static SubstitutionManagementViewImplUiBinder uiBinder = GWT
			.create(SubstitutionManagementViewImplUiBinder.class);
	@UiField
	PushButton createButton;
	@UiField
	PushButton updateButton;
	@UiField
	PushButton deleteButton;
	@UiField(provided = true)
	CellTable<T> table = new CellTable<T>();
	
	Presenter<T> presenter;
	
	ProvidesKey<T> keyProvider;
	
	ListDataProvider<T> listDataProvider=new ListDataProvider<T>();
	
	MultiSelectionModel<T> selectionModel;
	
	@Deprecated
	LinkedHashMap<String, Comparator<T>> comparators;
	
	MutableListHandler<T> sortHandler;
	
	/*
	public static final ProvidesKey<SubstitutionDetails> KEY_PROVIDER = 
	};
	*/

	@SuppressWarnings("rawtypes")
	interface SubstitutionManagementViewImplUiBinder extends
			UiBinder<Widget, SubstitutionManagementViewImpl> {
	}

	public SubstitutionManagementViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public void setData(List<T> items) {
		
		listDataProvider.setList(items);
		sortHandler.setData(listDataProvider.getList());
		table.setRowCount(items.size());
		
		/*
		ListDataProvider<SubstitutionDetails> listDataProvider=new ListDataProvider<SubstitutionDetails>((List<SubstitutionDetails>)items);
		
		listDataProvider.addDataDisplay(table);
		
		final MultiSelectionModel<SubstitutionDetails> selectionModel = new MultiSelectionModel<SubstitutionDetails>(KEY_PROVIDER);
		
		ListHandler<SubstitutionDetails> sortHandler=new ListHandler<SubstitutionDetails>(listDataProvider.getList());
		table.addColumnSortHandler(sortHandler);
				
		table.setSelectionModel(selectionModel,DefaultSelectionEventManager.<SubstitutionDetails>createCheckboxManager());
		
		Column<SubstitutionDetails, Boolean> checkColumn = new Column<SubstitutionDetails, Boolean>(
				new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(SubstitutionDetails object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};
			
		table.addColumn(checkColumn,"");
		
		// Name column
		TextColumn<SubstitutionDetails> nameColumn=new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				return object.getName();
			}
		};
		
		nameColumn.setSortable(true);
		
		sortHandler.setComparator(nameColumn, new Comparator<SubstitutionDetails>() {
			public int compare(SubstitutionDetails arg0, SubstitutionDetails arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		
		table.addColumn(nameColumn,"Substitute");
		
		TextColumn<SubstitutionDetails> roleColumn=new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				return object.getRole();
			}
		};
		
		roleColumn.setSortable(true);
		
		table.addColumn(roleColumn,"Role");
		sortHandler.setComparator(roleColumn, new Comparator<SubstitutionDetails>() {
			public int compare(SubstitutionDetails o1, SubstitutionDetails o2) {
				return o1.getRole().compareTo(o2.getRole());
			}
		});
		
		
		table.addColumn(new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				return object.getRuleType();
			}
		},"Rule type");
		
		table.addColumn(new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				//FIXME: localize date formatting
				//TODO: column should be sortable by date, so it is shouldn't be a text
				return object.getStartDate()!=null?object.getStartDate().toString():"";
			}
		},"Begin");
		
		table.addColumn(new TextColumn<SubstitutionDetails>() {
			@Override
			public String getValue(SubstitutionDetails object) {
				//FIXME: localize date formatting
				return object.getStartDate()!=null?object.getStartDate().toString():"";
			}
		},"End");
		
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Set<SubstitutionDetails> selectedSet=selectionModel.getSelectedSet();
				if(selectedSet.size()==0){
					updateButton.setEnabled(false);
					deleteButton.setEnabled(false);
				}else{
					if(selectedSet.size()==1){
						updateButton.setEnabled(true);
						deleteButton.setEnabled(true);
					}else{
						updateButton.setEnabled(false);
						deleteButton.setEnabled(true);
					}
				}
				
				selectedItems.clear();
				for(SubstitutionDetails item:selectedSet)
					selectedItems.add(item.getId());
			}
		});		

		table.setRowCount(items.size());
        table.getColumnSortList().push(nameColumn);
        */
	}

	public void setPresenter(SubstitutionManagementView.Presenter<T> presenter) {
		this.presenter = presenter;
	}


	public void updateData() {
		//TODO: F5 refresh button
	}


	@UiHandler("createButton")
	void onCreateButtonClick(ClickEvent event) {
		presenter.onCreateAction();
	}
	
	@UiHandler("updateButton")
	void onUpdateButtonClick(ClickEvent event) {
		presenter.onUpdateAction();
	}
	
	@UiHandler("deleteButton")
	void onDeleteButtonClick(ClickEvent event) {
		presenter.onDeleteAction();
	}


	public Collection<T> getSelectedItems() {
		return selectionModel.getSelectedSet();
	}


	public void init(ProvidesKey<T> keyProvider,
			LinkedHashMap<String, Column<T, ?>> columns,LinkedHashMap<String, Comparator<T>> comparators) {
		//this.comparators=comparators;
		this.keyProvider=keyProvider;
		
		listDataProvider.addDataDisplay(table);
		
		selectionModel = new MultiSelectionModel<T>(keyProvider);
		
		sortHandler=new MutableListHandler<T>();
		table.addColumnSortHandler(sortHandler);
		table.setSelectionModel(selectionModel,DefaultSelectionEventManager.<T>createCheckboxManager());
		
		//the first checkbox column
		Column<T, Boolean> checkColumn = new Column<T, Boolean>(new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(T object) {
				return selectionModel.isSelected(object);
			}
		};
			
		table.addColumn(checkColumn,"");
		
		for(Entry<String, Column<T, ?>> entry:columns.entrySet()){
			table.addColumn(entry.getValue(),entry.getKey());
			sortHandler.setComparator(entry.getValue(), comparators.get(entry.getKey()));
		}
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Set<T> selectedSet=selectionModel.getSelectedSet();
				presenter.onSelect(selectedSet);
			}
		});		
	}


	public void enableUpdateControl(boolean enabled) {
		updateButton.setEnabled(enabled);
	}


	public void enableDeleteControl(boolean enabled) {
		deleteButton.setEnabled(enabled);
	}


}
