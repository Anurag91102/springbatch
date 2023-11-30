package com.springbatch.config;

import org.modelmapper.ModelMapper;
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
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private FileUploadController fileUploadController;

	@Value("${upload.dir}")
	private String uploadDir;

	private int offset = 0;
	private int pageSize = 20;

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	@StepScope
	PoiItemReader<InsuranceDTO> excelJobReader(@Value("#{jobParameters['offset']}") Long jobOffset) {
		int linesToSkip;
		System.out.println("Excel Offset: " + jobOffset);
		if (jobOffset == 0) {
			offset = jobOffset.intValue();
			linesToSkip = 1;
		} else {
			offset = 1;
			linesToSkip = offset + offset * pageSize;
			pageSize = pageSize + 20;
		}
		PoiItemReader<InsuranceDTO> reader = new PoiItemReader<>();
		reader.setLinesToSkip(linesToSkip);
		String uploadedFileName = fileUploadController.getUploadedFileName();
		String uploadedFilePath = uploadDir + "/" + uploadedFileName;
		reader.setResource(new FileSystemResource(uploadedFilePath));
		System.out.println("Excel Path: "+uploadedFilePath);
		System.out.println("Excel LinesToSkip: " + linesToSkip);
		reader.setRowMapper(excelRowMapper());
		reader.setMaxItemCount(20);
		return reader;
	}

	private RowMapper<InsuranceDTO> excelRowMapper() {
		BeanWrapperRowMapper<InsuranceDTO> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(InsuranceDTO.class);
		return rowMapper;
	}

	@Bean
	ItemProcessor<InsuranceDTO, InsuranceDTO> customItemProcessor() {
		return new CustomProcessor();
	}

	@Bean
	ItemWriter<InsuranceDTO> customWriter() {
		return new CustomWriter();
	}

	@Bean
	public Step myStep() {
		return stepBuilderFactory.get("step1").<InsuranceDTO, InsuranceDTO>chunk(20).reader(excelJobReader(null))
				.processor(customItemProcessor()).writer(customWriter()).build();
	}

	@Bean
	Job myjob() {
		return jobBuilderFactory.get("processJob").flow(myStep()).end().build();
	}
	

}