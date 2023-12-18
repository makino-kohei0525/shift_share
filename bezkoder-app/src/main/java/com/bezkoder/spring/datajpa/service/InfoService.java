package com.bezkoder.spring.datajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.datajpa.form.InfoForm;
import com.bezkoder.spring.datajpa.model.Info;
import com.bezkoder.spring.datajpa.repository.InfoRepository;

@Service
public class InfoService {
	
	@Autowired
	InfoRepository infoRepository;
	
	public Info info(InfoForm infoForm) {
		Info info = new Info();
		
		info.setId(infoForm.getId());
		info.setArea(infoForm.getArea());
		info.setType(infoForm.getType());
		info.setClient(infoForm.getClient());
		info.setStore(infoForm.getStore());
		info.setCareer(infoForm.getCareer());
		info.setTantou(infoForm.getTantou());
		
		return infoRepository.saveAndFlush(info);
	}

}
