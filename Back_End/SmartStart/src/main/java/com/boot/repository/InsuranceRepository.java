package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.model.Home;
import com.boot.model.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Long>{
	
	Insurance findById(long idInsurance);
	List<Insurance> findByUserId(long user_id);
	Insurance findByHome(Home home);
}
