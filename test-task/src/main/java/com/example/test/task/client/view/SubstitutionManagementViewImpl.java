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
	
	MutableListHandler<T> sortHandler;
	
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
