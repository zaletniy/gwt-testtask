package com.example.test.task.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateDataEvent extends GwtEvent<UpdateDataEventHandler> {
	public static final Type<UpdateDataEventHandler> TYPE = new Type<UpdateDataEventHandler>();

	public UpdateDataEvent() {
		super();
	}

	@Override
	public GwtEvent.Type<UpdateDataEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateDataEventHandler handler) {
		handler.onUpdateDataEvent(this);
	}

}
