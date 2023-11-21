package com.springbatch.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbatch.model.ExcelLog;
import com.springbatch.repo.ExcelLogRepo;

@Service
public class ExcelLogService 
{

	@Autowired
	private ExcelLogRepo excelLogRepo;

	public void saveLog(int policyNo, String operation) {
		ExcelLog existedLog = excelLogRepo.findByPolicyNo(policyNo);
		if (excelLogRepo != null) {
			existedLog.setOperation(operation);
			existedLog.setTimestamp(LocalDateTime.now());
			excelLogRepo.save(existedLog);
		} else {
			ExcelLog excelLog = new ExcelLog();
			excelLog.setPolicyNo(policyNo);
			excelLog.setOperation(operation);
			excelLog.setTimestamp(LocalDateTime.now());
			excelLogRepo.save(excelLog);
		}

	}
	
}
