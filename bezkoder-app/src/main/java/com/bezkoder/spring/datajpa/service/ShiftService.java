package com.bezkoder.spring.datajpa.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bezkoder.spring.datajpa.config.SecuritySession;
import com.bezkoder.spring.datajpa.form.ShiftForm;

@Service
public class ShiftService {
	
	@Autowired
	SecuritySession securitySession;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private String thisMonth;
	private String nextMonth;
	
	/**
	 * 時間を格納するリスト
	 */
	
	public List<String> shiftTime(){
		
		List<String> shift = new ArrayList<String>();
        
			shift.add("休");
		for(int i =7;  i <15; i++) {
			shift.add(i + ":00");
			shift.add(i + ":15");
			shift.add(i + ":30");
			shift.add(i + ":45");
		}
			return shift;

	}
	
	/**
	 * 日付と時間を表すマップ作成
	 * @return
	 */
	
	public Map<LocalDate, List<String>> shift(@ModelAttribute ShiftForm shiftForm) {
		Map<LocalDate, List<String>> shift = new LinkedHashMap<LocalDate, List<String>>();
		
		LocalDate next = LocalDate.now().plusMonths(1); //今の日付プラス1ヶ月
			
			for(int i =1; i<  next.lengthOfMonth() +1; i++) {
				
				shift.put(next.withDayOfMonth(i), shiftTime());
			}
			
			return shift;

	}
		
	/**
	 * 次月の表示形式
	 */
	public String month() {
		
		LocalDate next = LocalDate.now().plusMonths(1);
		this.nextMonth = next.format(DateTimeFormatter.ofPattern("yyyyMM"));
		
		return this.nextMonth;
		
	}
	
	/**
	 * 当月の表示形式
	 */
	public String thisMonth() {
		LocalDate current = LocalDate.now();
		this.thisMonth = current.format(DateTimeFormatter.ofPattern("yyyyMM"));
		return this.thisMonth;
	}
	
	/**
	 * シフト入力情報を格納
	 */
	public Map<String, String> inputShift(@ModelAttribute ShiftForm shiftForm){
		
		String[] dateStr = shiftForm.getDate().split(",");
		String[] timeStr = shiftForm.getTime().split(",");
		
		Map<String, String> inputShift = new LinkedHashMap<String, String>();
		
		for(int i =0; i< dateStr.length; i++) {
			inputShift.put(dateStr[i], timeStr[i]);
		}
		return inputShift;
	}
	
	/**
	 * シフト登録
	 */
	public String shiftRegister(@ModelAttribute ShiftForm shiftForm) {
		
		Map<LocalDate, String> shift = new LinkedHashMap<LocalDate, String>();
		
		// 日付を文字列配列に
		String[] dateStr = shiftForm.getDate().split(",");
		
		// 時間を文字列配列に
		String[] timeStr = shiftForm.getTime().split(",");
		
		for(int i =0; i < dateStr.length;i++) {
			
			shift.put(LocalDate.parse(dateStr[i]), timeStr[i]);
			
		}
		
		//キーとバリューをビューに渡して表示
		
		for(LocalDate key: shift.keySet()) {
			
			String sql = "INSERT INTO shift" + this.nextMonth + " (id, date, time)" + " VALUES ('" + securitySession.getId() + "', '" + key + "', '" + shift.get(key) + "')";
			jdbc.update(sql);
		}
		
		return null;
	}
	
	/**
	 * 登録済みの来月シフト確認
	 */
	public List<Map<String, Object>> isRegistered() {
		
		String sql = "SELECT * FROM shift" + this.nextMonth + " WHERE id = " + securitySession.getId();
		List<Map<String, Object>>  resultList = jdbc.queryForList(sql);
		
		return resultList;
		
	}
	
	/**
	 * 当月シフト表示
	 */
	public List<Map<String, Object>> look() {
		
		String sql = "SELECT * FROM shift" + this.thisMonth + " WHERE id = " + securitySession.getId();
		List<Map<String, Object>>  thisShift = jdbc.queryForList(sql);
		
		return thisShift;
	}

}
