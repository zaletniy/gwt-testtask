package com.example.test.task.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event to start Edit window. 
 * 
 * @author Ilya Sviridov
 *
 */
public class CreateSubstitutionEvent extends
		GwtEvent<CreateSubstitutionEventHandler> {
	/**
	 * Type constant
	 */
	public static final Type<CreateSubstitutionEventHandler> TYPE = new Type<CreateSubstitutionEventHandler>();
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public GwtEvent.Type<CreateSubstitutionEventHandler> getAssociatedType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(CreateSubstitutionEventHandler handler) {
		handler.createSubstitution();
	}
}
