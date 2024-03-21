package kr.co.gudi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.dto.MemberDTO;
import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired MemberService service;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("main page");
		
		return "login";
	}
	
	@RequestMapping(value = "/joinForm")
	public String joinForm() {
		logger.info("joinForm 이동");
		return "joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, @RequestParam Map<String,String> param) {
		String page = "joinForm";
		String msg = "회원가입에 실패 했습니다.";
		
		logger.info("param : " + param); 
		
		int row = service.join(param);
		
		if (row == 1) {
			page = "login";
			msg = "회원가입에 성공 했습니다.";
		}
		
		model.addAttribute("msg", msg);
		
		return page;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model , HttpSession session, String id, String pw) {
		String page = "login";
		logger.info("id : {} / pw : {}",id,pw);
		
		String loginId = service.login(id,pw);
		logger.info("loginId : " + loginId);
		
		if (loginId != null) {
			page = "redirect:/list";
			session.setAttribute("loginId", loginId);
		} else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인해 주세요");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session) {
		logger.info("list 요청");
		
		String page = "redirect:/";
		
		if (session.getAttribute("loginId") != null) {
			page = "list";
			List<MemberDTO> list = service.list();
			model.addAttribute("list", list);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/detail")
	public String detail(Model model, String id) {
		logger.info(id);
		MemberDTO member = service.detail(id);
		model.addAttribute("member", member);
		
		return "detail";
	}
	
	@RequestMapping(value = "/del")
	public String del(HttpSession session ,String id) {
		String page = "redirect:/";
		
		if (session.getAttribute("loginId") != null) {
			page =  "redirect:/list";
		}
		logger.info(id);
		service.del(id);
		
		return page;
	}
	
	
	
}
