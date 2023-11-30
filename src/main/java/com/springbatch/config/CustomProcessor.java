package com.springbatch.config;

import org.springframework.batch.item.ItemProcessor;
import com.springbatch.model.InsuranceDTO;

public class CustomProcessor implements ItemProcessor<InsuranceDTO, InsuranceDTO>
{
	@Override
	public InsuranceDTO process(InsuranceDTO item) throws Exception 
	{
		 return item;
	}
}
