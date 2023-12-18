package com.bezkoder.spring.datajpa.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

/**
 * 出勤確認テーブルの主キー用
 */
@Data
public class TimecardKey implements Serializable {
	private String id;	
	private LocalDate date;

}
