package com.org.niteco.se.test.client.event;

import com.google.web.bindery.event.shared.Event;
import com.org.niteco.se.test.widget.MenuHeader;

public class MenuEvent extends Event<MenuHeader>{

	private static final Type<MenuHeader> TYPE = new Type<MenuHeader>();
	private String menu;

	public MenuEvent(String menu) {
		this.menu = menu;
	}
	
	
	public String getMenu() {
		return menu;
	}

	

	@Override
	public com.google.web.bindery.event.shared.Event.Type<MenuHeader> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MenuHeader handler) {
		
	}

}
