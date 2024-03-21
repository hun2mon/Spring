package my.project.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import my.project.member.dto.MemberDTO;

@Service
public interface MemberDAO {

	int join(Map<String, String> param);

	String login(String id, String pw);

	List<MemberDTO> list();

	MemberDTO detail(String id);

	void del(String id);

}
