package com.example.test.task.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Updated date event handler.
 * @author Ilya Sviridov
 *
 */
public interface UpdateDataEventHandler extends EventHandler {
	/**
	 * Handling method 
	 * @param event update date event
	 */
	void onUpdateDataEvent(UpdateDataEvent event);
}
