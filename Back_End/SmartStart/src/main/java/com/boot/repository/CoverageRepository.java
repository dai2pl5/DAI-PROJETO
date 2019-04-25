package com.boot.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.boot.model.Coverage;
public interface CoverageRepository extends JpaRepository<Coverage, Long>{
	
	Coverage findByName(String name);
	Coverage findById(long id);
}
