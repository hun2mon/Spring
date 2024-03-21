package com.sprng.main.service;

import com.sprng.main.dao.HomeDAO;

public class HomeService {

	public String stmt() {
		String msg = "테이블 생성에 실패 하였습니다.";
		
		HomeDAO dao = new HomeDAO();
		
		if (dao.stmt()) {
			msg = "테이블이 생성 되었습니다.";
		}
		
		return msg;
	}

	public String insert(String id, String pw, String name, String age, String gender, String email) {
		String msg = "데이터 추가에 실패 하였습니다.";
		HomeDAO dao = new HomeDAO();
		boolean result =  dao.insert(id,pw,name,age,gender,email);
		if (result) {
			msg = "데이터를 추가 했습니다.";
		}
		
		return msg;
	}

}
