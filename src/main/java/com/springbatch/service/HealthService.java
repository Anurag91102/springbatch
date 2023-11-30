package com.springbatch.service;

import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springbatch.model.Health;
import com.springbatch.model.InsuranceDTO;
import com.springbatch.repo.HealthRepo;

@Service
public class HealthService {

	@Autowired
	private HealthRepo healthRepo;
	
    @Autowired
    private ModelMapper modelMapper;
	
	@Transactional
    public void saveData(InsuranceDTO item) 
    {
    	Health health = modelMapper.map(item, Health.class);
  		healthRepo.save(health);
    }
}
