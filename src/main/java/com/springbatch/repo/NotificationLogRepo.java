package com.springbatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbatch.model.NotificationLog;


public interface NotificationLogRepo extends JpaRepository<NotificationLog, Integer>
{
	NotificationLog findByToEmail(String toEmail);
	
}