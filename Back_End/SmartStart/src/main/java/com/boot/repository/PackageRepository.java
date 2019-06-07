package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boot.model.Package;
import com.boot.model.User;

public interface PackageRepository extends JpaRepository<Package, Long> {
	 
	Package findById(long idPackage);
	List<Package> findByUser(User user);
	
	void delete(Package package1);
}
