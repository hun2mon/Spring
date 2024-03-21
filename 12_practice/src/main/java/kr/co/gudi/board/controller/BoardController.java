package kr.co.gudi.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.board.dto.BoardDTO;
import kr.co.gudi.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired BoardService service;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	// 게시물 리스트
	@RequestMapping(value = "/list")
	public String list(Model model ,HttpSession session) {
		model.addAttribute("logout", "<div>" + session.getAttribute("loginId") + "님 환영합니다. <a href = 'logout' >로그아웃</a><div>");
		String page = "login";
		logger.info("list 요청 들어옴");
		if (session.getAttribute("loginId") != null) {
			page = "list";
			List<BoardDTO> list = service.list();
			model.addAttribute("list", list);
		} else {
			model.addAttribute("msg", "로그인 후 이용가능합니다.");
		}
		return page;	
	}
	
	//로그아웃
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		return "login";
	}
	
	//게시글 삭제
	@RequestMapping(value = "/del")
	public String delete(String idx) {
		logger.info("idx : " + idx);
		int row = service.delete(idx);
		logger.info("row : " + row);
		return "redirect:/list";
	}
	
	//게시글 상세 보기
	@RequestMapping(value = "/detail")
	public String detail(Model model, String idx) {
		logger.info("idx : " + idx);
		BoardDTO param = service.detail(idx);
		model.addAttribute("bbs", param);
		return "detail";
	}
	
	//게시글 작성페이지 이동
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	//게시글 작성
	@RequestMapping(value = "/write")
	public String write(Model model, @RequestParam Map<String,String> param, HttpSession session) {
		String page = "writeForm";
		logger.info("params : " + param);
		if (session.getAttribute("loginId") != null) {
			service.write(param);
			page = "redirect:/list";
		}
		
		return page;
	}

}
