package com.springbatch.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbatch.model.Health;
import com.springbatch.model.InsuranceDTO;
import com.springbatch.model.Personal;
import com.springbatch.repo.PersonalRepo;

@Service
public class PersonalService 
{
	@Autowired
	private PersonalRepo personalRepo;
	
    @Autowired
    private ModelMapper modelMapper;
	
	@Transactional
    public void saveData(InsuranceDTO item) 
    {
		
		Personal personal = modelMapper.map(item, Personal.class);
		personalRepo.save(personal);
    
    }
	
	public Boolean findById(InsuranceDTO item)
	{
		Optional<Personal> personal= personalRepo.findById(item.getPolicy());
		if(personal.isPresent())
		{
			System.out.println("Personal Present");
			return true;
		}
		else
		{
			System.out.println("Personal Absent");
			return false;
		}
	}
}
