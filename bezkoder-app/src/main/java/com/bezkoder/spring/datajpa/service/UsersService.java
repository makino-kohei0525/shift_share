package com.bezkoder.spring.datajpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.datajpa.config.SecuritySession;
import com.bezkoder.spring.datajpa.form.EmployeeForm;
import com.bezkoder.spring.datajpa.model.Users;
import com.bezkoder.spring.datajpa.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	SecuritySession securitySession;
	
	/**
	 * 社員登録
	 * @param id
	 * @param name
	 * @param password
	 * @param authority
	 * @return
	 */
    public boolean createUsers(EmployeeForm employeeForm) {
        Users users = new Users();
        
        Optional<Users> list = usersRepository.findById(securitySession.getId());
        
        if(list == null) {
        
	        users.setId(employeeForm.getId());
	        users.setName(employeeForm.getName());
	        // パスワードはハッシュ化
	        users.setPassword(passwordEncoder.encode(employeeForm.getPassword()));
	        users.setAuthority("USER");
	        usersRepository.saveAndFlush(users);
	        return true;
        }else {
        	return false;
        }
        
    }
    
    public String user() {
    	Optional<Users> users = usersRepository.findById(securitySession.getId());
    	return users.get().getName();
    }
    
    // 権限情報を返す
    public String authentication() {
    	Optional<Users> users = usersRepository.findById(securitySession.getId());
    	return users.get().getAuthority();
    	
    }
}
