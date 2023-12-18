package com.bezkoder.spring.datajpa.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	private JdbcTemplate jdbc;
	
	/**
	 * シフト表示画面に表示するDB情報取得
	 */
	public List<Map<String, Object>> employee(String month){
		String sql = "SELECT user.id, user.name, info.area, info.type, info.client, info.store, info.career, info.tantou "
				+ "FROM user "
				+ "LEFT OUTER JOIN info ON user.id = info.id "
				+ "WHERE user.authority = 'USER'";
		
		List<Map<String, Object>> employeeList  = jdbc.queryForList(sql);
		
		for(Map<String, Object> employee : employeeList) {
			
			String shiftSql = "SELECT * "
					+ "FROM shift" + month + " "
					+ "WHERE id = " + employee.get("id");
			
			List<Map<String, Object>> shiftList = jdbc.queryForList(shiftSql);
			
			for(Map<String, Object> shift : shiftList) {
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
				employee.put(dateFormat.format(shift.get("date")), shift.get("time"));
			}
		}
		return employeeList;
	}
	
	// シフト表示画面のラベル
	public List<String> label(String month) {
		
		List<String> label = new ArrayList<>();
		
		for (String key : employee(month).get(0).keySet()) {
			label.add(key);
		}
		label.subList(0,8).clear();
		label.addAll(0,Arrays.asList("ID", "名前", "地域", "業種", "クライアント", "店舗", "キャリア", "担当"));
		
		return label;
	}
	
	/**
	 * 社員情報一覧画面に表示する情報
	 */
	public List<Map<String, Object>> employeeList(){
		
		String sql = "SELECT user.id, user.name, info.area, info.type, info.client, info.store, info.career, info.tantou "
				+ "FROM user "
				+ "LEFT OUTER JOIN info ON user.id = info.id "
				+ "WHERE user.authority = 'USER'";
		
		List<Map<String, Object>> employeeList  = jdbc.queryForList(sql);
		
		return employeeList;
	}
	// 社員情報一覧画面のラベル
	public List<String> labelList(){

		List<String> label = new ArrayList<>();
		label.addAll(Arrays.asList("ID", "名前", "地域", "業種", "クライアント", "店舗", "キャリア", "担当"));
		return label;
	}
}
