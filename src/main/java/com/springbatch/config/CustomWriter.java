package com.springbatch.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.springbatch.model.InsuranceDTO;
import com.springbatch.service.ExcelLogService;
import com.springbatch.service.HealthService;
import com.springbatch.service.PersonalService;

public class CustomWriter implements ItemWriter<InsuranceDTO> {
	
	Logger logger = LoggerFactory.getLogger(CustomWriter.class);
	
	@Autowired
	private PersonalService personalService;

	@Autowired
	private HealthService healthService;

	@Autowired
	private ExcelLogService excelLogService;

	public enum Category{
		PERSONAL,HEALTH;
	}
	
	@Override
	public void write(List<? extends InsuranceDTO> items) throws Exception{
		int count = 0;
		for (InsuranceDTO item : items) 
		{
			System.out.println("Item:"+item);
			count++;
			try {
				if(item!=null)
				{
					Category category =  Category.valueOf(item.getCategory().toUpperCase());
					switch (category) 
					{
						case HEALTH:
							healthService.saveData(item);
							break;
						case PERSONAL:
							personalService.saveData(item);
							break;
						default:
							throw new IllegalArgumentException();
					}
					excelLogService.saveLog(item.getPolicy(), "success");
				}
				else
				{
					  logger.warn("Skipping null item in the write method.");
				}
				
			} catch (Exception e) {
				logger.warn(e.getMessage());
				excelLogService.saveLog(item.getPolicy(), "fail");
			}

		}
		System.out.println("Number of records fetch :" + count);
	}

}
