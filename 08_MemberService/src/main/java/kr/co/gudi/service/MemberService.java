package kr.co.gudi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.gudi.dao.MemberDao;

public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	public String join(String id, String pw, String name, String age, String gender, String email) {
		logger.info("service로 요청 들어옴");
		
		String msg = "실패";
		
		MemberDao md = new MemberDao();
		if (md.join(id,pw,name,age,gender,email)) {
			msg = "성공";
		}
		
		
		return msg;
	}

}
