package com.bezkoder.spring.datajpa.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Irregular {
	
	private String tableYM;

	private String id;

	private LocalDate date;

	private String color;

	private String comment;
}
