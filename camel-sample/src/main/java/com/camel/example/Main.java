package com.camel.example;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.camel.example.routes.HelloWorldRoute;

public class Main {

	public static void main(String[] args) throws Exception 
	{		
		CamelContext camelContext=new DefaultCamelContext();
		
		camelContext.addRoutes(new HelloWorldRoute());
		
		//camelContext.addRoutes(rout);
		
		while(true)
		{
			camelContext.start();

		}
			
	}

}
