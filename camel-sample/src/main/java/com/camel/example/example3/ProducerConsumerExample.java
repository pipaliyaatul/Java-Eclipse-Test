package com.camel.example.example3;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerConsumerExample 
{
	public static void main(String args[]) throws Exception
	{
		/*Producer and consumer ideas*/
		
		CamelContext camelContext=new DefaultCamelContext();
		
		/*TO set activemq need to create connectionfactory*/
		
		
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory();
		
		//camelContext.addComponent("JMS", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		//camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		camelContext.addRoutes(new  RouteBuilder() 
		{			
			@Override
			public void configure() throws Exception 
			{
				
				from("direct:start")
				.process(new Processor() 
				{					
					public void process(Exchange exchange) throws Exception 
					{
						System.out.println("I am from processor!!");	
						
						String basemessage=exchange.getIn().getBody(String.class);
						
						basemessage=basemessage+" - This is passed through process exchange";
						
						exchange.getMessage().setBody(basemessage);
						
					}
				})
				.to("seda:end");
		
				
			}
		});
		
		camelContext.start();
		
		ProducerTemplate producerTemplate=camelContext.createProducerTemplate();
		
		producerTemplate.sendBody("direct:start", "Hello From the Producer");
		
		
		
		ConsumerTemplate consumerTemplate=camelContext.createConsumerTemplate();
		String message=consumerTemplate.receiveBody("seda:end", String.class);
		
		
		System.out.println(message);
	}

}
