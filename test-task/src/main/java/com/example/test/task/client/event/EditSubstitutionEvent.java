package com.example.test.task.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditSubstitutionEvent extends
		GwtEvent<EditSubstitutionEventHandler> {
	public static Type<EditSubstitutionEventHandler> TYPE = new Type<EditSubstitutionEventHandler>();
    final Integer substitutionId; 
    
	public EditSubstitutionEvent(Integer substitutionId) {
		super();
		this.substitutionId = substitutionId;
	}

	@Override
	public GwtEvent.Type<EditSubstitutionEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditSubstitutionEventHandler handler) {
		handler.onSubstitutionEditEvent(this);
	}

	public Integer getSubstitutionId() {
		return substitutionId;
	}
	
}
