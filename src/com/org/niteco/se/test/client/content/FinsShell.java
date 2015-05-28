package com.org.niteco.se.test.client.content;



import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.org.niteco.se.test.widget.ContentPanel;
import com.org.niteco.se.test.widget.MenuBar;

public class FinsShell extends ResizeComposite{

	private static UiBinder<Widget,FinsShell> binder = GWT
			.create(FinsShellUiBinder.class);

	interface FinsShellUiBinder extends UiBinder<Widget, FinsShell> {
	}
	
	@UiField
	Label logoLabel;

	@UiField
	ContentPanel contentPanel;
	
	@UiField
	MenuBar menuBar;

	public FinsShell() {
	   initWidget(binder.createAndBindUi(this));
	   if(!GWT.isProdMode()){
		   contentPanel.addTab("Log", new LogTab());
	   }
	   contentPanel.addTab("Home", new DateBox());
	   menuBar.addClickHandler(contentPanel);
	}
}
