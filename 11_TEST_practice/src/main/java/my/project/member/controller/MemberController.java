package my.project.member.controller;

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

import my.project.member.dto.MemberDTO;
import my.project.member.service.MemberService;

@Controller
public class MemberController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberService service;

	@RequestMapping(value = "/")
	public String home() {
		
		return "login";
	}
	
	@RequestMapping(value = "/joinForm")
	public String joinForm() {
		String page = "joinForm";
		return page;
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, @RequestParam Map<String,String> param) {
		String page = "joinForm";
		String msg = "회원가입에 실패하였습니다.";
		logger.info("회원 가입 정보 : " + param);
		try {
			int row = service.join(param);
			if (row == 1) {
				page = "login";
				msg = "회원가입에 성공 하였습니다.";
			}
		} catch (Exception e) {
			return "joinForm";
		} finally {
			model.addAttribute("msg", msg);
		}
		
		return page;
	}
	
	@RequestMapping(value ="/login" , method = RequestMethod.POST)
	public String login(Model model, String id, String pw, HttpSession session) {
		String page = "login";
		String msg = "로그인 실패! 다시 시도해 주세요.";
		logger.info("id : {} pw : {}",id,pw);
		String loginId = service.login(id,pw);
		if (loginId != null) {
			page = "redirect:/list";
			session.setAttribute("loginId", loginId);
		}
		model.addAttribute("msg", msg);
		
		return page;
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session) {
		String page = "login";
		
		if (session.getAttribute("loginId") != null) {
			logger.info("list 요청 들어옴");
			List<MemberDTO> list = service.list();
			page = "list";
			model.addAttribute("list", list);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/detail")
	public String detail(Model model, String id, HttpSession session) {
		String page = "login";
		
		if (session.getAttribute("loginId") != null) {
			page = "detail";
			MemberDTO dto = service.detail(id);
			model.addAttribute("member", dto);
		}
		return page;
	}
	
	@RequestMapping(value = "/del")
	public String del(String id) {
		service.del(id);
		return "redirect:/list";
	}
	
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		return "redirect:/";
	}
	
}
