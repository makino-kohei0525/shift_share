package com.bezkoder.spring.datajpa.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Shift {
	
	private String tableYM;
	
	private String id;
	
	private LocalDate date;
	
	private String time;

}
