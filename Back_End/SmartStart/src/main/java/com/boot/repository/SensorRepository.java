package com.boot.repository;
import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.boot.model.Sensor;

public interface SensorRepository extends MongoRepository<Sensor, String> {
	
	public List<Sensor> findByNameAndValue(String name, int value);
	
	
	public List<Sensor> findByNameLikeOrderByISODATADesc(String name);
	public List<Sensor> findByIdInsuranceAndNameOrderByISODATADesc(int idInsurance,String name);
	public List<Sensor> findByIdInsuranceLikeOrderByISODATADesc(int idInsurance);
	public List<Sensor> findByIdInsuranceAndName(int idInsurance, String name);
	public List<Sensor> findByValueAndName(int value,String name);
	public List<Sensor> findAllByOrderByISODATADesc();
	
	
}