package com.bezkoder.spring.datajpa.repository;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.datajpa.model.Timecard;

@Transactional
@Repository
public class TimecardRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private String sql;
	
	/**
	 * 一件のinsert文を実行
	 * @param timecard
	 * @return 影響があったカラム数(1以外はエラー)
	 */
	
	public int insert(Timecard timecard) {
		sql = "INSERT INTO timecard" + timecard.getTableYM() + " (id, date, time)"
				+ " VALUES('" + timecard.getId() + "', '" 
							  + timecard.getDate() + "', '" 
							  + timecard.getTime() + "')";
		int res = jdbc.update(sql);
		
		return res;
		
	}
	
	@SuppressWarnings("finally")
	public boolean findByIdAndDate(String tableYM ,String id, LocalDate date) {
		sql = "SELECT id FROM timecard" + tableYM + " WHERE data = " + date + " and id = '" + id +"'";
		try {
			Map<String,Object> res = jdbc.queryForMap(sql);
			return true;
		} finally {
			return false;
		}
	}
	
}
