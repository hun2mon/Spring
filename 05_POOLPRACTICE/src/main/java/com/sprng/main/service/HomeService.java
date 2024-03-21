package com.sprng.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sprng.main.dao.HomeDAO;

public class HomeService {

	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);	

	
	public String result() {
		String msg = "접속에 실패하였습니다.";
		
		HomeDAO dao = new HomeDAO();
		if (dao.dbConnect()) {
			msg = "접속에 성공하였습니다.";
		}
		
		
		return msg;
	}


	public String dbPool() {
		String msg = "접속에 실패 했습니다.";
		
		HomeDAO dao = new HomeDAO();
		
		if (dao.dbPool()) {
			msg = "접속에 성공했습니다.";
			
		}
		return msg;
	}

}
