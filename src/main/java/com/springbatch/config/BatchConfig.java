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
import org.springframework.core.io.ClassPathResource;
import com.springbatch.model.Insurance;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	private int offset = 0;
	private int pageSize = 20;

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	@StepScope
	PoiItemReader<Insurance> excelJobReader(@Value("#{jobParameters['offset']}") Long jobOffset) {
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
		PoiItemReader<Insurance> reader = new PoiItemReader<>();
		reader.setLinesToSkip(linesToSkip);
		reader.setResource(new ClassPathResource("InsuranceData.xlsx"));
		System.out.println("Excel LinesToSkip: " + linesToSkip);
		reader.setRowMapper(excelRowMapper());
		reader.setMaxItemCount(20);
		return reader;
	}

	private RowMapper<Insurance> excelRowMapper() {
		BeanWrapperRowMapper<Insurance> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(Insurance.class);
		return rowMapper;
	}

	@Bean
	ItemProcessor<Insurance, Insurance> customItemProcessor() {
		return new CustomProcessor();
	}

	@Bean
	ItemWriter<Insurance> customWriter() {
		return new CustomWriter();
	}

	@Bean
	public Step myStep() {
		return stepBuilderFactory.get("step1").<Insurance, Insurance>chunk(20).reader(excelJobReader(null))
				.processor(customItemProcessor()).writer(customWriter()).build();
	}

	@Bean
	Job myjob() {
		return jobBuilderFactory.get("processJob").flow(myStep()).end().build();
	}
}