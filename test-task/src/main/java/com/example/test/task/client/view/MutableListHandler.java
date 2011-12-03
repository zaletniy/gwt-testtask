package com.example.test.task.client.view;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.Handler;

public class MutableListHandler<T> implements Handler {
	private List<T> list;
	private final Map<Column<?, ?>, Comparator<T>> comparators = new HashMap<Column<?, ?>, Comparator<T>>();

	public MutableListHandler() {
	}

	public void onColumnSort(ColumnSortEvent event) {
		Column<?, ?> column = event.getColumn();
		if (column == null) {
			return;
		}

		final Comparator<T> comparator = comparators.get(column);
		if (comparator == null) {
			return;
		}
		if (event.isSortAscending()) {
			Collections.sort(list, comparator);
		} else {
			Collections.sort(list, new Comparator<T>() {
				public int compare(T o1, T o2) {
					return -comparator.compare(o1, o2);
				}
			});
		}
	}

	public void setData(List<T> data) {
		list = data;
	}

	public void setComparator(Column<T, ?> column, Comparator<T> comparator) {
		comparators.put(column, comparator);
	}
}
