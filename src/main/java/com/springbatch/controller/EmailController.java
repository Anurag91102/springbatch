package com.springbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController 
{
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job myjob2;
	
	private boolean firstExecution = true;
	
    
	@Scheduled(cron = "0 */2 * * * *")
	public void handleBatch() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException
	{
		long currentTime = System.currentTimeMillis();
		long offsetValue;
        if (firstExecution)
        {
            offsetValue = 0L;
        } else 
        {
            offsetValue = 1L;
        }

	    firstExecution = false;
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("EmailJobTime", currentTime)
				.addLong("EmailJobOffset", offsetValue)
				.toJobParameters();
		jobLauncher.run(myjob2, jobParameters);
		System.out.println("Email Job Completed.");
	}
}
