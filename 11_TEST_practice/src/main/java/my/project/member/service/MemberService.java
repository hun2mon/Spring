package my.project.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.project.member.dao.MemberDAO;
import my.project.member.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired MemberDAO dao;
	
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
