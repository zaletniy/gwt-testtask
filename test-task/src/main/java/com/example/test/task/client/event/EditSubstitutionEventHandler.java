package com.example.test.task.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Edit substitution event handler.
 * 
 * @author Ilya Sviridov
 *
 */
public interface EditSubstitutionEventHandler extends EventHandler {
	/**
	 * Handling method
	 * @param substitution event object
	 */
	void onSubstitutionEditEvent(EditSubstitutionEvent substitution);
}
