package com.springbatch.service;

import java.util.Optional;

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
	
	
	public Boolean findById(InsuranceDTO item)
	{
		Optional<Health> health= healthRepo.findById(item.getPolicy());
		if(health.isPresent())
		{
			System.out.println("Present");
			return true;
		}
		else
		{
			System.out.println("Absent");
			return false;
		}
	}
}
