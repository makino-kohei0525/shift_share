package com.bezkoder.spring.datajpa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.datajpa.config.SecuritySession;
import com.bezkoder.spring.datajpa.model.Timecard;
import com.bezkoder.spring.datajpa.repository.TimecardRepository;

@Service
public class TimecardService {
	
	@Autowired
	private final TimecardRepository repository;
	
	@Autowired
	SecuritySession securitySession;
	private String thisMonth;

	public TimecardService(TimecardRepository repository) {
        this.repository = repository;
        this.setThisMonth();
    }
	
	/**
	 * 出勤情報登録
	 */
	
	public boolean work() {
		/**
		 * 現在時刻とユーザー情報を取得してDB登録
		 */
		LocalDate nowDate = LocalDate.now();
		LocalDateTime nowDateTime = LocalDateTime.now();
		Timecard timecard = new Timecard();
		
//		List<Timecard> list = repository.findByIdAndDate(securitySession.getId(), nowDate);
		 boolean res = repository.findByIdAndDate(this.thisMonth, securitySession.getId(), nowDate);
		
		
//		if(list.size() == 0) {
		if(!res) {
			
			timecard.setTableYM(thisMonth);
			timecard.setId(securitySession.getId());
			timecard.setDate(nowDate);
			timecard.setTime(nowDateTime);
			repository.insert(timecard);
			
			return true;
			
		}else {
			
			return false;
		}
	}
	
	/**
	 * 当月の表示形式
	 */
	public void setThisMonth() {
		LocalDate current = LocalDate.now();
		this.thisMonth = current.format(DateTimeFormatter.ofPattern("yyyyMM"));
	}
}
