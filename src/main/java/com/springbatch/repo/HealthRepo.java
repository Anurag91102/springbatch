package com.springbatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbatch.model.Health;

public interface HealthRepo extends JpaRepository<Health, Integer>
{


}
