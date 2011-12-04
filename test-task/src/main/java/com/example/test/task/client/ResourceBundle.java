package com.example.test.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;

public interface ResourceBundle extends ClientBundle {
    public static final ResourceBundle INSTANCE = GWT.create(ResourceBundle.class);
    
    /*
	@Source("ApplicationStyle.css")
	public Style css();
	*/
    
    @Source({ CellTable.Style.DEFAULT_CSS,
	"com/example/test/task/client/SubstitutionManagementTable.css" })
    CellTable.Style cellTableStyle();
	
	@Source("create.gif")
	public ImageResource createIcon();
	
	@Source("wind_icon.gif")
	public ImageResource substitutionWindowIcon();
	
	@Source("close_btn.gif")
	public ImageResource closeButton();
	
	@Source("table_heading_bg.gif")
	DataResource tableHeaderBackground();
	
	@Source("cell_bg.gif")
	DataResource firstColumnBackground();
	
}
