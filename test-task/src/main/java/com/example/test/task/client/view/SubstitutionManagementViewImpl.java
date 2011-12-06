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

import com.example.test.task.client.Messages;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TabPanel;
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
	private static Messages messages=GWT.create(Messages.class);
	@UiField
	PushButton createButton;
	@UiField
	PushButton updateButton;
	@UiField
	PushButton deleteButton;
	
	@UiField
	PushButton okButton;
	
	@UiField(provided = true)
	CellTable<T> table=new CellTable<T>(10000,TableResources.INSTANCE);
	
	@UiField
	TabPanel tabPanel;
	
	@UiField WindowContainer windowContainer;

	Presenter<T> presenter;

	ProvidesKey<T> keyProvider;

	ListDataProvider<T> listDataProvider = new ListDataProvider<T>();

	MultiSelectionModel<T> selectionModel;

	MutableListHandler<T> sortHandler;
	
	HasWidgets container;

	@SuppressWarnings("rawtypes")
	interface SubstitutionManagementViewImplUiBinder extends
			UiBinder<Widget, SubstitutionManagementViewImpl> {
	}

	/**
	 * The resources applied to the table.
	 */
	interface TableResources extends CellTable.Resources {
		public static TableResources INSTANCE=GWT.create(TableResources.class); 
		@Source({ CellTable.Style.DEFAULT_CSS,
				"com/example/test/task/client/SubstitutionManagementTable.css" })
		TableStyle cellTableStyle();
		
		@Source("com/example/test/task/client/table_heading_bg.gif")
		DataResource tableHeaderBackground();
		
		@Source("com/example/test/task/client/cell_bg.gif")
		DataResource firstColumnBackground();
	}

	/**
	 * The styles applied to the table.
	 */
	interface TableStyle extends CellTable.Style {
	}

	public SubstitutionManagementViewImpl(HasWidgets container) {
		this.container=container;
		initWidget(uiBinder.createAndBindUi(this));
		tabPanel.selectTab(0);
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
		// TODO: F5 refresh button
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
			LinkedHashMap<String, Column<T, ?>> columns,
			LinkedHashMap<String, Comparator<T>> comparators) {
		this.keyProvider = keyProvider;

		listDataProvider.addDataDisplay(table);

		selectionModel = new MultiSelectionModel<T>(keyProvider);

		sortHandler = new MutableListHandler<T>();
		table.addColumnSortHandler(sortHandler);
		table.setSelectionModel(selectionModel,
				DefaultSelectionEventManager.<T> createCheckboxManager());

		// the first checkbox column
		Column<T, Boolean> checkColumn = new Column<T, Boolean>(
				new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(T object) {
				return selectionModel.isSelected(object);
			}
		};

		table.addColumn(checkColumn, "");

		for (Entry<String, Column<T, ?>> entry : columns.entrySet()) {
			table.addColumn(entry.getValue(), entry.getKey());
			sortHandler.setComparator(entry.getValue(),
					comparators.get(entry.getKey()));
		}

		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Set<T> selectedSet = selectionModel.getSelectedSet();
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
	
	@UiHandler("windowContainer")
	void onWindowContainerClick(ClickEvent event) {
		Window.alert(messages.notDefined());
	}
	
	@UiHandler("okButton")
	void onOkClick(ClickEvent event) {
		Window.alert(messages.notDefined());
	}

	public void go() {
		container.clear();
		container.add(this);
	}

}
