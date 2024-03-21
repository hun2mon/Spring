package kr.co.gudi.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	//최초 페이지(로그인 이동)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		
		return "login";
	}
	
	//회원가입 페이지 이동
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public String joinForm() {
		logger.info("회원가입 페이지 이동");
		
		return "joinForm";
	}
	
	//회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		
		if (id == null || pw == null || name == null|| age == null|| gender == null|| email == null) {
			model.addAttribute("msg", "정보를 입력해 주세요.");
			return "joinForm";
		}
		
		logger.info(id + "/" + pw + "/" + name + "/" + age + "/" + gender + "/" + email);
		
		MemberService ms = new MemberService();
		String msg = ms.join(id,pw,name,age,gender,email);
		model.addAttribute("msg", msg);
		
		if (msg.equals("성공")) {
			return "/login";
		}
		
		
		return "joinForm";
	}
}


















