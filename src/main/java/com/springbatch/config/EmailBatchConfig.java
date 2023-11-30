package com.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.springbatch.controller.FileUploadController;
import com.springbatch.model.InsuranceDTO;

@Configuration
@EnableBatchProcessing
public class EmailBatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private FileUploadController fileUploadController;

	@Value("${upload.dir}")
	private String uploadDir;

	private int offset = 0;
	private int pageSize = 1;

	@Bean
	@StepScope
	PoiItemReader<InsuranceDTO> EmailJobReader(@Value("#{jobParameters['EmailJobOffset']}") Long jobOffset) {
		int linesToSkip;
		System.out.println("Email Offset: " + jobOffset);
		if (jobOffset == 0) {
			offset = jobOffset.intValue();
			linesToSkip = 1;
		} else {
			offset = 1;
			linesToSkip = offset + offset * pageSize;
			pageSize = pageSize + 1;
		}
		PoiItemReader<InsuranceDTO> reader = new PoiItemReader<>();
		reader.setLinesToSkip(linesToSkip);
		String uploadedFileName = fileUploadController.getUploadedFileName();
		String uploadedFilePath = uploadDir + "/" + uploadedFileName;
		reader.setResource(new FileSystemResource(uploadedFilePath));
		System.out.println("Email Path: "+uploadedFilePath);
		System.out.println("Email LinesToSkip: " + linesToSkip);
		reader.setRowMapper(emailRowMapper());
		reader.setMaxItemCount(1);
		return reader;
	}

	private RowMapper<InsuranceDTO> emailRowMapper() {
		BeanWrapperRowMapper<InsuranceDTO> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(InsuranceDTO.class);
		return rowMapper;
	}

	@Bean
	ItemProcessor<InsuranceDTO, InsuranceDTO> customEmailProcessor() {
		return new CustomEmailProcessor();
	}

	@Bean
	ItemWriter<InsuranceDTO> customEmailWriter() {
		return new CustomEmailWriter();
	}

	@Bean
	public Step myStep2() {
		return stepBuilderFactory.get("step2").<InsuranceDTO, InsuranceDTO>chunk(100).reader(EmailJobReader(null))
				.processor(customEmailProcessor()).writer(customEmailWriter()).build();
	}

	@Bean
	Job myjob2() {
		return jobBuilderFactory.get("emailJob").flow(myStep2()).end().build();
	}
}
