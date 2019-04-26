package com.boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.boot.model.Home;

public interface HomeRepository extends JpaRepository<Home, Long> {
	
		Home findById(long id);
}
