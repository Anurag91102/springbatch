package com.springbatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springbatch.model.NotificationLog;
import com.springbatch.repo.NotificationLogRepo;

@Service
public class NotificationLogService {
	@Autowired
	private NotificationLogRepo notificationLogRepo;

	public void saveEmailLog(String toEmail, String subject, String bodyContent, String operation) {
		NotificationLog existedLog = notificationLogRepo.findByToEmail(toEmail);

		if (existedLog != null) {
			existedLog.setBodyContent(bodyContent);
			existedLog.setSubject(subject);
			existedLog.setOperationResult(operation);
			notificationLogRepo.save(existedLog);
		} else {
			NotificationLog notificationLog = new NotificationLog();
			notificationLog.setToEmail(toEmail);
			notificationLog.setFromEmail("anurag091102@gmail.com");
			notificationLog.setSubject(subject);
			notificationLog.setBodyContent(bodyContent);
			notificationLog.setOperationResult(operation);
			notificationLogRepo.save(notificationLog);

		}
	}
}
