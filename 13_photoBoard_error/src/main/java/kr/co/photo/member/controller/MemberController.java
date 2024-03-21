package kr.co.photo.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.photo.member.service.MemberService;

@Controller
public class MemberController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberService service;
	
	@RequestMapping(value = "/error/404")
	public String notFound(Model model) {
		model.addAttribute("code", "404");
		model.addAttribute("msg", "페이지 또는 요청 못찾음");
		return "error";
	}
	
	@RequestMapping(value = "/error/500")
	public String serverError(Model model) {
		model.addAttribute("code", "500");
		model.addAttribute("msg", "서버 처리중 문제 발생");
		return "error";
	}
	
	//메인 페이지 이동
	@RequestMapping(value = "/")
	public String home() {
		logger.info("home 요청 들어옴");
		return "login";
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String id, String pw,HttpSession session, Model model) {
		String page = "login";
		String msg = "아이디 비밀번호를 확인해 주세요.";
		logger.info("id : {} pw : {}",id,pw);
		String loginId = service.login(id,pw);
		if (loginId != null) {
			page = "redirect:/list";
			session.setAttribute("loginId", loginId);
		}else {
			model.addAttribute("msg", msg);
		}
		
		
		return page;
	}
	
}
