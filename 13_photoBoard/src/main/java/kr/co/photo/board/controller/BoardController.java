package kr.co.photo.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.photo.board.dto.BoardDTO;
import kr.co.photo.board.service.BoardService;

@Controller
public class BoardController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService service;
	
	// 글목록 보기
	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session) {
		String page = "login";
		logger.info("loginId = "+session.getAttribute("loginId"));
		String loginId = (String) session.getAttribute("loginId");
		if (session.getAttribute("loginId") != null) {
			page = "list";
			List<BoardDTO> list = service.list();
			model.addAttribute("list", list);
			model.addAttribute("loginBox", "<div>" + loginId +"님 환영 합니다.  <a href = 'logout'>로그아웃</a><div>");
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
		}
		
		return page;
	}
	
	//로그아웃
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		return "login";
	}
	
	//글작성 페이지 이동
	@RequestMapping(value = "/writeForm")
	public String writeForm(HttpSession session) {
		String page = "redirect:/list";
		if (session.getAttribute("loginId") != null) {
			page = "writeForm";
		}
		return page;
	}
	
	//게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@RequestParam Map<String,String> param) {
		String page = "redirect:/list";
		logger.info("params{}",param);
		service.write(param);
		return page;
	}
	
	//게시글 보기
	@RequestMapping(value = "/detail")
	public String detail(Model model,String idx,HttpSession session) {
		String page = "redirect:/list";
		logger.info("idx = " + idx);
		if (session.getAttribute("loginId") != null) {
			page = "detail";
			BoardDTO bbs = service.detail(idx);
			model.addAttribute("bbs", bbs);
		}
		return page;
	}
	
	//글 삭제
	@RequestMapping(value = "/del")
	public String del(String idx) {
		logger.info(idx+"");
		service.del(idx);
		return "redirect:/list";
	}
	
	//글 수정페이지 이동
	@RequestMapping(value = "/updateForm")
	public String updateForm(Model model ,HttpSession session, String idx) {
		String page = "redirect:/list";
		if (session.getAttribute("loginId") != null) {
			BoardDTO bbs = service.updateForm(idx);
			model.addAttribute("bbs", bbs);
			page = "updateForm";
		}
		return page;
	}
	
	//글 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam Map<String,String> param, String idx) {
		String page = "redirect:/detail?idx=" + idx;
		logger.info("params{}",param);
		service.update(param);
		return page;
	}
	
	
	
	
	
	
	
}
