package com.bezkoder.spring.datajpa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.datajpa.model.Irregular;

@Transactional
@Repository
public class IrregularRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private String sql;
	
	/**
	 * 一件のinsert文を実行
	 * @param irregular
	 * @return 影響があったカラム数(1以外はエラー)
	 */
	
	public int insert(Irregular irregular) {
		sql = "INSERT INTO irregular" + irregular.getTableYM() + " (id, date, color, comment)"
				+ " VALUES('" + irregular.getId() 		+ "', '" 
				  			  + irregular.getDate() 	+ "', '" 
				  			  + irregular.getColor()	+ "', '" 
							  + irregular.getComment() 	+ "')";
		int res = jdbc.update(sql);
		
		return res;
		
	}
	/**
	 * 一件のupdate文を実行
	 * @param irregular
	 * @return 影響があったカラム数(1以外はエラー)
	 */
	
	public int update(Irregular irregular) {
		sql = "UPDATE irregular" + irregular.getTableYM() 
			+ " SET " 	+ "color = '" 	  + irregular.getColor()	
			  			+ "', comment ='" + irregular.getComment()
			+ "WHERE id = '" + irregular.getId()
			+ "AND date ='"	 + irregular.getDate();
		int res = jdbc.update(sql);
		
		return res;
		
	}	
	
	
	/**
	 * 一件のdelete文を実行
	 * @param irregular
	 * @return 影響があったカラム数(1以外はエラー)
	 */
	
	public int delete(Irregular irregular) {
		sql = "DELETE FROM irregular" + irregular.getTableYM() 
			+ "WHERE id = '" + irregular.getId()
			+ "AND date = '" + irregular.getDate();
		int res = jdbc.update(sql);
		
		return res;
		
	}
	
	
	/**
	 * select文を実行
	 * @param irregular
	 * @return 結果List
	 */
	public List<Map<String,Object>> findByIdAndDate(String tableYM ,String id, LocalDate date) {
		sql = "SELECT id FROM irregular" + tableYM + " WHERE data = " + date + " and id = '" + id +"'";
		List<Map<String,Object>> res = jdbc.queryForList(sql);
		return res;
	}
	
	@SuppressWarnings("finally")
	public boolean existByIdAndDate(Irregular irregular) {
		String tableYM = irregular.getTableYM();
		LocalDate date = irregular.getDate();
		String id = irregular.getId();
		
		sql = "SELECT id FROM irregular" + tableYM + " WHERE data = " + date + " and id = '" + id +"'";
		try {
			List<Map<String,Object>> res = jdbc.queryForList(sql);
			return true;
		} finally {
			return false;
		}
	}
	
}
