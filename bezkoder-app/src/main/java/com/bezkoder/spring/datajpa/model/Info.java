package com.bezkoder.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "info")
public class Info {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "area")
	private String area;

	@Column(name = "type")
	private String type;
	
	@Column(name = "client")
	private String client;
	
	@Column(name = "store")
	private String store;
	
	@Column(name = "career")
	private String career;
	
	@Column(name = "tantou")
	private String tantou;

}
