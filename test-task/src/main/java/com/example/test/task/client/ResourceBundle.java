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
		
	@Source("update_active_icon.gif")
	public DataResource updateIconResource();
	
	@Source("update.gif")
	public DataResource updateIconDisabledResource();
	
	@Source("delete_active_icon.gif")
	public DataResource deleteIconResource();
	
	@Source("delete.gif")
	public DataResource deleteIconDisabledResource();
	
	
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
	
	@Source("btn_bg_l.png")
	public DataResource buttonLeftBackgroungResource();
	
	@Source("btn_bg_r.png")
	public DataResource buttonRightBackgroungResource();
	
	@Source("btn_bg_c.png")
	public DataResource buttonCenterBackgroungResource();
	
	@Source("cancel_icon.png")
	public DataResource cancelIconResource();
	
	
	@Source("tab_active_bg_l.jpg")
	public DataResource tabActiveBackgroundLeftResource();
	
	@Source("tab_active_bg_r.jpg")
	public DataResource tabActiveBackgroundRightResource();
	
	@Source("tab_state_bg_l.jpg")
	public DataResource tabBackgroundLeftResource();
	
	@Source("tab_state_bg_r.jpg")
	public DataResource tabBackgroundRightResource();
	
	@Source("tab_nav_bg.gif")
	public DataResource tabNavigationBackgroundRightResource();
	
	@Source("control_panel_bg.gif")
	public DataResource controlPanelBackgroundResource();
	
	
	@Source("date_picker.gif")
	public DataResource datePickerIconResource();
	
	@Source("save_icon.png")
	public DataResource saveButtonIconResource();
	
	public interface Style extends CssResource {
	    String tabStyle();
    }
}
