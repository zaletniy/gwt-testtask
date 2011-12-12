package com.example.test.task.client.view;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.ProvidesKey;

/**
 * The substitution management view interface.
 * 
 * @author Ilya Sviridov
 * 
 * @param <T>
 */
public interface SubstitutionManagementView<T> {
	/**
	 * Presenter
	 * 
	 * @author Ilya Sviridov
	 * 
	 * @param <T>
	 */
	public interface Presenter<T> {
		/**
		 * Called when create action performed
		 */
		void onCreateAction();

		/**
		 * Called when update action performed
		 */
		void onUpdateAction();

		/**
		 * Called when delete action performed
		 */
		void onDeleteAction();

		/**
		 * Called on selection item
		 */
		void onSelect(Collection<T> selctedItems);
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
	void init(ProvidesKey<T> keyProvider, LinkedHashMap<String, Column<T, ?>> columns,
			LinkedHashMap<String, Comparator<T>> comparators);

	/**
	 * Puts data to view
	 * 
	 * @param items
	 */
	void setData(List<T> items);

	/**
	 * Returns selected items
	 * 
	 * @return
	 */
	Collection<T> getSelectedItems();

	/**
	 * Enables or disables update control
	 * 
	 * @param enabled
	 */
	void enableUpdateControl(boolean enabled);

	/**
	 * Enables or disables delete control
	 * 
	 * @param enabled
	 */
	void enableDeleteControl(boolean enabled);

	/**
	 * Wires with presenter
	 * 
	 * @param presenter
	 */
	void setPresenter(Presenter<T> presenter);

	/**
	 * Go method
	 */
	void go();
}
