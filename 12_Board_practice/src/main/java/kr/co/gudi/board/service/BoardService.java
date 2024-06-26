package kr.co.gudi.board.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.board.dao.BoardDAO;
import kr.co.gudi.board.dto.BoardDTO;

@Service
public class BoardService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired BoardDAO dao;

	public List<BoardDTO> list() {
		return dao.list();
	}

	public void del(String idx) {
		dao.del(idx);
	}

	public int write(Map<String, String> param) {
		int row = -1;
		row = dao.write(param);
		logger.info("insert count :" + row);
		return  row;
	}

	public BoardDTO detail(String idx) {
		dao.upHit(idx); // 조회수 증가
		return dao.detail(idx);
	}

	public BoardDTO updateForm(String idx) {
		return dao.updateForm(idx);
	}

	public void update(Map<String, String> param) {
		dao.updateContent(param);
		dao.updateSubject(param);
	}
}
