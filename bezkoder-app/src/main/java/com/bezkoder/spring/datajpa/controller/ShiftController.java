package com.bezkoder.spring.datajpa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bezkoder.spring.datajpa.form.ShiftForm;
import com.bezkoder.spring.datajpa.service.ShiftService;
import com.bezkoder.spring.datajpa.service.UsersService;

@Controller
public class ShiftController {
	
	@Autowired
	ShiftService shiftService;
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("shift")
	public String view(@ModelAttribute ShiftForm shiftForm,Model model) {
		
		model.addAttribute("name", usersService.user());
		model.addAttribute("shift",  shiftService.shift(shiftForm));

		return "shift";
	}
	
	/**
	 * シフト管理画面表示
	 */
	@RequestMapping("/shiftAdmin")
	public String top(@ModelAttribute ShiftForm shiftForm, Model model) {
		
		model.addAttribute("name", usersService.user());
		
		return "shiftAdmin";
	}
	
	/**
	 * 入力画面表示
	 */
	@RequestMapping("/register")
	public String register(@ModelAttribute ShiftForm shiftForm, Model model) {
		
		model.addAttribute("name", usersService.user());
		model.addAttribute("month",  shiftService.month());
		model.addAttribute("shiftForm", new ShiftForm());
		model.addAttribute("shift",  shiftService.shift(shiftForm));
		
		List<Map<String, Object>>  resultList = shiftService.isRegistered();
		
		// シフト未登録の場合
		if(resultList.size() == 0) {
			model.addAttribute("name", usersService.user());
			return "shift";
		// シフト登録済みの場合
		}else {
			model.addAttribute("name", usersService.user());
			model.addAttribute("resultList", resultList);
			
			return "confirm";
		}
			
	}
	
	/**
	 * 入力画面からの遷移
	 */
	@RequestMapping("/update")
	public String update(
			@RequestParam("action")String action,
			@ModelAttribute ShiftForm shiftForm, 
			Model model) {
		
		if(action.equals("戻る")) {
			model.addAttribute("name", usersService.user());
			return "shiftAdmin";
			
		}if(action.equals("確認")) {
			model.addAttribute("name", usersService.user());
			model.addAttribute("inputShift", shiftService.inputShift(shiftForm));
			return "check";

		}else {
			return null;
		}
	}
	
	/**
	 * 確認画面からの遷移
	 */
	@RequestMapping("/submit")
	public String submit(
			@RequestParam("action")String action,
			@ModelAttribute ShiftForm shiftForm, 
			Model model) {
		if(action.equals("戻る")) {
			
			model.addAttribute("month",  shiftService.month());
			model.addAttribute("name", usersService.user());
			model.addAttribute("shiftForm", new ShiftForm());
			model.addAttribute("shift",  shiftService.shift(shiftForm));
			model.addAttribute("inputShift", shiftService.inputShift(shiftForm));
			
			return "shift";
			
		}if(action.equals("登録")) {
			
			shiftService.shiftRegister(shiftForm);
			model.addAttribute("name", usersService.user());
			
			return "home";
		}else {
			return null;
		}
		
	}

	/**
	 * 当月シフト確認ボタン押下処理
	 * @param model
	 * @return
	 */
	@RequestMapping("/look")
	public String look(Model model) {
		
		shiftService.thisMonth();
		
		List<Map<String, Object>> thisShift = shiftService.look();
		model.addAttribute("name", usersService.user());
		model.addAttribute("thisShift", thisShift);
		
		return "confirm";
	}
}
