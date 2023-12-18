package com.bezkoder.spring.datajpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Info;

public interface InfoRepository extends JpaRepository <Info, String>{

	Optional<Info> findById(String id);
}
