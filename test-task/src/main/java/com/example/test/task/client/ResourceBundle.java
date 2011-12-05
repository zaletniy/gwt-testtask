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
	
	@Source("wind_icon2.gif")
	public ImageResource substitutionEditWindowIcon();
	
	@Source("close_btn.gif")
	public ImageResource closeButtonIcon();
	
	@Source("close_btn.gif")
	public DataResource closeButtonIconResource();
	
	@Source("top_left_corner.png")
	public DataResource topLeftCorner();
	
	@Source("top_right_corner.png")
	public DataResource topRightCorner();
	
	@Source("bottom_left_corner.png")
	public DataResource bottomLeftCorner();
	
	@Source("bottom_right_corner.png")
	public DataResource bottomRightCorner();
	
	@Source("top_repeat_center.png")
	public DataResource topCenterBackground();
	
	@Source("left_middle_cen.png")
	public DataResource middleLeftBackground();
	
	@Source("bottom_repeat_center.png")
	public DataResource bottomCenterBackground();
	
	@Source("right_middle_cen.png")
	public DataResource middleRightBackground();
	
	
	public interface Style extends CssResource {
	    String tabStyle();
    }
}
