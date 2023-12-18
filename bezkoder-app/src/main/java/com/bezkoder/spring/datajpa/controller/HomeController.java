package com.bezkoder.spring.datajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bezkoder.spring.datajpa.config.SecuritySession;
import com.bezkoder.spring.datajpa.form.TimecardForm;
import com.bezkoder.spring.datajpa.service.TimecardService;
import com.bezkoder.spring.datajpa.service.UsersService;

@Controller
public class HomeController {
	
	@Autowired
	TimecardService timecardService;
	
	@Autowired
	SecuritySession securitySession;
	
	@Autowired
	UsersService usersService;
	
	/**
	 * ログイン後の分岐処理
	 * @param model
	 * @return
	 */
	@GetMapping("/success")
	public String loginView(Model model) {
		if(usersService.authentication().equals("ADMIN")) {
			return "admin";
		}else {
			model.addAttribute("name", usersService.user());
			return "home";
		}
	}
	
	/**
	 * ユーザーTOP画面表示
	 * @param model
	 * @return
	 */
	@GetMapping("/home")
	public String view(Model model) {
		model.addAttribute("name", usersService.user());
		model.addAttribute("TimecardForm", new TimecardForm());
		return "home";
		
	}
	
	/**
	 * 出勤ボタン押下時
	 */
	@RequestMapping("/work")
	public String work(@ModelAttribute TimecardForm timecardForm, Model model) {
		
		boolean errFlg = timecardService.work();
		if(errFlg) {
			model.addAttribute("name", usersService.user());
			model.addAttribute("workMsg", "出勤しました。");

			return "home";
		}else {
			model.addAttribute("name", usersService.user());
			model.addAttribute("workError", "出勤連絡済みです。");
			return "home";
		}
	}
}
