package com.org.niteco.se.test.client;

import com.google.gwt.core.client.GWT;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface FinsResources extends ClientBundle {

	public static final FinsResources INSTANCE = GWT.create(FinsResources.class);
	
	@Source("com/org/niteco/se/test/client/images/albumImages.ico")
	ImageResource albumImages();

	

}
