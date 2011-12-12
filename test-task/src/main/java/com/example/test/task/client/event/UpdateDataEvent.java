package com.example.test.task.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * The event to inform substitution management that data has been updated and
 * shouldbe reloaded from server.
 * 
 * @author Ilya Sviridov
 * 
 */
public class UpdateDataEvent extends GwtEvent<UpdateDataEventHandler> {
	/**
	 * Type constant
	 */
	public static final Type<UpdateDataEventHandler> TYPE = new Type<UpdateDataEventHandler>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public GwtEvent.Type<UpdateDataEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(UpdateDataEventHandler handler) {
		handler.onUpdateDataEvent(this);
	}

}
