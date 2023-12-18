package com.bezkoder.spring.datajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

	@Autowired
	private JdbcTemplate jdbc;
	
	/**
	 * シフトのDB登録
	 */
//	
//	public String inser(("month")String month) {
//		
//		String sqlInsert = "INSERT INTO shift";
//		String sqlColumns = "(id SERIAL NOT NULL, date DATE NOT NULL, time VARCHAR(20), PRIMARY KEY(id, date))";
//		String sql = sqlInsert + month + sqlColumns;
//		
//		
//		
//		jdbc.update(sql);
//		
//		return null;
//	}
	
}
