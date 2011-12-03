package com.example.test.task.client.view;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.ProvidesKey;

public interface SubstitutionManagementView<T> extends IsWidget {
	public interface Presenter<T> {
		void onCreateAction();

		void onUpdateAction();

		void onDeleteAction();

		void onSelect(Collection<T> selctedItems);
	}

	void init(ProvidesKey<T> keyProvider,
			LinkedHashMap<String, Column<T, ?>> columns,
			LinkedHashMap<String, Comparator<T>> comparators);

	void setData(List<T> items);

	Collection<T> getSelectedItems();

	void enableUpdateControl(boolean enabled);

	void enableDeleteControl(boolean enabled);

	void setPresenter(Presenter<T> presenter);
}
