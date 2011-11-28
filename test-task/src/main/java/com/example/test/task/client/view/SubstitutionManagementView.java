package com.example.test.task.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

public interface SubstitutionManagementView<T> extends IsWidget {
	public interface Presenter<T> {
		void onCreateButtonClicked();

		void onUpdateButtonClicked();

		void onDeleteButtonClicked();
		
		void updateData();
	}

	void setData(List<T> items);

	List<Integer> getCheckedItemIds();

	void setPresenter(Presenter<T> presenter);
	
	void onLoadDataStart();
	void onLoadDataFinish();
	void onLoadDataError();
}
