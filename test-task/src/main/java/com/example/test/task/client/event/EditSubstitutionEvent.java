package com.example.test.task.client.event;

import com.google.gwt.event.shared.GwtEvent;
/**
 * Event for editing or creating substitution starting.
 * 
 * @author Ilya Sviridov
 *
 */
public class EditSubstitutionEvent extends
		GwtEvent<EditSubstitutionEventHandler> {
	/**
	 * Type constant
	 */
	public static Type<EditSubstitutionEventHandler> TYPE = new Type<EditSubstitutionEventHandler>();
	
	/**
	 * Editable substitution id. Can be null
	 */
    final Integer substitutionId; 
    
    /**
     * Constructor
     * @param substitutionId  id if object to edit
     */
	public EditSubstitutionEvent(Integer substitutionId) {
		super();
		this.substitutionId = substitutionId;
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public GwtEvent.Type<EditSubstitutionEventHandler> getAssociatedType() {
		return TYPE;
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditSubstitutionEventHandler handler) {
		handler.onSubstitutionEditEvent(this);
	}
	
	/**
	 * Accessor method
	 * @return  substitution id
	 */
	public Integer getSubstitutionId() {
		return substitutionId;
	}
	
}
