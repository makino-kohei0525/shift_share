package com.bezkoder.spring.datajpa.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

/**
 * イレギュラーテーブルの主キー用
 */
@Data
public class IrregularKey implements Serializable {

	private String id;	
	private LocalDate date;

}
