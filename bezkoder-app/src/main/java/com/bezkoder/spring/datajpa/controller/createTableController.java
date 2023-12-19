package com.bezkoder.spring.datajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * SQLクエリを実行するクラス
 */


@RestController
public class createTableController {

	@Autowired
	private JdbcTemplate jdbc;
	
	@GetMapping("/create/{month}")
	public String createTable(@PathVariable("month")String month) {
		
		String createShift = "CREATE TABLE shift"; 
		String columnsShift = " (id VARCHAR(5) NOT NULL, date DATE NOT NULL, time VARCHAR(20), PRIMARY KEY(id, date), FOREIGN KEY(id) REFERENCES user(id))";
		String sqlShift = createShift + month + columnsShift;
		

		String createTimecard = "CREATE TABLE timecard"; 
		String columnsTimecard = " ( "
				+ "	date DATE NOT NULL, "
				+ "	id VARCHAR(5) NOT NULL, "
				+ "	time DATETIME NULL DEFAULT NULL, "
				+ "	PRIMARY KEY (date, id) USING BTREE "
				+ ") "
				+ "COLLATE='utf8mb4_unicode_ci' "
				+ "ENGINE=InnoDB";
		String sqlTimecard = createTimecard + month + columnsTimecard;

		
		String createIrregular = "CREATE TABLE irregular"; 
		String columnsIrregular = " ( "
				+ "	date DATE NOT NULL, "
				+ "	id VARCHAR(5) NOT NULL, "
				+ "	irrVal VARCHAR(10) NOT NULL, "
				+ "	color VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci', "
				+ "	comment VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci', "
				+ "	PRIMARY KEY (date, id) USING BTREE "
				+ ") "
				+ "COLLATE='utf8mb4_unicode_ci' "
				+ "ENGINE=InnoDB;";
		String sqlIrregular = createIrregular + month + columnsIrregular;

		jdbc.execute(sqlShift);
		jdbc.execute(sqlTimecard);
		jdbc.execute(sqlIrregular);

		
		
		
		return month + "のテーブルを作成しました。";
	}
	
	
}
