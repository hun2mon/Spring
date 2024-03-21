package my.project.member.dao;

import java.util.List;
import java.util.Map;

import my.project.member.dto.MemberDTO;

public interface MemberDAO {

	int join(Map<String, String> param);

	String login(String id, String pw);

	List<MemberDTO> list();

	MemberDTO detail(String id);

	void del(String id);

}
