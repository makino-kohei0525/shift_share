package com.bezkoder.spring.datajpa.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.datajpa.form.IrregularForm;
import com.bezkoder.spring.datajpa.model.Irregular;
import com.bezkoder.spring.datajpa.repository.IrregularRepository;

@Service
public class AdminService {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private IrregularRepository irrRepository;

	/**
	 * シフト表示画面に表示するDB情報取得
	 */
	public List<Map<String, Object>> employee(String month) {
		String sql = "SELECT user.id, user.name, info.area, info.type, info.client, info.store, info.career, info.tantou "
				+ "FROM user "
				+ "LEFT OUTER JOIN info ON user.id = info.id "
				+ "WHERE user.authority = 'USER'";

		List<Map<String, Object>> employeeList = jdbc.queryForList(sql);

		for (Map<String, Object> employee : employeeList) {

			String shiftSql = "SELECT * "
					+ "FROM shift" + month + " "
					+ "WHERE id = " + employee.get("id");

			List<Map<String, Object>> shiftList = jdbc.queryForList(shiftSql);

			for (Map<String, Object> shift : shiftList) {

				employee.put(shift.get("date").toString(), shift.get("time"));
			}
		}
		return employeeList;
	}

	// シフト表示画面のラベル
	public List<String> label(String month) {

		List<String> label = new ArrayList<>();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dateFormat2 =DateTimeFormatter.ofPattern("MM/dd");
		int i = 0;
		for (String key : employee(month).get(0).keySet()) {
			if (i >= 8) {
				LocalDate keyDate = LocalDate.parse(key, dateFormat);
				label.add(keyDate.format(dateFormat2));
			}
			i++;
		}
//		label.subList(0, 8).clear();
		label.addAll(0, Arrays.asList("ID", "名前", "地域", "業種", "クライアント", "店舗", "キャリア", "担当"));

		return label;
	}

	/**
	 * 社員情報一覧画面に表示する情報
	 */
	public List<Map<String, Object>> employeeList() {

		String sql = "SELECT user.id, user.name, info.area, info.type, info.client, info.store, info.career, info.tantou "
				+ "FROM user "
				+ "LEFT OUTER JOIN info ON user.id = info.id "
				+ "WHERE user.authority = 'USER'";

		List<Map<String, Object>> employeeList = jdbc.queryForList(sql);

		return employeeList;
	}

	// 社員情報一覧画面のラベル
	public List<String> labelList() {

		List<String> label = new ArrayList<>();
		label.addAll(Arrays.asList("ID", "名前", "地域", "業種", "クライアント", "店舗", "キャリア", "担当"));
		return label;
	}

	/**
	 * イレギュラー登録
	 */
	public void irrRegister(IrregularForm irregularForm) {
		Irregular irregular = new Irregular();

		LocalDate date = LocalDate.parse(irregularForm.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String tableYM = date.format(DateTimeFormatter.ofPattern("yyyyMM"));
		irregular.setId(irregularForm.getId());
		irregular.setDate(date);
		irregular.setColor(irregularForm.getColor());
		irregular.setComment(irregularForm.getComment());
		irregular.setTableYM(tableYM);
		
		irrRepository.insert(irregular);

	}

}
