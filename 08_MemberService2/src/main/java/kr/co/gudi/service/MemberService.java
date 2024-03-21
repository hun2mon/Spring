package kr.co.gudi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.gudi.dao.MemberDao;
import kr.co.gudi.dto.MemberDTO;

public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	public int join(String id, String pw, String name, String age, String gender, String email) {
		logger.info("service로 요청 들어옴");
		
		
		MemberDao md = new MemberDao();
		
		return md.join(id,pw,name,age,gender,email);
	}

	public boolean login(String id, String pw) {
		MemberDao dao = new MemberDao();
		return dao.login(id,pw);
	}

	public List<MemberDTO> list() {
		MemberDao dao = new MemberDao();
		return dao.list();
	}

	public MemberDTO detail(String id) {
		MemberDao dao = new MemberDao();
		return dao.detail(id);
		
	}

	public void del(String id) {
		MemberDao dao = new MemberDao();
		dao.del(id);
	}

	

}
