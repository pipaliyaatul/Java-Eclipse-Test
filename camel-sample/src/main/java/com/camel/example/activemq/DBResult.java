package com.camel.example.activemq;

import java.util.List;

public class DBResult 
{
	public void result(List list)
	{
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println(list.get(i));
			
		}
		
	}

}
