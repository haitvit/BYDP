package com.org.niteco.se.test.client;


import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.org.niteco.se.test.client.content.FinsShell;


public class Test implements EntryPoint {
	private static final Logger log = Logger.getLogger(Test.class.getName());
	
	@Override
	public void onModuleLoad() {
		 RootLayoutPanel rp = RootLayoutPanel.get();
		    FinsShell finsShell = new FinsShell();
		    rp.add(finsShell);
		    log.info("a" + GWT.getModuleBaseURL());
	}
	
	
		
					
}
