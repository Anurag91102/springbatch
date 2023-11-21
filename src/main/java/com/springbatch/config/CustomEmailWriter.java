package com.springbatch.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.springbatch.model.Insurance;
import com.springbatch.service.EmailService;
import com.springbatch.service.NotificationLogService;

public class CustomEmailWriter implements ItemWriter<Insurance>
{
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private NotificationLogService notificationLogService;

	@Override
	public void write(List<? extends Insurance> items) throws Exception 
	{
		for(Insurance item : items)
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
				System.out.println(e);
				notificationLogService.saveEmailLog(email, subject, body, "fail");
			}
		}
	}
}
