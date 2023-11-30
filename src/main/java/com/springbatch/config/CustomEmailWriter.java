package com.springbatch.config;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.springbatch.model.InsuranceDTO;
import com.springbatch.service.EmailService;
import com.springbatch.service.NotificationLogService;

public class CustomEmailWriter implements ItemWriter<InsuranceDTO>
{
	Logger logger = LoggerFactory.getLogger(CustomEmailWriter.class);
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private NotificationLogService notificationLogService;

	@Override
	public void write(List<? extends InsuranceDTO> items) throws Exception 
	{
		for(InsuranceDTO item : items)
		{
			String email = item.getEmail();
			String body = "Hii Testing Started";
			String subject = "Cron Job for Email";
			try
			{
				emailService.sendEmail(email, body,subject);
				notificationLogService.saveEmailLog(email, subject, body, "success");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				notificationLogService.saveEmailLog(email, subject, body, "fail");
			}
		}
	}
}
