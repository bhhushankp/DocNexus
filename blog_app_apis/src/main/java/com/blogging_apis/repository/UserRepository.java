package com.blogging_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging_apis.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
}
