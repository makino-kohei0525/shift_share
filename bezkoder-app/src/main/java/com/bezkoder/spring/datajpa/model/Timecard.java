package com.bezkoder.spring.datajpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Timecard {
	
	private String tableYM;

	private String id;
	
	private LocalDate date;
	
	private LocalDateTime time;
}
