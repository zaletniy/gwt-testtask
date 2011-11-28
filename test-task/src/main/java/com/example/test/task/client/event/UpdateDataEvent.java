package com.example.test.task.client.event;

import com.example.test.task.shared.Substitution;
import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.event.shared.GwtEvent;

public class UpdateDataEvent extends
		GwtEvent<UpdateDataEventHandler> {
	public static final Type<UpdateDataEventHandler> TYPE = new Type<UpdateDataEventHandler>();
	private final Substitution substitution;
		
	public UpdateDataEvent(Substitution substitution) {
		super();
		this.substitution = substitution;
	}

	@Override
	public GwtEvent.Type<UpdateDataEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateDataEventHandler handler) {
		handler.onUpdateDataEvent(this);
	}

	public Substitution getSubstitution() {
		return substitution;
	}
	
}
