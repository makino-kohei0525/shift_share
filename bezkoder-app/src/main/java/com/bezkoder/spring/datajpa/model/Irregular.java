package com.bezkoder.spring.datajpa.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Irregular {
	
	private String tableYM;

	private String id;

	private LocalDate date;
	
	private String irrVal;

	private String color;

	private String comment;
	
	final String CONST_YUUKYUU = "paid";
	
	final String CONST_KEKKIN = "absence";
	
}
