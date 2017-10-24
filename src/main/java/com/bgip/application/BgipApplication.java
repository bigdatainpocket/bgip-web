package com.bgip.application;

import org.glassfish.jersey.server.ResourceConfig;

public class BgipApplication extends ResourceConfig{

	  public BgipApplication() {
	        
      packages("com.bgip.controller", "com.bgip.filter");
      
	        
	  }
	
}
