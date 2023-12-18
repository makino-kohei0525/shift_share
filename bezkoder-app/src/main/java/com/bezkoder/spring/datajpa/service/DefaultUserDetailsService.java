package com.bezkoder.spring.datajpa.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.datajpa.model.Users;
import com.bezkoder.spring.datajpa.repository.UsersRepository;

/**
 * インタフェース実装クラス
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService{

	@Autowired
	UsersRepository usersRepository;
	
	/**
	 * ユーザー情報をDBから取得
	 */
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
		
		Users users = usersRepository
				.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
		return new org.springframework.security.core.userdetails.User(users.getId(), users.getPassword(), Collections.emptyList());
	}
}
