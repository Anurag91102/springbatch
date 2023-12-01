package com.springbatch.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.springbatch.model.Health;
import com.springbatch.model.InsuranceDTO;
import com.springbatch.model.Personal;
import com.springbatch.repo.HealthRepo;
import com.springbatch.repo.PersonalRepo;
import com.springbatch.service.HealthService;
import com.springbatch.service.PersonalService;

public class CustomProcessor implements ItemProcessor<InsuranceDTO, InsuranceDTO>
{
	@Autowired
	private HealthService healthService;
	
	@Autowired
	private PersonalService personalService;
	
	@Autowired
	private HealthRepo healthRepo;
	
	@Autowired
	private PersonalRepo personalRepo;
	
	@Override
	public InsuranceDTO process(InsuranceDTO item) throws Exception 
	{
		int id = item.getPolicy();
		System.out.println(id);
		if(healthService.findById(item) || personalService.findById(item))
		{
			Boolean healthExistence;
			Boolean personalExistence;
			int itemId = item.getPolicy();
			Health existedHealth = healthRepo.findByPolicy(itemId);
			if(existedHealth != null)
			{
				healthExistence = existedHealth.getUpdatedAt().equalsIgnoreCase(item.getUpdatedAt());
			}
			else
			{
				healthExistence = false;
			}
			System.out.println("Entered");
			Personal existedPersonal = personalRepo.findByPolicy(itemId);
			if(existedPersonal!=null)
			{
				personalExistence = existedPersonal.getUpdatedAt().equalsIgnoreCase(item.getUpdatedAt());
			}
			else
			{
				personalExistence=false;
			}
			System.out.println(healthExistence);
			System.out.println(personalExistence);
			if(healthExistence || personalExistence)
			{
				return null;
			}
			else
			{
				return item;
			}
		}
		else
		{
			return item;
		}
	}
}
