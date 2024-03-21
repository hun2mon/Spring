package kr.co.gudi.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.gudi.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired MemberService service;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("로그인 페이지 진입");
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, String id, String pw) {
		String page = "login";
		String msg = "아이디 비밀번호를 확인해 주세요";
		logger.info("id : {} pw : {}",id,pw);
		
		String loginId = service.login(id,pw);
		logger.info("loginId : " +loginId);
		if (loginId != null) {
			page = "redirect:/list";
			session.setAttribute("loginId", loginId);
		} else {
			model.addAttribute("msg", msg);			
		}
		
		
		return page;
	}
}
