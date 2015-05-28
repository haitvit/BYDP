package com.org.niteco.se.test.widget;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

public class MenuBar extends ResizeComposite {

	private static UiBinder<Widget, MenuBar> binder = GWT
			.create(MenuBarUiBinder.class);

	interface MenuBarUiBinder extends UiBinder<Widget, MenuBar> {
	}

	public MenuBar() {
		initWidget(binder.createAndBindUi(this));
	}

	public void addClickHandler(ClickHandler clickHandler) {
		Widget widget = getWidget();
		Iterator<Widget> it = ((HasWidgets) widget).iterator();
		while (it.hasNext()) {
			Widget w = it.next();
			if (w instanceof LayoutPanel) {
				Iterator<Widget> it1 = ((HasWidgets) w).iterator();
				while (it1.hasNext()) {
					Widget menuItem = it1.next();
					if (menuItem instanceof MenuItem) {
						((MenuItem) menuItem).anchor.addClickHandler(clickHandler);
					}
				}
			}
		}
	}

}
