package com.boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.boot.model.Home;
import com.boot.model.User;

public interface HomeRepository extends JpaRepository<Home, Long> {
	
		Home findById(long id);
		Home findByUser(User user);
}
