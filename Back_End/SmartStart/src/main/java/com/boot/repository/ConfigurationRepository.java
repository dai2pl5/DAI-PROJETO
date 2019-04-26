package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.model.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
	
	Configuration findByDesignation(String designation);
}
