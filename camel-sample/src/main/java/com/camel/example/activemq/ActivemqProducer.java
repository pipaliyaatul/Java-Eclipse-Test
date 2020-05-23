package com.camel.example.activemq;

import java.util.Date;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ActivemqProducer 
{
	public static void main(String args[]) throws Exception
	{

		CamelContext camelContext=new DefaultCamelContext();

		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory();


		camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		camelContext.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception 
			{
				from("direct:start").to("jms:queue:MyQueue");		
			
			}
		});

		camelContext.start();

		ProducerTemplate producerTemplate=camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:start", new Date());

	}
}
