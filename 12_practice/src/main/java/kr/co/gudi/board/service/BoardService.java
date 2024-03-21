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
	
	@Autowired BoardDAO dao;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public List<BoardDTO> list() {
		return dao.list();
	}

	public int delete(String idx) {
		return dao.delete(idx);
	}

	public BoardDTO detail(String idx) {
		dao.upHit(idx);
		return dao.detail(idx);
	}

	public void write(Map<String, String> param) {
		dao.write(param);
	}

}
