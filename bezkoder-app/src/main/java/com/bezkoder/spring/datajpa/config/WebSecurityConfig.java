package com.bezkoder.spring.datajpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	/**
	 * アクセス制御
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		http
			.formLogin()
				.usernameParameter("id")
				.passwordParameter("password")
				.defaultSuccessUrl("/success");
	}
	
	/**
	 * パスワードハッシュ化
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 認証マネージャー
	 * 登録API使用時に実装
	 */
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception{
//		
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}
	
}