package com.org.niteco.se.test.client.content;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.HasWidgetsLogHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LogTab extends ResizeComposite {
	
	private static final Logger log = Logger.getLogger("");

	private static UiBinder<Widget, LogTab> uiBinder = GWT.create(LogTabUiBinder.class);

	interface LogTabUiBinder extends UiBinder<Widget, LogTab> {
	}
	
	
	@UiField
	VerticalPanel logArea;
	
	public LogTab() {
		initWidget(uiBinder.createAndBindUi(this));
		log.addHandler(new HasWidgetsLogHandler(logArea));
	}


}
