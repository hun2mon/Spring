package kr.co.gudi.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.MemberDAO;
import kr.co.gudi.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired MemberDAO dao;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public int join(Map<String, String> param) {
		return dao.join(param);
	}

	public String login(String id, String pw) {
		return dao.login(id,pw);
	}

	public List<MemberDTO> list() {
		return dao.list();
	}

	public MemberDTO detail(String id) {
		return dao.detail(id);
	}

	public void del(String id) {
		dao.del(id);
	}

}
