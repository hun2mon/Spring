package kr.co.gudi.service;

import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.MemberDAO;

@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberDAO dao;
	public int join(String id, String pw, String name, String age, String gender, String email) {
		
		int row = dao.join(id,pw,name,age,gender,email);
		return row;
	}
	
	
	

}
