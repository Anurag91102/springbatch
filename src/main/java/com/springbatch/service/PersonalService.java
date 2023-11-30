package com.springbatch.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		
//		Personal personal = new Personal();
//    	personal.setPolicy(item.getPolicy());
//    	personal.setCategory(item.getCategory());
//    	personal.setExpiry(item.getExpiry());
//    	personal.setLocation(item.getLocation());
//    	personal.setState(item.getState());
//    	personal.setRegion(item.getRegion());
//    	personal.setInsuredValue(item.getInsuredValue());
//    	personal.setCategory(item.getCategory());
//    	personal.setName(item.getName());
//    	personal.setPos(item.getPos());
//  		personal.setHt(item.getHt());
//  		personal.setWt(item.getWt());
//  		personal.setAge(item.getAge());
//  		personal.setExp(item.getExp());
//    	personal.setCollege(item.getCollege());
//    	personal.setFirstName(item.getFirstName());
//    	personal.setLastName(item.getLastName());
//    	personal.setEmail(item.getEmail());
//    	personal.setPhone(item.getPhone());	
//  	personalRepo.save(personal);
    
    }
}
