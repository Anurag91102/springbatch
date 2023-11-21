package com.springbatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbatch.model.ExcelLog;


public interface ExcelLogRepo extends JpaRepository<ExcelLog, Integer>
{
	ExcelLog findByPolicyNo(int policyNo);
}
