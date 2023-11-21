package com.springbatch.config;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.springbatch.model.Insurance;
import com.springbatch.service.ExcelLogService;
import com.springbatch.service.HealthService;
import com.springbatch.service.PersonalService;

public class CustomWriter implements ItemWriter<Insurance>
{
	@Autowired
	private PersonalService personalService;
	
	@Autowired
	private HealthService healthService;
	
	@Autowired
	private ExcelLogService excelLogService;
	
	@Override
	public void write(List<? extends Insurance> items) throws Exception 
	{
		int count = 0;
		for(Insurance item:items)
		{
			count++;
			try {
				if("health".equalsIgnoreCase(item.getCategory()))
			    {
			    	 healthService.saveData(item); 
		        }
			    else
			    {
		            personalService.saveData(item);
		        }
				excelLogService.saveLog(item.getPolicy(), "success");
			}
		    catch (Exception e) 
			{
				System.out.println(e);
				excelLogService.saveLog(item.getPolicy(), "fail");
			}
		     
		}
		System.out.println("Number of records fetch :"+count);
	}
	

}
