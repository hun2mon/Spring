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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.board.dto.BoardDTO;
import kr.co.gudi.board.service.BoardService;

@Controller
public class BoardController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired BoardService service;
	
	// 글 목록 보기
	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session) {
		String page = "login";
		String msg = "로그인이 필요한 서비스 입니다.";
		String id = (String) session.getAttribute("loginId");
		if (id != null) {
			page = "list";
			List<BoardDTO> list = service.list();
			logger.info("list size() = " + list.size());
			model.addAttribute("list", list);
			model.addAttribute("loginBox", "<div>안녕하세요 " + id + "님 <a href='logout'>  로그아웃</a></div>");
		} else {
			model.addAttribute("msg", msg);			
		}
		return page;
	}
	
	// 글 삭제
	@RequestMapping(value = "/del")
	public String delete(String idx, HttpSession session) {
		logger.info("idx : " + idx);
		String page = "redirect:/";
		if (session.getAttribute("loginId") != null) {
			page = "redirect:/list";
			service.del(idx);
		}
		return page;
	}
	
	
	// 로그아웃
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		return "redirect:/";
	}
	
	// 글작성 페이지 이동
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	// 글 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(Model model, @RequestParam Map<String, String> param, HttpSession session) {
		String page = "redirect:/list";
		logger.info("params = {}", param);
		
		if (session.getAttribute("loginId") != null) {
			int row = service.write(param);
			if (row <1) {
				page = "writeFrom";
				
			}
		}
		
		return page;
	}
	
	// 글 보기
	@RequestMapping(value = "/detail")
	public String detail(Model model, String idx, HttpSession session) {
		String page = "redirect:/list";
		logger.info("detail id : " + idx); 
		
		if (session.getAttribute("loginId") != null) {
			BoardDTO bbs = service.detail(idx);
			page = "detail";
			model.addAttribute("bbs", bbs);
		}
		
		return page;
	}
	
	// 글 수정
		@RequestMapping(value = "/updateForm")
		public String updateForm(Model model, String idx, HttpSession session) {
			String page = "redirect:/list";
			logger.info("updateForm idx : " + idx); 
			
			if (session.getAttribute("loginId") != null) {
				BoardDTO bbs = service.updateForm(idx);
				page = "updateForm";
				model.addAttribute("bbs", bbs);
			}
			
			return page;
		}
	
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String update(Model model,String idx ,@RequestParam Map<String, String> param, HttpSession session) {
			String page = "redirect:/list";
			logger.info(idx);
			logger.info("params = {}", param);
			
			if (session.getAttribute("loginId") != null) {
				int row = service.update(param);
				if (row >= 1) {
					page = "redirect:/detail?idx=" + idx;
				}
			}
			
			return page;
		}
		
	
	
	
	
	
	
	
	
	
	
	
	
}
