package com.springbatch.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NotificationLog 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String toEmail;
	
    private String fromEmail;
    
    private String subject;
    
    private String bodyContent;
    
    private String operationResult;
    
    private LocalDateTime timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	public String getOperationResult() {
		return operationResult;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}
	
	public NotificationLog(String toEmail, String fromEmail, String subject, String bodyContent, String operationResult,
			LocalDateTime timestamp) {
		super();
		this.toEmail = toEmail;
		this.fromEmail = fromEmail;
		this.subject = subject;
		this.bodyContent = bodyContent;
		this.operationResult = operationResult;
		this.timestamp = timestamp;
	}

	public NotificationLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NotificationLog [id=" + id + ", toEmail=" + toEmail + ", fromEmail=" + fromEmail + ", subject="
				+ subject + ", bodyContent=" + bodyContent + ", operationResult=" + operationResult + ", timestamp="
				+ timestamp + "]";
	}
   
}
