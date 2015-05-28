package com.org.niteco.se.test.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class ContentPanel extends ResizeComposite implements ClickHandler {

	private static UiBinder<Widget,ContentPanel> binder = GWT
			.create(ContentPanelUiBinder.class);

	interface ContentPanelUiBinder extends UiBinder<Widget, ContentPanel> {
		
	}

	public ContentPanel() {
		initWidget(binder.createAndBindUi(this));
	}
	
	@UiField
	TabLayoutPanel tab;

	public void addTab(String text,Composite content) {
		tab.add(content,text);
		tab.selectTab(tab.getWidgetCount() -1);
	}

	@Override
	public void onClick(ClickEvent event) {
		String text = ((Anchor) event.getSource()).getText();
		addTab(text, new DateBox());
	}

	
}
