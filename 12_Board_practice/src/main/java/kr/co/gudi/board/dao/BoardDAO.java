package kr.co.gudi.board.dao;

import java.util.List;
import java.util.Map;

import kr.co.gudi.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list();

	void del(String idx);

	int write(Map<String, String> param);

	BoardDTO detail(String idx);

	void upHit(String idx);

	BoardDTO updateForm(String idx);

	void updateContent(Map<String, String> param);

	void updateSubject(Map<String, String> param);

}
