package kr.co.gudi.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.gudi.dto.MemberDTO;
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
		String page = "joinForm";
		String msg = "회원가입에 실패 했습니다. 다시 시도해 주세요";
		
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		
		MemberService service = new MemberService();
		int row = service.join(id,pw,name,age,gender,email);
		logger.info("성공 실패 여부" + row);
		
		if (row == 1) {
			page = "login";
			msg = "회원가입에 성공 했습니다.";
		}
		
		model.addAttribute("msg", msg);
		
		
		return page;
	}
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, String id, String pw, HttpSession session) {
		logger.info(id + "/" + pw);
		
		String page = "login";
		String msg = "아이디 또는 비밀번호를 확인해 주세요";
		
		MemberService service = new MemberService();
		if (service.login(id,pw)) {
			session.setAttribute("loginId", id);
			page = "redirect:/list";//list 라는 요청으로 이동시켜라
			//msg = id + "님 환영합니다."; // redirect 사용시 model 의 값을 전달할 수 없다.
		}

		model.addAttribute("msg", msg);
		
		return page;
	}
	
	//회원 리스트
	// method 를 지정하지 않으면 GET, POST, PUT, DELETE 다 받는다.
	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session) {
		logger.info("회원 리스트 요청");
		String page = "login";
		
		if (session.getAttribute("loginId") != null) {
			page = "list";
			MemberService service = new MemberService();
			List<MemberDTO> list =  service.list();
			logger.info("size : ",list.size());
			model.addAttribute("list", list);
		} else {
			page = "redirect:/";
		}
		
		
		return page;
	}
	
	// 회원 상세 조회
	@RequestMapping(value = "/detail")
	public String detail(Model model, String id, HttpSession session) {
		
		String page = "login";
		if (session.getAttribute("loginId") != null) {
			MemberService service = new MemberService();
			logger.info(id);
			MemberDTO dto =  service.detail(id);
			model.addAttribute("member", dto);
			page = "detail";
		}
		return page;
	}
	
	// 회원 삭제
	@RequestMapping(value = "/del")
	public String del(Model model ,String id,HttpSession session) {
		String page = "redirect:/";
		if (session.getAttribute("loginId") != null) {
			MemberService service = new MemberService();
			page = "redirect:/list";
			service.del(id);
		}
		return page;
	}
	
	
	
	
	
}


















