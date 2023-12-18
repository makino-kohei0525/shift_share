package com.bezkoder.spring.datajpa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bezkoder.spring.datajpa.form.EmployeeForm;
import com.bezkoder.spring.datajpa.form.InfoForm;
import com.bezkoder.spring.datajpa.service.AdminService;
import com.bezkoder.spring.datajpa.service.InfoService;
import com.bezkoder.spring.datajpa.service.UsersService;

@Controller
public class AdminController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	InfoService infoService;
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/admin")
	public String view(Model model) {
		
		if(usersService.authentication().equals("ADMIN")) {
			model.addAttribute("employeeForm", new EmployeeForm());
			return "admin";
		}else {
			return "redirect:/top";
		}
	}
	
	/**
	 * 所属情報・シフト表示
	 * @param month
	 * @param model
	 * @return
	 */
	@PostMapping("/select/month")
	public String month(@RequestParam(name = "month")String submitMonth, Model model) {
		
		String date = submitMonth + "-01";
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String month = localDate.format(DateTimeFormatter.ofPattern("yyyyMM"));
		
		model.addAttribute("employee", adminService.employee(month));
		model.addAttribute("label", adminService.label(month));
		
		return "employee";
	}
	
	@PostMapping("/list/employee")
	public String list(Model model) {
		model.addAttribute("employee", adminService.employeeList());
		model.addAttribute("label", adminService.labelList());
		
		return "employee";
	}

	/**
	 * 社員情報登録
	 */
	@PostMapping("/employee/input")
	public String user(@ModelAttribute EmployeeForm employeeForm) {
		
		boolean errFlg = usersService.createUsers(employeeForm);
		
		if(errFlg) {
			return "admin";
		}else {
			return "admin";
		}
	}
	
	/**
	 * 	所属情報登録
	 */
	@PostMapping("/employee/info")
	public String info(@ModelAttribute InfoForm infoForm) {
		
		infoService.info(infoForm);
		return "admin";
	}
}
