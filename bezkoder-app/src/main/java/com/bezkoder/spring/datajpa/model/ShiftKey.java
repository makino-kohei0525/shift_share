package com.bezkoder.spring.datajpa.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;


/**
 * シフトテーブルの主キー用
 */
@Data
public class ShiftKey implements Serializable {
	
	private int id;	
	private LocalDate date;

}
