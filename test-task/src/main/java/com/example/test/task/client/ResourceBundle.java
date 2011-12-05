package com.example.test.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;

public interface ResourceBundle extends ClientBundle {
    public static final ResourceBundle INSTANCE = GWT.create(ResourceBundle.class);
    
	@Source("ApplicationStyle.css")
    @CssResource.NotStrict
    public Style css();
		
	@Source("create.gif")
	public ImageResource createIcon();
	
	@Source("create.gif")
	public DataResource createIconResource();
	
	@Source("wind_icon.gif")
	public ImageResource substitutionWindowIcon();
	
	@Source("close_btn.gif")
	public ImageResource closeButtonIcon();
	
	@Source("close_btn.gif")
	public DataResource closeButtonIconResource();
	
	public interface Style extends CssResource {
	    String tabStyle();
    }
}
