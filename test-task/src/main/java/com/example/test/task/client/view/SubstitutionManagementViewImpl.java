/**
 * 
 */
package com.example.test.task.client.view;

import java.util.Comparator;
import java.util.List;

import com.example.test.task.shared.Substitution;
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
	CellTable<Substitution> table = new CellTable<Substitution>(KEY_PROVIDER);
	@UiField Label loadingLable;
	
	Presenter<T> presenter;
	
	Integer selectedItemId=-1;
	
	public static final ProvidesKey<Substitution> KEY_PROVIDER = new ProvidesKey<Substitution>() {
		public Integer getKey(Substitution item) {
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
		
		//table=new CellTable<Substitution>();
		
		ListDataProvider<Substitution> listDataProvider=new ListDataProvider<Substitution>((List<Substitution>)items);
		
		listDataProvider.addDataDisplay(table);
		
		final MultiSelectionModel<Substitution> selectionModel = new MultiSelectionModel<Substitution>(KEY_PROVIDER);
		
		ListHandler<Substitution> sortHandler=new ListHandler<Substitution>(listDataProvider.getList());
		table.addColumnSortHandler(sortHandler);
				
		table.setSelectionModel(selectionModel,DefaultSelectionEventManager.<Substitution>createCheckboxManager());
		
		
		Column<Substitution, Boolean> checkColumn = new Column<Substitution, Boolean>(
				new CheckboxCell(true, true)) {
			@Override
			public Boolean getValue(Substitution object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};
		
		
		table.addColumn(checkColumn,"");
		
		// Name column
		TextColumn<Substitution> nameColumn=new TextColumn<Substitution>() {
			@Override
			public String getValue(Substitution object) {
				return object.getName();
			}
		};
		
		nameColumn.setSortable(true);
		
		sortHandler.setComparator(nameColumn, new Comparator<Substitution>() {
			public int compare(Substitution arg0, Substitution arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		
		table.addColumn(nameColumn,"Substitute");
		
		table.addColumn(new TextColumn<Substitution>() {
			@Override
			public String getValue(Substitution object) {
				return object.getRole();
			}
		},"Role");
		
		table.addColumn(new TextColumn<Substitution>() {
			@Override
			public String getValue(Substitution object) {
				return object.getRuleType();
			}
		},"Rule type");
		
		table.addColumn(new TextColumn<Substitution>() {
			@Override
			public String getValue(Substitution object) {
				//FIXME: localize date formatting
				return object.getStartDate().toString();
			}
		},"Begin");
		
		table.addColumn(new TextColumn<Substitution>() {
			@Override
			public String getValue(Substitution object) {
				//FIXME: localize date formatting
				return object.getEndDate().toString();
			}
		},"End");
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				System.out.println("Event "+event);
			}
		});		

		table.setRowCount(items.size());
		//table.setRowData((List<Substitution>)items);
	}

	public int getSelectedItemId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Integer> getCheckedItemIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPresenter(SubstitutionManagementView.Presenter<T> presenter) {
		this.presenter = presenter;
	}


	public void updateData() {
		
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

}
