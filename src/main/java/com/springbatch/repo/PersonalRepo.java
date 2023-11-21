package com.springbatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbatch.model.Personal;

public interface PersonalRepo extends JpaRepository<Personal, Integer>

{

}
