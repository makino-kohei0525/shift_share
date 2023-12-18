package com.bezkoder.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class Users {
	
	private static final String ADMIN  = "ADMIN";
	private static final String USER  = "USER";
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;
	
	@Column(name = "authority")
	private String authority;
	
	public Users(){
		
	}
}
