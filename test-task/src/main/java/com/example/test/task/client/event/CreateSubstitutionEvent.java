package com.example.test.task.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CreateSubstitutionEvent extends
		GwtEvent<CreateSubstitutionEventHandler> {
	public static final Type<CreateSubstitutionEventHandler> TYPE = new Type<CreateSubstitutionEventHandler>();

	@Override
	public GwtEvent.Type<CreateSubstitutionEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateSubstitutionEventHandler handler) {
		handler.createSubstitution();
	}

}
