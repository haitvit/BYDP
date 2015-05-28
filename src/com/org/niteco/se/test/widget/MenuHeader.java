package com.org.niteco.se.test.widget;



import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

public class MenuHeader extends ResizeComposite{

	private static UiBinder<Widget, MenuHeader> binder = GWT
			.create(MenuHeaderUiBinder.class);

	interface MenuHeaderUiBinder extends UiBinder<Widget, MenuHeader> {
		
	}
	
	@UiField(provided = true)
	Image image;
	
	@UiField(provided = true)
	HTML html;
	
	@UiConstructor
	public MenuHeader(String text,ImageResource imageResource) {
		image = new Image(imageResource);
		html = new HTML(text);
		initWidget(binder.createAndBindUi(this));
	}

	
}
