package com.springbatch.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExcelLog 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int policyNo;
	
	private String operation;

	private LocalDateTime timestamp;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
	}

	public String getOperation() {
		return operation;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public ExcelLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExcelLog(int policyNo, String operation, LocalDateTime timestamp) {
		super();
		this.policyNo = policyNo;
		this.operation = operation;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ExcelLog [id=" + id + ", policyNo=" + policyNo + ", operation=" + operation + ", timestamp=" + timestamp
				+ "]";
	}

	
	
}
