package com.springbatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbatch.model.Health;
import java.util.List;


public interface HealthRepo extends JpaRepository<Health, Integer>
{
	Health findByPolicy(int policy);
}
