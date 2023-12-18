package com.bezkoder.spring.datajpa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.datajpa.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String>{
	
	Optional<Users> findById(String id);
}
