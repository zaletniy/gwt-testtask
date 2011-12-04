package com.example.test.task.client.view;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class WindowContainer extends Composite implements HasWidgets{

	private static WindowContainerUiBinder uiBinder = GWT
			.create(WindowContainerUiBinder.class);
	@UiField
	PushButton closeBtn;
	
	@UiField
	Label titleLbl;

	@UiField
	HorizontalPanel controlPanel;
	
	@UiField
	SimplePanel contentPanel;
	
	@UiField
	SimplePanel windowIconPanel;

	interface WindowContainerUiBinder extends UiBinder<Widget, WindowContainer> {
	}

	public WindowContainer() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void add(Widget w) {
		contentPanel.add(w);
	}

	public void clear() {
		contentPanel.clear();
	}

	public Iterator<Widget> iterator() {
		return contentPanel.iterator();
	}

	public boolean remove(Widget w) {
		return contentPanel.remove(w);
	}
	
	@UiChild(limit = 1, tagname = "controllPanel")
	public void addControl(Widget widget){
		controlPanel.add(widget);
	}
	
	@UiChild(limit = 1, tagname = "contentPanel")
	public void setContentPanel(Widget widget){
		contentPanel.add(widget);
	}
	
	@UiChild(limit = 1, tagname = "windowImage")
	public void setWindowImage(Image image){
		windowIconPanel.add(image);
	}
	
	public void setTitle(String title){
		titleLbl.setText(title);
	}
}
