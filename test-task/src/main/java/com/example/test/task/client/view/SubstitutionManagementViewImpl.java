/**
 * 
 */
package com.example.test.task.client.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

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
	CellTable<SubstitutionDetails> table = new CellTable<SubstitutionDetails>(KEY_PROVIDER);
	@UiField Label loadingLable;
	
	Presenter<T> presenter;
	
	List<Integer> selectedItems=new ArrayList<Integer>();
	
	public static final ProvidesKey<SubstitutionDetails> KEY_PROVIDER = new ProvidesKey<SubstitutionDetails>() {
		public Integer getKey(SubstitutionDetails item) {
			return item.getId();
		}
	};

	@SuppressWarnings("rawtypes")
	interface SubstitutionManagementViewImplUiBinder extends
			UiBinder<Widget, SubstitutionManagementViewImpl> {
	}

	public SubstitutionManagementViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public void setData(List<T> items) {
		
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
	}

	public List<Integer> getCheckedItemIds() {
		return selectedItems;
	}

	public void setPresenter(SubstitutionManagementView.Presenter<T> presenter) {
		this.presenter = presenter;
	}


	public void updateData() {
		//TODO: F5 refresh button
	}


	public void onLoadDataStart() {
		loadingLable.setText("Loading...");
	}


	public void onLoadDataFinish() {
		loadingLable.setText("");
	}


	public void onLoadDataError() {
		loadingLable.setText("Loading error. Please try again later");		
	}

	@UiHandler("createButton")
	void onCreateButtonClick(ClickEvent event) {
		presenter.onCreateButtonClicked();
	}
	
	@UiHandler("updateButton")
	void onUpdateButtonClick(ClickEvent event) {
		presenter.onUpdateButtonClicked();
	}
	
	@UiHandler("deleteButton")
	void onDeleteButtonClick(ClickEvent event) {
		presenter.onDeleteButtonClicked();
	}
}
