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
	
	@Autowired MemberService service;

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/")
	public String home() {
		return "login";
	}
	
	@RequestMapping(value = "/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model ,@RequestParam Map<String, String> param) {
		String page = "joinForm";
		String msg = "회원가입에 실패 하였습니다.";
		logger.info("들어온 값 : " + param);
		try {
			int row = service.join(param);
			if (row == 1) {
				page = "login";
				msg = "회원가입에 성공 했습니다.";
			}
		} catch (Exception e) {
			model.addAttribute("msg", msg);	
			return page;
		} finally {
			model.addAttribute("msg", msg);			
		}
		
		
		return page;
	}
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public String login(Model model, HttpSession session, String id, String pw) {
		String page = "login";
		String msg = "로그인에 실패하였습니다. 다시 시도해 주세요.";
		
		String loginId = service.login(id,pw);
		if (loginId != null) {
			session.setAttribute("loginId", loginId);
			page = "redirect:/list";
		}
		model.addAttribute("msg" , msg);
		
		return page;
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session) {
		String page = "login";
		logger.info(session.getAttribute("loginId") + "as");
		if (session.getAttribute("loginId") != null) {
			List<MemberDTO> list = service.list();
			page = "list";
			model.addAttribute("list", list);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/detail")
	public String detail(Model model, String id) {
		logger.info(id);
		MemberDTO dto = service.detail(id);
		model.addAttribute("member", dto);
		
		return "detail";
	}
	
	@RequestMapping(value = "/del")
	public String del(String id) {
		service.del(id);
		return "redirect:/list";
	}
	
	
}
