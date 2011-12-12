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
 * GWT impelemntation of {@link SubstitutionManagementView}.
 * 
 * @author Ilya Sviridov
 * 
 */
public class SubstitutionManagementViewImpl<T> extends Composite implements SubstitutionManagementView<T> {

	/**
	 * UIBinder
	 */
	private static SubstitutionManagementViewImplUiBinder uiBinder = GWT
			.create(SubstitutionManagementViewImplUiBinder.class);
	/**
	 * Messages
	 */
	private static Messages messages = GWT.create(Messages.class);

	/**
	 * Create button
	 */
	@UiField
	PushButton createButton;

	/**
	 * Update button
	 */
	@UiField
	PushButton updateButton;

	/**
	 * Delete button
	 */
	@UiField
	PushButton deleteButton;

	/**
	 * Ok button
	 */
	@UiField
	PushButton okButton;

	/**
	 * Table
	 */
	@UiField(provided = true)
	CellTable<T> table = new CellTable<T>(10000, TableResources.INSTANCE);

	/**
	 * Tab panel
	 */
	@UiField
	TabPanel tabPanel;

	/**
	 * Window container {@link WindowContainer}
	 */
	@UiField
	WindowContainer windowContainer;

	/**
	 * Presenter
	 */
	Presenter<T> presenter;

	/**
	 * Key provider
	 */
	ProvidesKey<T> keyProvider;

	/**
	 * Data provider
	 */
	ListDataProvider<T> listDataProvider = new ListDataProvider<T>();

	/**
	 * Selection model
	 */
	MultiSelectionModel<T> selectionModel;

	/**
	 * Data handler
	 */
	MutableListHandler<T> sortHandler;

	/**
	 * Contaner
	 */
	HasWidgets container;

	/**
	 * 
	 * Binding interface
	 * 
	 * @author Ilya Sviridov
	 * 
	 */
	@SuppressWarnings("rawtypes")
	interface SubstitutionManagementViewImplUiBinder extends UiBinder<Widget, SubstitutionManagementViewImpl> {
	}

	/**
	 * The resources applied to the table.
	 */
	interface TableResources extends CellTable.Resources {
		/**
		 * Resource instance
		 */
		public static TableResources INSTANCE = GWT.create(TableResources.class);

		/**
		 * CSS styles. Based on default
		 */
		@Source({ CellTable.Style.DEFAULT_CSS, "com/example/test/task/client/SubstitutionManagementTable.css" })
		TableStyle cellTableStyle();

		/**
		 * Table header background image resource
		 */
		@Source("com/example/test/task/client/table_heading_bg.gif")
		DataResource tableHeaderBackground();

		/**
		 * First column background image
		 * 
		 * @return
		 */
		@Source("com/example/test/task/client/cell_bg.gif")
		DataResource firstColumnBackground();
	}

	/**
	 * The styles applied to the table.
	 */
	interface TableStyle extends CellTable.Style {
	}

	/**
	 * Constructor
	 * 
	 * @param container
	 */
	public SubstitutionManagementViewImpl(HasWidgets container) {
		this.container = container;
		initWidget(uiBinder.createAndBindUi(this));
		tabPanel.selectTab(0);
	}

	/**
	 * Puts data to view
	 * 
	 * @param items
	 */
	public void setData(List<T> items) {
		listDataProvider.setList(items);
		sortHandler.setData(listDataProvider.getList());
		table.setRowCount(items.size());
	}

	/**
	 * Wires with presenter
	 * 
	 * @param presenter
	 */
	public void setPresenter(SubstitutionManagementView.Presenter<T> presenter) {
		this.presenter = presenter;
	}

	/**
	 * Create button event handler
	 * 
	 * @param event
	 */
	@UiHandler("createButton")
	void onCreateButtonClick(ClickEvent event) {
		presenter.onCreateAction();
	}

	/**
	 * Update button event handler
	 * 
	 * @param event
	 */
	@UiHandler("updateButton")
	void onUpdateButtonClick(ClickEvent event) {
		presenter.onUpdateAction();
	}

	/**
	 * Delete button event handler
	 * 
	 * @param event
	 */
	@UiHandler("deleteButton")
	void onDeleteButtonClick(ClickEvent event) {
		presenter.onDeleteAction();
	}

	/**
	 * Returns selected items
	 * 
	 * @return
	 */
	public Collection<T> getSelectedItems() {
		return selectionModel.getSelectedSet();
	}

	/**
	 * Init method
	 * 
	 * @param keyProvider
	 *            key provider
	 * @param columns
	 *            columns definition (the checkbox column is defined in view)
	 * @param comparators
	 *            comparators to make columns sortable
	 */
	public void init(ProvidesKey<T> keyProvider, LinkedHashMap<String, Column<T, ?>> columns,
			LinkedHashMap<String, Comparator<T>> comparators) {
		this.keyProvider = keyProvider;

		listDataProvider.addDataDisplay(table);

		selectionModel = new MultiSelectionModel<T>(keyProvider);

		sortHandler = new MutableListHandler<T>();
		table.addColumnSortHandler(sortHandler);
		table.setSelectionModel(selectionModel, DefaultSelectionEventManager.<T> createCheckboxManager());

		// the first checkbox column
		Column<T, Boolean> checkColumn = new Column<T, Boolean>(new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(T object) {
				return selectionModel.isSelected(object);
			}
		};

		table.addColumn(checkColumn, "");

		for (Entry<String, Column<T, ?>> entry : columns.entrySet()) {
			table.addColumn(entry.getValue(), entry.getKey());
			sortHandler.setComparator(entry.getValue(), comparators.get(entry.getKey()));
		}

		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Set<T> selectedSet = selectionModel.getSelectedSet();
				presenter.onSelect(selectedSet);
			}
		});
	}

	/**
	 * Enables or disables update control
	 * 
	 * @param enabled
	 */
	public void enableUpdateControl(boolean enabled) {
		updateButton.setEnabled(enabled);
	}

	/**
	 * Enables or disables delete control
	 * 
	 * @param enabled
	 */
	public void enableDeleteControl(boolean enabled) {
		deleteButton.setEnabled(enabled);
	}

	/**
	 * Window close handler. For now do nothing real.
	 * @param event
	 */
	@UiHandler("windowContainer")
	void onWindowContainerClick(ClickEvent event) {
		Window.alert(messages.notDefined());
	}

	/**
	 * OK button handler. For now do nothing real.
	 * @param event
	 */
	@UiHandler("okButton")
	void onOkClick(ClickEvent event) {
		Window.alert(messages.notDefined());
	}
	
	/**
	 * Go method
	 */
	public void go() {
		container.clear();
		container.add(this);
	}

}
