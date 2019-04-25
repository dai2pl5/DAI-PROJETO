package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boot.model.Package;

public interface PackageRepository extends JpaRepository<Package, Long> {
	 
	Package findById(long idPackage);
	
}
