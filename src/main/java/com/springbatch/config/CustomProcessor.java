package com.springbatch.config;

import org.springframework.batch.item.ItemProcessor;
import com.springbatch.model.Insurance;

public class CustomProcessor implements ItemProcessor<Insurance, Insurance>
{

	@Override
	public Insurance process(Insurance item) throws Exception 
	{
		 return item;
	}

	

}
