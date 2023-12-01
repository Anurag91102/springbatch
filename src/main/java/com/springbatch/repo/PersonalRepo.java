package com.springbatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbatch.model.Personal;
import java.util.List;


public interface PersonalRepo extends JpaRepository<Personal, Integer>
{
	Personal findByPolicy(int policy);
}
