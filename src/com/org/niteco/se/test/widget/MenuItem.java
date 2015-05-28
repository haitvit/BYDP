package com.org.niteco.se.test.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.org.niteco.se.test.client.FinsResources;

public class MenuItem extends Composite{
	
	FinsResources images = FinsResources.INSTANCE;

	private static UiBinder<Widget, MenuItem> binder = GWT
			.create(MenuItemUiBinder.class);

	interface MenuItemUiBinder extends UiBinder<Widget, MenuItem> {
	}
	
	
	@UiField
	Anchor anchor;

	
	@UiConstructor
	public MenuItem(String text,ImageResource imageResource,final String contentName) {
		initWidget(binder.createAndBindUi(this));
		anchor.setHTML(getHtml(imageResource,text));
		anchor.setName(contentName);
	}
	
	private SafeHtml getHtml(ImageResource image,String text){
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.append(getHtml(images.albumImages()));
		sb.append(getHtml(image));
		sb.append(getHtml(images.albumImages()));
		sb.appendEscaped(" ").appendEscaped(text);
		
		return sb.toSafeHtml();
		
	}
	
	private SafeHtml getHtml(ImageResource image){
		return SafeHtmlUtils.fromTrustedString(AbstractImagePrototype.create(image).getHTML());
	}

	

}
