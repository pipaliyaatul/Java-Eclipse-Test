package com.camel.example.routes;

import org.apache.camel.builder.RouteBuilder;

public class HelloWorldRoute extends RouteBuilder
{

	@Override
	public void configure() throws Exception
	{
	
		System.out.println("Copying file started...");
		filecopy();
		System.out.println("Finished");
		
	}
	
	/*THis is example for file copy */
	public void filecopy()
	{
		System.out.println("In Progress");
		from("file:input_box?noop=true").to("file:output_box");
	}
}
